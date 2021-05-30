package ir.ac.kntu.util;

import java.util.Random;

public class RandomHelper {

    private static final Random random = new Random();

    public static String getRandomWord() {
        String createdWord = "";
        for (int i = 0; i < getRandomInt(3,10); i++) {
            createdWord += (char)('a' + getRandomInt(0,25));
        }
        return createdWord;
    }

    public static Double getRandomCost() {
        return getRandomInt(5000,50000) + Double.parseDouble(String.format("%.2f",random.nextDouble()));
    }

    public static int getRandomInt(int start,int end) {
        return start + random.nextInt(end - start + 1);
    }
}
