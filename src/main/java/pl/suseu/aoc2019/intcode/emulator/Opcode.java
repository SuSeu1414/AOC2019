package pl.suseu.aoc2019.intcode.emulator;

public enum Opcode {

    ERROR(-1, 0, (emulator, args) -> {
        System.out.println("ERROR");
//        emulator.setRunning(false);
//        System.exit(1);
    }),

    ADD(1, 3, (emulator, args) -> {
        int pos = args[2].getValue();
        int lol= emulator.getValueFromMemory(args[0]);
        int lol2= emulator.getValueFromMemory(args[1]);
        int sum = emulator.getValueFromMemory(args[0]) + emulator.getValueFromMemory(args[1]);
        emulator.setMemoryValue(pos, sum);
//
//        System.out.println("ADD pos=" + pos + ", sum=" + sum);
    }),

    MULTIPLY(2, 3, (emulator, args) -> {
        int pos = args[2].getValue();
        int product = emulator.getValueFromMemory(args[0]) * emulator.getValueFromMemory(args[1]);
        emulator.setMemoryValue(pos, product);
//
//        System.out.println("MULTIPLY pos=" + pos + ", product=" + product);
    }),

    INPUT(3, 1, (emulator, args) -> {
        int pos = args[0].getValue();
        emulator.setMemoryValue(pos, emulator.getInputs().remove(0));
    }),

    OUTPUT(4, 1, (emulator, args) -> {
        emulator.getOutputs().add(emulator.getValueFromMemory(args[0]));
        emulator.setRunning(false);
//        System.out.println("OUTPUT: " + emulator.getValueFromMemory(args[0]));
    }),

    JUMP_IF_TRUE(5, 2, (emulator, args) -> {
        boolean condition = emulator.getValueFromMemory(args[0]) != 0;
        if(condition)
            emulator.setPc(emulator.getValueFromMemory(args[1]));
    }),

    JUMP_IF_FALSE(6, 2, (emulator, args) -> {
        boolean condition = emulator.getValueFromMemory(args[0]) == 0;
        if(condition)
            emulator.setPc(emulator.getValueFromMemory(args[1]));
    }),

    LESS_THAN(7, 3, (emulator, args) -> {
        int first = emulator.getValueFromMemory(args[0]);
        int second = emulator.getValueFromMemory(args[1]);
        int pos = args[2].getValue();

        emulator.setMemoryValue(pos, first < second ? 1 : 0);
    }),

    EQUALS(8, 3, (emulator, args) -> {
        int first = emulator.getValueFromMemory(args[0]);
        int second = emulator.getValueFromMemory(args[1]);
        int pos = args[2].getValue();

        emulator.setMemoryValue(pos, first == second ? 1 : 0);
    }),

    HALT(99, 0, (emulator, args) -> {
//        System.out.println("Finished with code " + emulator.getMemory()[0]);
//        System.exit(-1);
        System.out.println("HALT");
        emulator.setRunning(false);
        emulator.setFinished(true);
    });



    final int op;
    final int argsLength;
    final OpcodeRunnable runnable;

    Opcode(int op, int argsLength, OpcodeRunnable runnable) {
        this.op = op;
        this.argsLength = argsLength;
        this.runnable = runnable;
    }

    public void run(IntCodeEmulator emulator, Argument[] args) {
        runnable.run(emulator, args);
    }

    public static Opcode get(int id) {
        for (Opcode opc : Opcode.values()) {
            if(opc.getOp() == id) return opc;
        }
        return Opcode.ERROR;
    }

    public int getArgsLength() {
        return argsLength;
    }

    public int getOp() {
        return op;
    }

    public OpcodeRunnable getRunnable() {
        return runnable;
    }


}
