package pl.suseu.aoc2019.day1;

import pl.suseu.aoc2019.FileUtils;

import java.util.List;

public class Day1 {

    private List<Integer> masses;

    public void firstPart(){
        masses = FileUtils.loadFileAsIntegerList("day1");
        int sum = masses.stream().mapToInt(i -> i / 3 - 2).sum();
        System.out.println(sum);
    }

}
