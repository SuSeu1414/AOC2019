package pl.suseu.aoc2019.day5.computer.opcodes;

import pl.suseu.aoc2019.day5.computer.Argument;
import pl.suseu.aoc2019.day5.computer.Emulator;

public interface OpcodeRunnable {

    void run(Emulator emulator, Argument[] args);
}
