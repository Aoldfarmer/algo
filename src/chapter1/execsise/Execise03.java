package chapter1.execsise;

/**
 * @author yunqiangdi
 * @version 1.0
 * @since 2017-06-14 1:38 PM
 */
public class Execise03 {

    // 0 < n < 10
    public static void printDigit(int n) {
        System.out.print(n);
    }

    public static void printOut(int n) {
        if (n >= 10)
            printOut(n / 10);
        printDigit(n % 10);
    }

    public static void printDouble(double d) {
        int n0 = (int) d;
        if (n0 < 0) {
            System.out.print("-");
            printOut(-n0);
        }
        if (n0 >= 0) {
            printOut(n0);
        }
        if (d != 0) {
            System.out.print(".");
            String n1Str = String.valueOf(d);
            printOut(Integer.parseInt(n1Str.substring(n1Str.indexOf(".") + 1)));
        }
    }

    public static void main(String[] args) {
        printDouble(0D);
    }

}
