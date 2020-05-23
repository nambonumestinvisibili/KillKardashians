package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ParseLevel {

    static ArrayList<String> levelPaths = new ArrayList<>();

    public static void getLevels(){
        File folder = new File("resources\\levels\\");
        File[] listOfFiles = folder.listFiles();

        assert listOfFiles != null;
        for (File file : listOfFiles){
            levelPaths.add(file.getAbsolutePath());
        }
    }

    public static ArrayList<ArrayList<Integer>> parseLevel(int level) throws FileNotFoundException {

        File leveldata =  new File(levelPaths.get(level));
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
