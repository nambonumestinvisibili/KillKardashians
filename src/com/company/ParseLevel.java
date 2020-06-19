package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ParseLevel {

    public static int level = 0;
    //public int level = 0;

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

    public static void createAllGameObjects(GamePanel panel) throws FileNotFoundException {

        ArrayList<ArrayList<Integer>> leveldata = ParseLevel.parseLevel(level);
        int row = leveldata.size();
        int col = leveldata.get(0).size();

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){

                if (leveldata.get(i).get(j) == 1){
                    panel.walls.add(new Wall(j*50, i*50, 50, 50));
                }
                if (leveldata.get(i).get(j) == 2){
                    panel.obstacles.add(new ObstacleLow(j*50, i*50, 50, 50, panel));
                }
                if (leveldata.get(i).get(j) == 4){
                    panel.obstacles.add(new ObstacleMedium(j*50, i*50, 50, 50, panel));
                }
                if (leveldata.get(i).get(j) == 5){
                    panel.obstacles.add(new ObstacleHigh(j*50, i*50, 50, 50, panel));
                }

            }
        }

    }

}
