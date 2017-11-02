package chapter5.hashtable;

import java.util.Random;

public class StringHashFamily implements HashFamily<String> {

    private final int[] MULTIPLIERS;

    private final Random r = new Random();

    public StringHashFamily(int d) {
        MULTIPLIERS = new int[d];
        generateNewFunctions();
    }

    @Override
    public int getNumberOfFunctions() {
        return MULTIPLIERS.length;
    }

    @Override
    public void generateNewFunctions() {
        for (int i = 0; i < MULTIPLIERS.length; i++) {
            MULTIPLIERS[i] = r.nextInt();
        }
    }

    @Override
    public int hash(String s, int which) {
        final int multiplier = MULTIPLIERS[which];
        int hashVal = 0;

        for (int i = 0; i < s.length(); i++) {
            hashVal = multiplier * hashVal + s.charAt(i);
        }

        return hashVal;
    }
}
