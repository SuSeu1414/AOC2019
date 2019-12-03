package pl.suseu.aoc2019.day2;

import pl.suseu.aoc2019.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class Day2 {

    private List<Integer> opcodes;

    public void firstPart() {
        opcodes = FileUtils.loadFileAsIntegerListBySplittingALongStringLikeThisFunctionName("day2", ",");
        System.out.println(runProgram(12, 2, new ArrayList<>(opcodes)));
    }

    public void secondPart() {
        opcodes = FileUtils.loadFileAsIntegerListBySplittingALongStringLikeThisFunctionName("day2", ",");
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                int output = runProgram(i, j, new ArrayList<>(opcodes));
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

    private int runProgram(int noun, int verb, List<Integer> memory) {
        int pc = 0;
        memory.set(1, noun);
        memory.set(2, verb);
        while (true) {
            int op = memory.get(pc);
            switch (op) {
                case 1: {
                    int sum = memory.get(memory.get(pc + 1)) + memory.get(memory.get(pc + 2));
                    int pos = memory.get(pc + 3);
                    memory.set(pos, sum);
                    break;
                }
                case 2: {
                    int product = memory.get(memory.get(pc + 1)) * memory.get(memory.get(pc + 2));
                    int pos = memory.get(pc + 3);
                    memory.set(pos, product);
                    break;
                }
                case 99:
                    System.out.println("Program finished with code " + memory.get(0));
                    return memory.get(0);
                default:
                    System.out.println("Something went completely wrong, fix your code!");
                    System.exit(1);
            }

            pc += 4;
        }
    }

}
