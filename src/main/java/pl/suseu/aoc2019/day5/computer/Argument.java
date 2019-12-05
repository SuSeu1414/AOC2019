package pl.suseu.aoc2019.day5.computer;

public class Argument {

    private int value;
    private int mode;

    public Argument(int value, int mode) {
        this.value = value;
        this.mode = mode;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
