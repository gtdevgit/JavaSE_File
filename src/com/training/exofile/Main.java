package com.training.exofile;

import java.io.*;
import java.util.*;

public class Main {

    // "c:\temp\file.txt"
    // ".\fichier\file.txt"
    // private static String pathname;

    private static Map<Character, Integer> map = new TreeMap<>();

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
        int count = map.getOrDefault(c, 0);
        count++;
        map.put(c, count);
    }

    private static void afficher(){
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "\t (unicode: " + (int) entry.getKey() + ") :\t" + entry.getValue());
        }
    }
}
