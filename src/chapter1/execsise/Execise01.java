package chapter1.execsise;

import java.util.Arrays;
import java.util.Random;

/**
 * @author yunqiangdi
 * @version 1.0
 * @since 2017-06-12 12:02 PM
 */
public class Execise01 {

    private static final Random RANDOM = new Random(47);

    public static int[] randomArray(int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++)
            array[i] = RANDOM.nextInt(length * 2);
        return array;
    }


    public static void printResult(int[] array) {

        int[] copyArray = Arrays.copyOf(array, array.length);

        System.out.println("length:" + array.length);
        long start = System.currentTimeMillis();
        bubbleSort(array);
        System.out.println("algo1 result:" + array[array.length / 2 - 1]);
        System.out.println("algo1 cost:" + (System.currentTimeMillis() - start) + "ms");

        long start2 = System.currentTimeMillis();
        System.out.println("algo2 result:" + banSort(copyArray, array.length / 2));
        System.out.println("algo2 cost:" + (System.currentTimeMillis() - start2) + "ms");
        System.out.println("--------------------------------");
    }

    //algo 1, 冒泡排序
    public static void bubbleSort(int[] array) {
        for (int i = 0; i< array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    //algo2, 分开排序
    public static int banSort(int[] array, int kMax) {
        int[] temp = Arrays.copyOf(array, kMax);
        bubbleSort(temp);
        for (int i = kMax; i < array.length; i++) {
            if (temp[kMax - 1] > array[i]) {
                if (temp[0] > array[i]) {
                    int s = kMax - 1;
                    while (s != 0) {
                        temp[s] = temp[s - 1];
                        s--;
                    }
                    temp[0] = array[i];
                } else {
                    for (int j = 0; j < kMax - 1; j++) {
                        if (temp[j] <= array[i] && array[i] < temp[j + 1]) {
                            int s = kMax - 1;
                            while (s != 0 && s != j + 1) {
                                temp[s] = temp[s - 1];
                                s--;
                            }
                            temp[j + 1] = array[i];
                            break;
                        }
                    }
                }
            }
        }
        return temp[kMax - 1];
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            printResult(randomArray(RANDOM.nextInt(10000)));
        }
    }
}
