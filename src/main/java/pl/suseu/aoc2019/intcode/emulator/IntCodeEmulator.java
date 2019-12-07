package pl.suseu.aoc2019.intcode.emulator;

import pl.suseu.aoc2019.intcode.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class IntCodeEmulator {

    private boolean running;
    private int pc;
    private boolean haltOnOutput;
    private boolean finished;
    private ArrayList<Integer> memory;
    private List<Integer> inputs;
    private List<Integer> outputs = new ArrayList<>();

    public IntCodeEmulator(ArrayList<Integer> memory, List<Integer> inputs, boolean haltOnOutput) {
        pc = 0;
        this.haltOnOutput = haltOnOutput;
        this.memory = memory;
        this.inputs = inputs;
    }

    public IntCodeEmulator run() {
        running = true;
        while (running) {
            System.out.println("WTF" + memory.get(7));
            fetchOpcode();
        }
        return this;
    }

    public void fetchOpcode() {
//        System.out.println("pc = " + pc);

        int opcode = memory.get(pc);
        int op = 10 * Utils.getDigit(opcode, 2) + Utils.getDigit(opcode, 1);
        int[] modes = getModes(opcode);

        System.out.println("opcode = " + opcode);
        System.out.println("op = " + op);

        Opcode opc = Opcode.get(op);

        Argument[] args = new Argument[opc.getArgsLength()];
        for (int i = 1; i <= opc.getArgsLength(); i++) {
            Argument arg = new Argument(memory.get(pc + i), modes[i]);
            args[i - 1] = arg;

//            System.out.println("args[" + (i - 1) + "]=" + arg.getValue()
//                    + "(mode=" + arg.getMode() + ") {" + getValueFromMemory(arg) + "}");
        }

        pc += (args.length + 1);

        opc.run(this, args);
//        System.out.println();
    }

    public void setMemoryValue(int pos, int value){
        System.out.println("loool " + pos);
        Utils.ensureSize(memory, pos+1);
        memory.set(pos, value);
    }

    public int getValueFromMemory(Argument arg) {
        return getValueFromMemory(arg.getValue(), arg.getMode());
    }

    public int getValueFromMemory(int val, int mode) {
        return mode == 0 ? memory.get(val) : val;
    }

    private static int[] getModes(int opcode) {
        int[] modes = new int[4];
        modes[0] = -1;
        modes[1] = Utils.getDigit(opcode, 3);
        modes[2] = Utils.getDigit(opcode, 4);
        modes[3] = Utils.getDigit(opcode, 5);

        return modes;
    }

    public int getLastOutput(){
        return outputs.get(outputs.size()-1);
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public ArrayList<Integer> getMemory() {
        return memory;
    }

    public List<Integer> getInputs() {
        return inputs;
    }

    public List<Integer> getOutputs() {
        return outputs;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean isHaltOnOutput() {
        return haltOnOutput;
    }

    public boolean isRunning() {
        return running;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public static class Builder {
        private ArrayList<Integer> memory;
        private List<Integer> inputs = new ArrayList<>();

        private boolean haltOnOutput;

        private boolean useVerb;
        private int verb;

        private boolean useNoun;
        private int noun;

        public Builder(){ }

        public IntCodeEmulator build(){
            IntCodeEmulator emulator = new IntCodeEmulator(memory, inputs, haltOnOutput);
            if (useNoun) emulator.getMemory().set(1, noun);
            if (useVerb) emulator.getMemory().set(2, verb);
            return emulator;
        }

        public Builder setHaltOnOutput(boolean haltOnOutput) {
            this.haltOnOutput = haltOnOutput;
            return this;
        }

        public Builder setMemory(ArrayList<Integer> memory) {
            this.memory = memory;
            return this;
        }

        public Builder addInputs(int... inputs) {
            for (int input : inputs) {
                this.inputs.add(input);
            }
            return this;
        }

        public Builder withNoun(int noun) {
            useNoun = true;
            this.noun = noun;
            return this;
        }

        public Builder withVerb(int verb) {
            useVerb = true;
            this.verb = verb;
            return this;
        }
    }
}
