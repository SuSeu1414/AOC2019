package pl.suseu.aoc2019.day2;

import pl.suseu.aoc2019.FileUtils;
import pl.suseu.aoc2019.intcode.emulator.IntCodeEmulator;

import java.util.ArrayList;
import java.util.List;

public class Day2 {

    private List<Integer> opcodes;

    public void firstPart() {
        opcodes = FileUtils.loadFileAsIntegerListBySplittingALongStringLikeThisFunctionName("day2", ",");
        System.out.println(new IntCodeEmulator.Builder()
                .setMemory(new ArrayList<>(opcodes))
                .withNoun(12)
                .withVerb(2)
                .build()
                .run()
                .getMemory().get(0));
    }

    public void secondPart() {
        opcodes = FileUtils.loadFileAsIntegerListBySplittingALongStringLikeThisFunctionName("day2", ",");
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                int output = new IntCodeEmulator.Builder()
                        .setMemory(new ArrayList<>(opcodes))
                        .withNoun(i)
                        .withVerb(j)
                        .build()
                        .run()
                        .getMemory().get(0);

                if (output == 19690720) {
                    System.out.println("noun = " + i);
                    System.out.println("verb = " + j);
                    System.out.println(100*i + j);
                    System.exit(-1);
                }
            }
        }
        System.exit(1);
    }

}
