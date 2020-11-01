package com.booking;

import java.util.*;

public class AwardTopKHotels {
    public static void main(String[] args) {

        String positiveKeywords = "breakfast beach citycenter location metro view staff price";
        String negativeKeywords = "not";
        int[] hotelsId = new int[] {1,2,1,1,2};
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

        HashSet<String> positiveMap = convertToSet(positiveKeywords);
        HashSet<String> negativeMap = convertToSet(negativeKeywords);

        HashMap<Integer, Integer> hotelsSum = analyzeHotels(hotelsIds, reviews, positiveMap, negativeMap);

        Hotel[] listHotels = getHotelsSorted(hotelsSum);

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

    private static HashMap<Integer, Integer> analyzeHotels(int[] hotelsIds, List<String> reviews, HashSet<String> positiveMap, HashSet<String> negativeMap) {
        Iterator<String> it = reviews.iterator();
        HashMap<Integer, Integer> hotelsSum = new HashMap<>();
        int pos = -1;
        while (it.hasNext()) {
            pos++;
            String review = it.next();

            String[] words = review.toLowerCase().replaceAll("[,.]", "").split(" ");

            for (int i = 0; i< words.length; i++) {
                if (positiveMap.contains(words[i])) {
                    if (hotelsSum.containsKey(hotelsIds[pos])) {
                        hotelsSum.put(hotelsIds[pos], hotelsSum.get(hotelsIds[pos]) + 3);
                    } else {
                        hotelsSum.put(hotelsIds[pos],  3);
                    }

                }
                if (negativeMap.contains(words[i])) {
                    if (hotelsSum.containsKey(hotelsIds[pos])) {
                        hotelsSum.put(hotelsIds[pos], hotelsSum.get(hotelsIds[pos]) + 1);
                    } else {
                        hotelsSum.put(hotelsIds[pos],  -1);
                    }
                }
            }

        }
        return hotelsSum;
    }

    private static HashSet<String> convertToSet(String keywords) {
        String[] p = keywords.split(" ");
        HashSet<String> keywordSet = new HashSet<>();
        for (int i = 0; i<p.length; i++) {
            keywordSet.add(p[i]);
        }
        return keywordSet;

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

