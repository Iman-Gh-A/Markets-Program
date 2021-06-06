package ir.ac.kntu.util;

import java.util.Random;

public class RandomHelper {

    private static final Random RANDOM = new Random();

    public static Double getRandomCost() {
        return getRandomInt(5000,50000) + Double.parseDouble(String.format("%.2f", RANDOM.nextDouble()));
    }

    public static int getRandomInt(int start,int end) {
        return start + RANDOM.nextInt(end - start + 1);
    }

    public static String getRandomID() {
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append(RANDOM.nextInt(10));
        }
        return id.toString();
    }

    public static String getRandomPhone() {
        StringBuilder phone = new StringBuilder("09-");
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                phone.append(RANDOM.nextInt(10));
            }
            phone.append("-");
        }
        return phone.substring(0,phone.length()-1);
    }
}
