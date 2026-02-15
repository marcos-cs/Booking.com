package com.booking;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Mutual Anagrams Detector
 *
 * Given a list of words, groups them by mutual anagrams.
 * Spaces are ignored when determining anagrams.
 * Each group is output as a comma-separated string, sorted lexicographically.
 * Groups themselves are also sorted lexicographically (by first element).
 */
public class Anagram {

    public static void main(String[] args) {
        String[] words = {"pear", "amleth", "dormitory", "tinsel", "dirty room", "hamlet", "listen", "silnet"};

        List<String> result = groupAnagrams(words);
        result.forEach(System.out::println);
    }

    /**
     * Groups words that are mutual anagrams and returns them as sorted comma-separated strings.
     *
     * @param words array of input words
     * @return list of comma-separated anagram groups, sorted lexicographically
     */
    public static List<String> groupAnagrams(String[] words) {
        Map<String, List<String>> groups = new TreeMap<>();

        for (String word : words) {
            String signature = getAnagramSignature(word);
            groups.computeIfAbsent(signature, k -> new ArrayList<>()).add(word);
        }

        return groups.values().stream()
                .map(group -> {
                    Collections.sort(group);
                    return String.join(",", group);
                })
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Produces a canonical signature for anagram detection by:
     * 1. Removing all spaces
     * 2. Converting to lowercase
     * 3. Sorting the characters
     *
     * Two words are anagrams if and only if they have the same signature.
     */
    static String getAnagramSignature(String input) {
        char[] chars = input.replaceAll("\\s+", "").toLowerCase().toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
