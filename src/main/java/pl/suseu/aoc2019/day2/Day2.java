package pl.suseu.aoc2019.day2;

import pl.suseu.aoc2019.FileUtils;

import java.util.List;

public class Day2 {

    private List<Integer> opcodes;
    private int pc = 0;

    public void firstPart() {
        opcodes = FileUtils.loadFileAsIntegerListBySplittingALongStringLikeThisFunctionName("day2", ",");
        opcodes.set(1, 12);
        opcodes.set(2, 2);
        while (true) {
            int op = opcodes.get(pc);
            System.out.println("op = " + op);
            switch (op) {
                case 1: {
                    int sum = opcodes.get(opcodes.get(pc + 1)) + opcodes.get(opcodes.get(pc + 2));
                    int pos = opcodes.get(pc + 3);
                    opcodes.set(pos, sum);
                    break;
                }
                case 2: {
                    int product = opcodes.get(opcodes.get(pc + 1)) * opcodes.get(opcodes.get(pc + 2));
                    int pos = opcodes.get(pc + 3);
                    opcodes.set(pos, product);
                    break;
                }
                case 99:
                    System.out.println("Program finished with code " + opcodes.get(0));
                    System.exit(-1);
                default:
                    System.out.println("Something went completely wrong, fix your code!");
                    System.exit(1);
            }

            pc += 4;
        }
    }

}
