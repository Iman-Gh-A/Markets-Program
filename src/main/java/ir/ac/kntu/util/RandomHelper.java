package ir.ac.kntu.util;

import java.util.Random;

public class RandomHelper {

    private static final Random RANDOM = new Random();

    public static String getRandomWord() {
        String createdWord = "";
        for (int i = 0; i < getRandomInt(3,10); i++) {
            createdWord += (char)('a' + getRandomInt(0,25));
        }
        return createdWord;
    }

    public static Double getRandomCost() {
        return getRandomInt(5000,50000) + Double.parseDouble(String.format("%.2f", RANDOM.nextDouble()));
    }

    public static int getRandomInt(int start,int end) {
        return start + RANDOM.nextInt(end - start + 1);
    }

    public static String getRandomID() {
        String id = "";
        for (int i = 0; i < 10; i++) {
            id += RANDOM.nextInt(10);
        }
        return id;
    }

    public static String getRandomPhone() {
        String phone = "09-";
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                phone += RANDOM.nextInt(10);
            }
            phone += "-";
        }
        return phone.substring(0,phone.length()-1);
    }
}
