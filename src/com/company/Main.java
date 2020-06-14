package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.String;
import java.util.*;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {
        Scanner scn = new Scanner(System.in);
        System.out.println("Введите название файла: ");

        String fileName = scn.nextLine();
        File file = new File(fileName);
        Scanner scn1 = new Scanner(file);
        String text = scn1.nextLine();

        //Java-map
        HashMap<String, Integer> map = new HashMap<>();
        Implementation_JavaMap.findAbbreviationsInMap(text, map);

        for (Map.Entry<String, Integer> pair : map.entrySet()) {
            System.out.println(pair.getKey() + "=" + pair.getValue());
        }

        System.out.println("\n");

        //Собственная map
        NewMap<String, Integer> mp = new NewMap<>();
        Implementation_NewMap.findAbbreviationsInNewMap(text, mp);

        for (int i = 0; i < mp.size(); i++) {
            System.out.println(mp.getPair(i));
        }


    }
}
