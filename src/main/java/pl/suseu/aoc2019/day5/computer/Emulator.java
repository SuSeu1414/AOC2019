package pl.suseu.aoc2019.day5.computer;

import pl.suseu.aoc2019.day5.util.Utils;

public class Emulator {

    private int[] memory;
    private int pc;
    private int input;

    public Emulator(int[] memory) {
        pc = 0;
        this.memory = memory;
    }

    public void run(int input) {
        this.input = input;
        while (true) {
            fetchOpcode();
        }
    }

    public void fetchOpcode() {
//        System.out.println("pc = " + pc);

        int opcode = memory[pc];
        int op = 10 * Utils.getDigit(opcode, 2) + Utils.getDigit(opcode, 1);
        int[] modes = getModes(opcode);

//        System.out.println("opcode = " + opcode);
//        System.out.println("op = " + op);

        Opcode opc = Opcode.get(op);

        Argument[] args = new Argument[opc.getArgsLength()];
        for (int i = 1; i <= opc.getArgsLength(); i++) {
            Argument arg = new Argument(memory[pc + i], modes[i]);
            args[i - 1] = arg;

//            System.out.println("args[" + (i - 1) + "]=" + arg.getValue()
//                    + "(mode=" + arg.getMode() + ") {" + getValueFromMemory(arg) + "}");
        }

        pc += (args.length + 1);

        opc.run(this, args);
//        System.out.println();
    }

    public int getValueFromMemory(Argument arg) {
        return getValueFromMemory(arg.getValue(), arg.getMode());
    }

    public int getValueFromMemory(int val, int mode) {
        return mode == 0 ? memory[val] : val;
    }

    private static int[] getModes(int opcode) {
        int[] modes = new int[4];
        modes[0] = -1;
        modes[1] = Utils.getDigit(opcode, 3);
        modes[2] = Utils.getDigit(opcode, 4);
        modes[3] = Utils.getDigit(opcode, 5);

        return modes;
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public int[] getMemory() {
        return memory;
    }

    public int getInput() {
        return input;
    }
}
