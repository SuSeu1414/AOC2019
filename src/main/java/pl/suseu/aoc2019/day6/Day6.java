package pl.suseu.aoc2019.day6;

import pl.suseu.aoc2019.FileUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Day6 {

    private Map<String, List<String>> orbits = new HashMap<>();

    private void loadInput(){
        List<String> input = FileUtils.loadFile("day6");

        input.forEach(s -> {
            String planet1 = s.split("\\)")[0];
            String planet2 = s.split("\\)")[1];
            List<String> planets = orbits.get(planet1);
            if (planets == null) planets = new ArrayList<>();

            planets.add(planet2);
            orbits.put(planet1, planets);
        });
    }

    public void firstPart() {
        loadInput();

        AtomicInteger count = new AtomicInteger();

        new HashMap<>(orbits).forEach((key, value) -> {
            value.forEach(s -> {
                count.addAndGet(countOrbits(s, "COM"));
            });
        });

        System.out.println(count);
    }

    public void secondPart() {

    }

    public int countOrbits(String id, String planet) {
        if (orbits.get(planet).contains(id)) return 1;

        for (Map.Entry<String, List<String>> entry : orbits.entrySet()){
            if (entry.getValue().contains(id)) return countOrbits(entry.getKey(), planet) + 1;
        }

        return 1;
    }
}
