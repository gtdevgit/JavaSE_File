package com.training.tetris;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    // "c:\temp\file.txt"
    // ".\fichier\file.txt"
    // private static String pathname;

    private static Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    public static void main(String[] args) throws FileNotFoundException {
	    // write your code here
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Working Directory = " + System.getProperty("user.dir"));
            System.out.println("Enter file patch or enter to use default file path (try '.\\fichier\\file.txt'): .");

            String pathname = scanner.nextLine();
            System.out.println(pathname);

            if (pathname.isEmpty())
                // default file path
                pathname = ".\\fichier\\file.txt";

            File file = new File(pathname);
            if (!file.exists())
                throw new FileNotFoundException(file.getPath());

            System.out.println(String.format("File \"%s\" was found", file.getAbsolutePath()));

            // read file
            Reader fileReader = new FileReader(pathname);
            try {
                try {
                    int data = fileReader.read();
                    while(data != -1) {
                        //do something with data...
                        countCaractere(data);

                        data = fileReader.read();
                    }
                }
                finally {
                    fileReader.close();
                }
            }
            catch (java.io.IOException e){
                System.err.println(e.getMessage());
            }
        }
        finally {
            scanner.close();
        }

        afficher();
    }

    private static void countCaractere(int data){
        System.out.println(data);

        char c = (char) data;
        if (map.isEmpty()) {
            map.put(data, 1);
            return;
        }

        Integer count = map.get(data);
        if (count == null) {
            map.put(data, 1);
        } else {
            count++;
            map.replace(data, count);
        }
    }

    private static void afficher(){
        for (Map.Entry m : map.entrySet()) {
            System.out.println(m.getKey() + " => " + m.getValue());
            try {
                // m.getKey est un objet, il faut le récupérer en tant que int avant de le caster en char.
                char c = (char) (int) m.getKey();
                System.out.println(m.getKey() + " => " + m.getValue());
            }
            catch (ClassCastException e){
                System.err.println("***" + m.getKey() +"***");
            }
        }
    }
}
