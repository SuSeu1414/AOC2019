package pl.suseu.aoc2019.day5.computer;

import pl.suseu.aoc2019.day5.computer.opcodes.OpcodeRunnable;

public enum Opcode {

    ERROR(-1, 0, (emulator, args) -> {
        System.out.println("ERROR");
        System.exit(1);
    }),

    ADD(1, 3, (emulator, args) -> {
        int pos = args[2].getValue();
        int sum = emulator.getValueFromMemory(args[0]) + emulator.getValueFromMemory(args[1]);
        emulator.getMemory()[pos] = sum;
//
//        System.out.println("ADD pos=" + pos + ", sum=" + sum);
    }),

    MULTIPLY(2, 3, (emulator, args) -> {
        int pos = args[2].getValue();
        int product = emulator.getValueFromMemory(args[0]) * emulator.getValueFromMemory(args[1]);
        emulator.getMemory()[pos] = product;
//
//        System.out.println("MULTIPLY pos=" + pos + ", product=" + product);
    }),

    INPUT(3, 1, (emulator, args) -> {
        int pos = args[0].getValue();
        emulator.getMemory()[pos] = emulator.getInput();
    }),

    OUTPUT(4, 1, (emulator, args) -> {
        System.out.println("OUTPUT: " + emulator.getValueFromMemory(args[0]));
    }),

    HALT(99, 0, (emulator, args) -> {
        System.out.println("Finished with code " + emulator.getMemory()[0]);
        System.exit(-1);
    });



    final int op;
    final int argsLength;
    final OpcodeRunnable runnable;

    Opcode(int op, int argsLength, OpcodeRunnable runnable) {
        this.op = op;
        this.argsLength = argsLength;
        this.runnable = runnable;
    }

    public void run(Emulator emulator, Argument[] args) {
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
