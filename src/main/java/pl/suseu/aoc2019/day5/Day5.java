package pl.suseu.aoc2019.day5;

import pl.suseu.aoc2019.FileUtils;
import pl.suseu.aoc2019.intcode.emulator.IntCodeEmulator;

import java.util.List;

public class Day5 {

    private List<Integer> program = FileUtils.loadFileAsIntegerListBySplittingALongStringLikeThisFunctionName("day5", ",");

    public void firstPart(){
        int[] memory = program.stream().mapToInt(i -> i).toArray();
        System.out.println(new IntCodeEmulator.Builder().setMemory(memory).addInputs(1).build().run().getLastOutput());
    }

    public void secondPart(){
        int[] memory = program.stream().mapToInt(i -> i).toArray();
        System.out.println(new IntCodeEmulator.Builder().setMemory(memory).addInputs(5).build().run().getLastOutput());
    }
}
