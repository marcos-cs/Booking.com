package com.booking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AwardTopKHotelsTest {

    @Test
    @DisplayName("Should return top k hotels sorted by score descending")
    void shouldReturnTopKHotelsByScore() {
        String positiveKeywords = "breakfast beach citycenter location metro view staff price";
        String negativeKeywords = "not";
        int[] hotelIds = {1, 2, 1, 1, 2};

        List<String> reviews = List.of(
                "This hotel has a nice view of the citycenter. The location is perfect",
                "The breakfast is ok. Regarding location, it is quite far from citycenter but price is cheap so it is worth",
                "location is excelent, 5 minutes from citycenter. There is also a metro station very close to the hotel",
                "they said i couldn't take my doc and there were other with dogs! that is not fair",
                "very friendly staff and good cost-benefit ratio. its location is a bit far from citycenter"
        );

        List<Integer> result = AwardTopKHotels.awardTopKHotels(positiveKeywords, negativeKeywords, hotelIds, reviews, 2);

        // Hotel 2: breakfast+location+citycenter+price (12) + staff+location+citycenter (9) = 21
        // Hotel 1: view+citycenter+location (9) + location+citycenter+metro (9) + not (-1) = 17
        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(2, 1);
    }

    @Test
    @DisplayName("Negative keywords should subtract 1 from score")
    void negativeKeywordsShouldSubtractFromScore() {
        String positiveKeywords = "good";
        String negativeKeywords = "bad terrible";
        int[] hotelIds = {1, 2};

        List<String> reviews = List.of(
                "bad terrible bad",    // Hotel 1: -3
                "good"                 // Hotel 2: +3
        );

        List<Integer> result = AwardTopKHotels.awardTopKHotels(positiveKeywords, negativeKeywords, hotelIds, reviews, 2);

        assertThat(result).containsExactly(2, 1);
    }

    @Test
    @DisplayName("Positive keywords should add 3 to score")
    void positiveKeywordsShouldAdd3ToScore() {
        String positiveKeywords = "great amazing";
        String negativeKeywords = "bad";
        int[] hotelIds = {1, 2};

        List<String> reviews = List.of(
                "great amazing",  // Hotel 1: +6
                "great bad"       // Hotel 2: +3 - 1 = +2
        );

        List<Integer> result = AwardTopKHotels.awardTopKHotels(positiveKeywords, negativeKeywords, hotelIds, reviews, 2);

        assertThat(result).containsExactly(1, 2);
    }

    @Test
    @DisplayName("Should return empty list when no hotels or reviews")
    void shouldReturnEmptyForEmptyInput() {
        assertThat(AwardTopKHotels.awardTopKHotels("good", "bad", new int[]{}, List.of(), 1))
                .isEmpty();

        assertThat(AwardTopKHotels.awardTopKHotels("good", "bad", new int[]{1}, List.of(), 1))
                .isEmpty();
    }

    @Test
    @DisplayName("Should handle k larger than number of hotels")
    void shouldHandleKLargerThanHotelCount() {
        String positiveKeywords = "good";
        String negativeKeywords = "bad";
        int[] hotelIds = {1};

        List<String> reviews = List.of("good");

        List<Integer> result = AwardTopKHotels.awardTopKHotels(positiveKeywords, negativeKeywords, hotelIds, reviews, 5);

        assertThat(result).containsExactly(1);
    }

    @Test
    @DisplayName("Should be case insensitive when matching keywords")
    void shouldBeCaseInsensitive() {
        String positiveKeywords = "good";
        String negativeKeywords = "bad";
        int[] hotelIds = {1};

        List<String> reviews = List.of("GOOD Good good");

        List<Integer> result = AwardTopKHotels.awardTopKHotels(positiveKeywords, negativeKeywords, hotelIds, reviews, 1);

        assertThat(result).containsExactly(1);
    }

    @Test
    @DisplayName("Should ignore punctuation in reviews")
    void shouldIgnorePunctuation() {
        String positiveKeywords = "good";
        String negativeKeywords = "bad";
        int[] hotelIds = {1};

        List<String> reviews = List.of("good, very good. not bad");

        List<Integer> result = AwardTopKHotels.awardTopKHotels(positiveKeywords, negativeKeywords, hotelIds, reviews, 1);

        // "good" appears twice (+6), "bad" appears once (-1) = 5
        assertThat(result).containsExactly(1);
    }
}
