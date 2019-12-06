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
        loadInput();
        List<String> you = getAllIndirectOrbits("YOU");
        List<String> san = getAllIndirectOrbits("SAN");
        String meetAt = "";

        for (String s : san) {
            if (you.contains(s)) {
                meetAt = s;
                break;
            }
        }

        int dis = countOrbits("YOU" , meetAt) + countOrbits("SAN", meetAt) - 2;
        System.out.println(dis);
    }

    public List<String> getAllIndirectOrbits(String planet) {
        if (planet.equals("COM")) return new ArrayList<>();

        List<String> orbs = new ArrayList<>();

        new HashMap<>(orbits).forEach((key, value) -> {
            if (value.contains(planet))
                orbs.add(key);
        });

        if (orbs.isEmpty()) return orbs;

        String pl = orbs.get(0);
        orbs.addAll(getAllIndirectOrbits(pl));
        return orbs;
    }

    public int countOrbits(String id, String planet) {
        if (orbits.get(planet).contains(id)) return 1;

        for (Map.Entry<String, List<String>> entry : orbits.entrySet()){
            if (entry.getValue().contains(id)) return countOrbits(entry.getKey(), planet) + 1;
        }

        return 1;
    }
}
