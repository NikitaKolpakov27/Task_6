package com.company;

import java.util.Map;

class Implementation_NewMap {
    static void findAbbreviationsInNewMap(String line, NewMap<String, Integer> map) {
        String[] words = line.split("[ ,.:()]");

        for (int i = 0; i < words.length; i++) {
            int count = 1;

            for (int j = i + 1; j < words.length; j++) {
                if (words[i].equals(words[j])) {
                    count++;
                }
            }

            if (countLetters(words[i], map) == 0) {
                map.put(words[i], count);
            }
        }
    }

    private static int countLetters(String str, NewMap<String, Integer> map) {
        if (str.length() > 5 || str.length() == 1 || str.length() == 0) {
            return -1;
        }

        for (int i = 0; i < map.size(); i++) {
            if (str.equals(map.getKey(i))) {
                return -1;
            }
        }

        int count = 0;
        char[] array = str.toCharArray();

        for (char x : array) {
            if (Character.isUpperCase(x)) {
                count++;
            }
        }

        if (str.length() == count) {
            return 0;
        } else {
            return -1;
        }
    }
}
