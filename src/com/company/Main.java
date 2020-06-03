package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.lang.String;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {
        Scanner scn = new Scanner(System.in);
        System.out.println("Введите название файла: ");

        String fileName = scn.nextLine();
        File file = new File(fileName);
        Scanner scn1 = new Scanner(file);
        String line = scn1.nextLine();

        //Java-map
        HashMap<String, Integer> map = new HashMap<>();
        Task.execute(line, map);

        for (Map.Entry<String, Integer> pair : map.entrySet()) {
            System.out.println(pair.getKey() + "=" + pair.getValue());
        }

        System.out.println("\n");

        //Собственная map
        MyOwnMap<String, Integer> newMap = new MyOwnMap<>(100);
        Task.execute(line, newMap);

        for (Map.Entry<String, Integer> pair : newMap.entrySet()) {
            System.out.println(pair.getKey() + "=" + pair.getValue());
        }



    }
}
