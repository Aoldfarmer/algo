package chapter1.execsise;

/**
 * @author yunqiangdi
 * @version 1.0
 * @since 2017-06-15 11:56 AM
 */
public class Execise05 {

    public static long getCount(long n) {
        if (n < 2)
            return n;
        else
            return getCount(n / 2) + n % 2;
    }



    public static void main(String[] args) {
        System.out.print(getCount(6));
    }
}
