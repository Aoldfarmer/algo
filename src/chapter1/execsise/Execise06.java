package chapter1.execsise;

/**
 *
 * @author yunqiangdi
 * @version 1.0
 * @since 2017-06-16 12:24 PM
 */
public class Execise06 {

    public static void permute(String str) {
        char[] array = str.toCharArray();
        permute(array, 0, array.length - 1);
    }

    private static void permute(char[] str, int low, int high) {
        if (low == high) {
            System.out.println(str);
         } else {
            for (int i = low; i <= high; i++) {
                swap(str, low, i);
                permute(str, low + 1, high);
                swap(str, low, i);
            }
        }
    }

    private static void swap(char[] str, int i , int j) {
        int length = str.length;
        if (i < length && j < length) {
            char temp = str[i];
            str[i] = str[j];
            str[j] = temp;
        }
    }


    public static void main(String[] args) {
        permute("abcd");
    }
}
