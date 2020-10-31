package com.booking;

import java.util.*;

public class Anagram {
    public static void main(String[] args) {

        String[] words = new String[]{"pear", "amleth", "dormitory", "tinsel", "dirtyroom", "hamlet", "listen", "silnet"};

        HashMap<Integer, List<String>> result = new HashMap<>();

        for (int i = 0; i<words.length; i++) {
            int sum = sumWord(words[i]);
            if (result.containsKey(sum)) {
                result.get(sum).add(words[i]);
            } else {
                List<String> listWords = new LinkedList<>();
                listWords.add(words[i]);
                result.put(sum, listWords);
            }
        }

        result.forEach((k,v) -> {
            if (v.size()>1) {
                System.out.println(v);
            }
        });

    }

    private static int sumWord(String input) {
        String word = input.replaceAll(" |[,|.]", "");
        int[] iWord = new int[26];
        for (int i = 0; i< word.length(); i++) {
            char w = word.charAt(i);
            iWord[(int)w-97]+=(int)w-97;
        }
        int preVal = iWord[0]+1;
        for (int i = 1; i< iWord.length; i++) {
            preVal+=iWord[i];
        }
        return preVal;
    }


}
