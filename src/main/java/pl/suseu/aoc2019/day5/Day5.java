package pl.suseu.aoc2019.day5;

import pl.suseu.aoc2019.FileUtils;
import pl.suseu.aoc2019.day5.computer.Emulator;
import pl.suseu.aoc2019.day5.util.Utils;

import java.util.List;

public class Day5 {

    private List<Integer> program = FileUtils.loadFileAsIntegerListBySplittingALongStringLikeThisFunctionName("day5", ",");

    public void firstPart(){
        new Emulator(program.stream().mapToInt(i -> i).toArray()).run(1);
    }

    public void secondPart(){
        new Emulator(program.stream().mapToInt(i -> i).toArray()).run(5);
    }
}
