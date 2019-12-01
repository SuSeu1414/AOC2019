package pl.suseu.aoc2019.day1;

import pl.suseu.aoc2019.FileUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day1 {

    private List<Integer> masses;

    public void firstPart(){
        masses = FileUtils.loadFileAsIntegerList("day1");
        int sum = masses.stream().mapToInt(this::calculateFuel).sum();
        System.out.println(sum);
    }

    public void secondPart(){
        masses = FileUtils.loadFileAsIntegerList("day1");
        AtomicInteger fuelNeeded = new AtomicInteger();
        masses.forEach(mass -> {
            int fuel = calculateFuel(mass);
            int fuel2 = calculateFuel(fuel);
            while (fuel2 > 0) {
                fuel += fuel2;
                fuel2 = calculateFuel(fuel2);
            }
            fuelNeeded.addAndGet(fuel);
        });
        System.out.println(fuelNeeded);
    }

    private int calculateFuel(int mass) {
        return mass / 3 - 2;
    }

}
