package fr.diginamic.bll;

import au.com.bytecode.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFileManager {
    private final static String RESOURCES_PATH = "src/main/resources/";
    private final static String CSV_PATH = "open-food-facts.csv";
    private final static char SEPARATOR = '|';

    public static List<String[]> readFile() throws IOException {
        File file = new File(RESOURCES_PATH + CSV_PATH);
        FileReader fr = new FileReader(file);
        CSVReader csvReader = new CSVReader(fr, SEPARATOR);

        List<String[]> data = new ArrayList<>();

        String[] nextLine;
        while ((nextLine = csvReader.readNext()) != null) {
            int size = nextLine.length;

            // ligne vide
            if (size == 0) {
                continue;
            }
            String debut = nextLine[0].trim();
            if (debut.length() == 0 && size == 1) {
                continue;
            }

            // ligne de commentaire
            if (debut.startsWith("#")) {
                continue;
            }
            data.add(nextLine);
        }

        return data;
    }
}
