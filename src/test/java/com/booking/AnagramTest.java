package com.booking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AnagramTest {

    @Test
    @DisplayName("Should group mutual anagrams from the problem example")
    void shouldGroupAnagramsFromExample() {
        String[] words = {"pear", "amleth", "dormitory", "tinsel", "dirty room", "hamlet", "listen", "silnet"};

        List<String> result = Anagram.groupAnagrams(words);

        assertThat(result).containsExactly(
                "amleth,hamlet",
                "dirty room,dormitory",
                "listen,silnet,tinsel",
                "pear"
        );
    }

    @Test
    @DisplayName("Should handle single words (no anagram pairs)")
    void shouldHandleSingleWords() {
        String[] words = {"hello", "world"};

        List<String> result = Anagram.groupAnagrams(words);

        assertThat(result).containsExactly("hello", "world");
    }

    @Test
    @DisplayName("Should handle empty input")
    void shouldHandleEmptyInput() {
        List<String> result = Anagram.groupAnagrams(new String[]{});

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Should be case sensitive (as per spec)")
    void shouldDetectAnagramsWithSpaces() {
        String[] words = {"dirty room", "dormitory"};

        List<String> result = Anagram.groupAnagrams(words);

        assertThat(result).containsExactly("dirty room,dormitory");
    }

    @Test
    @DisplayName("Anagram signature should ignore spaces")
    void signatureShouldIgnoreSpaces() {
        assertThat(Anagram.getAnagramSignature("dirty room"))
                .isEqualTo(Anagram.getAnagramSignature("dormitory"));
    }

    @Test
    @DisplayName("Different character counts should NOT be anagrams")
    void differentCharCountsShouldNotBeAnagrams() {
        // "the" and "thee" are NOT anagrams per the spec
        assertThat(Anagram.getAnagramSignature("the"))
                .isNotEqualTo(Anagram.getAnagramSignature("thee"));
    }

    @Test
    @DisplayName("Words sorted lexicographically within groups")
    void wordsShouldBeSortedWithinGroups() {
        String[] words = {"hamlet", "amleth"};

        List<String> result = Anagram.groupAnagrams(words);

        assertThat(result).containsExactly("amleth,hamlet");
    }
}
