package pl.suseu.aoc2019;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    private static File getFileFromResources(String fileName) {
        ClassLoader classLoader = FileUtils.class.getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }

    }

    public static List<String> loadFile(String name) {
        try {
            return Files.readAllLines(getFileFromResources(name).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static List<Integer> loadFileAsIntegerList(String name){
        List<String> list = loadFile(name);
        List<Integer> newList = new ArrayList<>();
        list.forEach(s -> newList.add(Integer.parseInt(s)));
        return newList;
    }
}
