package com.tuco.station;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class CountriesConstants {
    private static List<String> countries = getCountriesFromResources();

    private static List<String> getCountriesFromResources() {
        List<String> result = new ArrayList<>();

        File file = new File("src/main/resources/countries");

        try (Stream<String> stream = Files.lines(Paths.get(file.getPath()))) {
            stream.forEach(result::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getCountry() {
        int index = new Random().nextInt(countries.size());
        String country = countries.get(index);
        countries.remove(index);
        return country;
    }
}