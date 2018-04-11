package com.tuco.server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class CountriesConstants {
    private static List<String> getCountriesFromResources() {
        List<String> result = new ArrayList<String>();
//        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//        Stream<String> inputStream = (Stream<String>) classloader.getResourceAsStream("countries");
//        inputStream.forEach(result::add);

        ClassLoader classLoader = CountriesConstants.class.getClassLoader();
        File file = new File(classLoader.getResource("countries").getFile());

        try (Stream<String> stream = Files.lines(Paths.get(file.getPath()))) {
            stream.forEach(result::add);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getCountry() {
        List<String> countries = getCountriesFromResources();
        int index = new Random().nextInt(countries.size());
        return countries.get(index);
    }
}