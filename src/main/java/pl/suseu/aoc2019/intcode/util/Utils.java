package pl.suseu.aoc2019.intcode.util;

import java.util.ArrayList;

public class Utils {

    public static int getDigit(int number, int place) {
        return ( number % ( (int) Math.pow(10, place) ) ) / ( (int) Math.pow(10, (place-1) ) );
    }

    public static void ensureSize(ArrayList<Integer> list, int size) {
        // Prevent excessive copying while we're adding
        list.ensureCapacity(size);
        while (list.size() < size) {
            list.add(0);
        }
    }
}
