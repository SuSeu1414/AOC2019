package pl.suseu.aoc2019.day5.emulator.opcodes;

import pl.suseu.aoc2019.day5.emulator.Argument;
import pl.suseu.aoc2019.day5.emulator.Emulator;

public interface OpcodeRunnable {

    void run(Emulator emulator, Argument[] args);
}
