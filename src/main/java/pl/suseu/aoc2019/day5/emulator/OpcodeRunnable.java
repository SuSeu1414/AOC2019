package pl.suseu.aoc2019.day5.emulator;

import pl.suseu.aoc2019.day5.emulator.Argument;
import pl.suseu.aoc2019.day5.emulator.Emulator;

public interface OpcodeRunnable {

    void run(Emulator emulator, Argument[] args);
}
