package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ParseLevel {

    public static ArrayList<ArrayList<Integer>> parseLevel(String fileName) throws FileNotFoundException {

        File leveldata =  new File(fileName);
        Scanner scanner = new Scanner(leveldata);

        ArrayList<ArrayList<Integer>> splitList = new ArrayList<>();

        while (scanner.hasNext()){
            String[] temp = scanner.nextLine().split(";");
            ArrayList<Integer> aux = new ArrayList<>();
            for (String s : temp){
                aux.add(Integer.parseInt(s));
            }
            splitList.add(aux);
        }

        return splitList;
    }

}
