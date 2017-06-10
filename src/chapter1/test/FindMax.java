package chapter1.test;

import chapter1.entity.Circle;
import chapter1.entity.Rectangle;
import chapter1.entity.Shape;
import chapter1.entity.Square;

/**
 *
 * @author yunqiangdi
 * @version 1.0
 * @since 2017-06-10 2:59 PM
 */
public class FindMax {

    public static Comparable findMax(Comparable [] arr) {
        int maxIndex = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].compareTo(arr[maxIndex]) > 0) {
                maxIndex = i;
            }
        }

        return arr[maxIndex];
    }

    public static void main(String[] args) {
        Shape[] sh1 = {new Circle(4.0), new Square(3.0), new Rectangle(3.0, 2.0)};
        String[] sh2 = {"Joe", "Bob", "Bill", "Zeke"};

        System.out.println(findMax(sh1));
        System.out.println(findMax(sh2));

    }
}
