package pl.suseu.aoc2019.intcode.emulator;

public interface OpcodeRunnable {

    void run(IntCodeEmulator emulator, Argument[] args);
}
