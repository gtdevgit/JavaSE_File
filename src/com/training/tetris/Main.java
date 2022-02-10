package com.training.tetris;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    // "c:\temp\file.txt"
    // ".\fichier\file.txt"
    // private static String pathname;

    private static Map<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {
	    // write your code here
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Working Directory = " + System.getProperty("user.dir"));
            System.out.println("Enter file patch or enter to use default file path (try '.\\fichier\\file.txt'): .");

            String pathname = scanner.nextLine();
            System.out.println(pathname);

            if (pathname.isEmpty()) {
                // default file path
                pathname = ".\\fichier\\file.txt";
            }

            File file = new File(pathname);
            if (!file.exists()) {
                throw new FileNotFoundException(file.getPath());
            }

            System.out.println(String.format("File \"%s\" was found", file.getAbsolutePath()));

            // read file
            try (Reader fileReader = new FileReader(pathname)) {
                int data = fileReader.read();
                while(data != -1) {
                    countCaractere(data);
                    data = fileReader.read();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        afficher();
    }

    private static void countCaractere(int data){
        System.out.println(data);
        Character c = (char) data;
        try {
            int count = map.get(c);
            count++;
            map.put(c, count);
        }
        catch (NullPointerException e) {
            map.put(c, 1);
        }
    }

    private static void afficher(){
/*        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }*/
        List<Character> sortedKeys = map.keySet().stream().sorted().toList();
        for (Character c : sortedKeys) {
            System.out.println(c + "\t (unicode: " + (int) c + ") :\t" + map.get(c));
        }
    }
}
