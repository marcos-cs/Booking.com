package com.booking;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        String positiveKeywords = "breakfast beach citycenter location metro view staff price";
        String negativeKeywords = "not";
        int[] hotelsId = new int[] {1,5,3,4,2};
        List<String> reviews = new ArrayList<String>();
        reviews.add("This hotel has a nice view of the citycenter. The location is perfect");
        reviews.add("The breakfast is ok. Regarding location, it is quite far from citycenter but price is cheap so it is worth");
        reviews.add("location is excelent, 5 minutes from citycenter. There is also a metro station very close to the hotel");
        reviews.add("they said i couldn't take my doc and there were other with dogs! that is not fair");
        reviews.add("very friendly staff and good cost-benefit ratio. its location is a bit far from citycenter");
        int k = 2;

        List<Integer> top = awardTopKHotels(positiveKeywords, negativeKeywords, hotelsId, reviews, k);

        System.out.println(top);

    }


    public static List<Integer> awardTopKHotels(String positiveKeywords, String negativeKeywords, int[] hotelsIds, List<String> reviews, int k) {

        List<Integer> top = new ArrayList<>();
        if (hotelsIds.length==0 || reviews.size()==0) {
            return top;
        }

        HashMap<String, List<Integer>> positiveMap = new HashMap<>();
        HashMap<String, List<Integer>> negativeMap = new HashMap<>();

        convertToMap(positiveKeywords, negativeKeywords, positiveMap, negativeMap);

        analyzeHotels(hotelsIds, reviews, positiveMap, negativeMap);

        HashMap<Integer, Integer> hotels = calcHotelsScore(k, positiveMap, negativeMap);

        Hotel[] listHotels = getHotelsSorted(hotels);
        System.out.println(Arrays.toString(listHotels));
        for (int i = 0; i<listHotels.length; i++) {
            if (i<k || (i > 0 && listHotels[i].count == listHotels[i-1].count )) {
                top.add(listHotels[i].id);
            }

        }

        return top;
    }

    private static Hotel[] getHotelsSorted(HashMap<Integer, Integer> hotels) {
        Hotel[] listHotels = new Hotel[hotels.size()];

        Iterator<Integer> it= hotels.keySet().iterator();
        int pos = 0;
        while (it.hasNext()) {
            Integer key = it.next();
            Integer val =  hotels.get(key);
            listHotels[pos] = new Hotel(key, val);
            pos++;
        }

        Arrays.sort(listHotels);
        return listHotels;
    }


    private static HashMap<Integer, Integer> calcHotelsScore(int k, HashMap<String, List<Integer>> positiveMap, HashMap<String, List<Integer>> negativeMap) {
        HashMap<Integer, Integer> hotels = new HashMap<>();

        Iterator<String> it = positiveMap.keySet().iterator();
        while (it.hasNext()) {
            String word = it.next();
            List<Integer> hotelsId = positiveMap.get(word);

            if (hotelsId.size()>0) {
                hotelsId.forEach((h) -> {
                    if (hotels.containsKey(h)) {
                        hotels.put(h, hotels.get(h)+3);
                    } else {
                        hotels.put(h, 3);
                    }
                });
            }

        }


        negativeMap.forEach((word, hotelsId) -> {
            if (hotelsId.size()>0) {
                hotelsId.forEach((h) -> {
                    if (hotels.get(h)!=null) {
                        hotels.put(h, hotels.get(h)-1);
                    } else {
                        hotels.put(h, -1);
                    }
                });
            }
        });
        return hotels;
    }

    private static void analyzeHotels(int[] hotelsIds, List<String> reviews, HashMap<String, List<Integer>> positiveMap, HashMap<String, List<Integer>> negativeMap) {
        Iterator<String> it = reviews.iterator();
        int pos = -1;
        while (it.hasNext()) {
            pos++;
            String review = it.next();

            review = review.toLowerCase().replaceAll("[,.]", "");

            String[] words =  review.toLowerCase().split(" ");
            for (int i = 0; i< words.length; i++) {
                if (positiveMap.containsKey(words[i])) {
                    positiveMap.get(words[i]).add(hotelsIds[pos]);
                }
                if (negativeMap.containsKey(words[i])) {
                    negativeMap.get(words[i]).add(hotelsIds[pos]);
                }
            }

        }
    }

    private static void convertToMap(String positiveKeywords, String negativeKeywords, HashMap<String, List<Integer>> positiveMap, HashMap<String, List<Integer>> negativeMap) {
        String[] p = positiveKeywords.split(" ");
        String[] n = negativeKeywords.split(" ");

        for (int i = 0; i<p.length; i++) {
            positiveMap.put(p[i], new LinkedList<>());
        }
        for (int i = 0; i<n.length; i++) {
            negativeMap.put(p[i], new LinkedList<>());
        }
    }

    static class Hotel implements Comparable<Hotel> {

        private int id;
        private int count;

        public Hotel(int id, int count) {
            this.id = id;
            this.count = count;
        }

        public int getCount() {
            return count;
        }

        public void addCount(int val) {
            count+=val;
        }

        @Override
        public int compareTo(Hotel o) {
            return o.getCount() - this.getCount();
        }

        @Override
        public String toString() {
            return "Hotel{" +
                    "id=" + id +
                    ", count=" + count +
                    '}';
        }
    }

}

