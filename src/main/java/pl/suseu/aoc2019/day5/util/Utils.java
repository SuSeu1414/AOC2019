package pl.suseu.aoc2019.day5.util;

public class Utils {

    public static int getDigit(int number, int place) {
        return ( number % ( (int) Math.pow(10, place) ) ) / ( (int) Math.pow(10, (place-1) ) );
    }
}
