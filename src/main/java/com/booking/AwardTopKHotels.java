package com.booking;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Award Top K Hotels
 *
 * Given a list of hotel reviews, positive/negative keywords, and a number k,
 * calculate review scores and return the top k hotel IDs sorted by score (descending).
 * Positive keywords weigh +3 points each, negative keywords weigh -1 each.
 */
public class AwardTopKHotels {

    public static void main(String[] args) {
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
        int k = 2;

        List<Integer> top = awardTopKHotels(positiveKeywords, negativeKeywords, hotelIds, reviews, k);
        System.out.println(top);
    }

    /**
     * Returns a list of the top k hotel IDs sorted by review score in descending order.
     * On ties, hotels with lower IDs come first.
     */
    public static List<Integer> awardTopKHotels(String positiveKeywords, String negativeKeywords,
                                                 int[] hotelIds, List<String> reviews, int k) {
        if (hotelIds.length == 0 || reviews.isEmpty()) {
            return Collections.emptyList();
        }

        Set<String> positiveSet = convertToSet(positiveKeywords);
        Set<String> negativeSet = convertToSet(negativeKeywords);

        Map<Integer, Integer> hotelScores = analyzeHotels(hotelIds, reviews, positiveSet, negativeSet);

        return hotelScores.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(k)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private static Map<Integer, Integer> analyzeHotels(int[] hotelIds, List<String> reviews,
                                                        Set<String> positiveSet, Set<String> negativeSet) {
        Map<Integer, Integer> hotelScores = new HashMap<>();

        for (int i = 0; i < reviews.size(); i++) {
            String[] words = reviews.get(i).toLowerCase().replaceAll("[,.]", "").split("\\s+");
            int hotelId = hotelIds[i];

            for (String word : words) {
                if (positiveSet.contains(word)) {
                    hotelScores.merge(hotelId, 3, Integer::sum);
                }
                if (negativeSet.contains(word)) {
                    hotelScores.merge(hotelId, -1, Integer::sum);
                }
            }
        }

        return hotelScores;
    }

    private static Set<String> convertToSet(String keywords) {
        return new HashSet<>(Arrays.asList(keywords.split("\\s+")));
    }
}
