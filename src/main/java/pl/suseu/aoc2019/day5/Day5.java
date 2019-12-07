package pl.suseu.aoc2019.day5;

import pl.suseu.aoc2019.FileUtils;
import pl.suseu.aoc2019.intcode.emulator.IntCodeEmulator;

import java.util.ArrayList;
import java.util.List;

public class Day5 {

    private List<Integer> program = FileUtils.loadFileAsIntegerListBySplittingALongStringLikeThisFunctionName("day5", ",");

    public void firstPart(){
        System.out.println(new IntCodeEmulator.Builder().setMemory(new ArrayList<>(program)).addInputs(1).build().run().getLastOutput());
    }

    public void secondPart(){
        System.out.println(new IntCodeEmulator.Builder().setMemory(new ArrayList<>(program)).addInputs(5).build().run().getLastOutput());
    }
}
