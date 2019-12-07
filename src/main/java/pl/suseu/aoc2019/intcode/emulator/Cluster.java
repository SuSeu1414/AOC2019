package pl.suseu.aoc2019.intcode.emulator;

import java.util.ArrayList;
import java.util.List;

public class Cluster {

    private List<IntCodeEmulator> emulators = new ArrayList<>();
    private List<Integer> options;
    private int lastOutput = 0;

    public Cluster(int amount, List<Integer> options, List<Integer> program) {
        this.options = options;
        for (int i = 0; i < amount; i++) {
            emulators.add(new IntCodeEmulator
                    .Builder()
                    .setMemory(new ArrayList<>(program))
                    .setHaltOnOutput(true)
                    .build());
        }
    }

    public int run() {
//        System.out.println("Running a cluster...");
//        long start = System.currentTimeMillis();
//        options.forEach(System.out::println);
        lastOutput = 0;

        loop:
        while (true) {
            for (int i = 0; i < emulators.size(); i++) {
                IntCodeEmulator emulator = emulators.get(i);
                if (options != null) {
                    emulator.getInputs().add(options.get(i));
                }
                emulator.getInputs().add(lastOutput);
//            System.out.println("Running emulator(" + i + ")...");
                lastOutput = emulator.run().getLastOutput();
                if (emulator.isFinished()) {
                    break loop;
                }
            }
            options = null;
        }
//        System.out.println("Done in " + (System.currentTimeMillis() - start) + "ms.");
        return emulators.get(emulators.size() - 1).getLastOutput();
    }
}
