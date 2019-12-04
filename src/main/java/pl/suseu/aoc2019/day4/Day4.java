package pl.suseu.aoc2019.day4;

import pl.suseu.aoc2019.FileUtils;

public class Day4 {

    private int min;
    private int max;

    public Day4(){
        String input = FileUtils.loadFile("day4").get(0);
        min = Integer.parseInt(input.split("-")[0]);
        max = Integer.parseInt(input.split("-")[1]);
    }

    public void firstPart() {
        int validPasswords = 0;
        for (; min < max; min++) {
            if (checkPassword(min)) validPasswords++;
        }
        System.out.println(validPasswords);
    }

    public void secondPart() {
        int validPasswords = 0;
        for (; min < max; min++) {
            if (checkPassword2(min)) validPasswords++;
        }
        System.out.println(validPasswords);
    }

    public boolean checkPassword(int pass) {
        char[] digits = String.valueOf(pass).toCharArray();

        boolean sameDigits = false;
        boolean digitsDecrease = false;

        for (int i = 0; i < digits.length - 1; i++) {
            if (digits[i] == digits[i+1]) {
                sameDigits = true;
            }
            if (digits[i] > digits[i+1]) {
                digitsDecrease = true;
                break;
            }
        }

        return sameDigits && !digitsDecrease;
    }

    public boolean checkPassword2(int pass) {
        char[] digits = String.valueOf(pass).toCharArray();

        boolean sameDigits = false;
        boolean digitsDecrease = false;

        for (int i = 0; i < digits.length - 1; i++) {
            if (exactlyTwoDigits(digits)) {
                sameDigits = true;
            }
            if (digits[i] > digits[i+1]) {
                digitsDecrease = true;
                break;
            }
        }

        return sameDigits && !digitsDecrease;
    }

    public boolean exactlyTwoDigits(char[] digits) {
        int digitCount = 1;

        for (int i = 0; i < digits.length; i++) {
            for (int j = 0; j < digits.length; j++) {
                if (i == j) continue;

                if (digits[i] == digits[j])
                    digitCount++;
            }
            if(digitCount == 2) return true;
            digitCount = 1;
        }
        return false;
    }
}
