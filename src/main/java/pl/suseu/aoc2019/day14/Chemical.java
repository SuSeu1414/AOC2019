package pl.suseu.aoc2019.day14;

public class Chemical {

    private final String type;
    private long amount;

    public Chemical(String type) {
        this.type = type;
        this.amount = 0;
    }

    public String getType() {
        return type;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void addAmount(long amount) {
        this.amount += amount;
    }

    public static Chemical getFromString(String str) {
        Chemical chemical = new Chemical(str.split(" ")[1]);
        chemical.setAmount(Long.parseLong(str.split(" ")[0]));
        return chemical;
    }
}
