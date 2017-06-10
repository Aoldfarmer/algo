package chapter1.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

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

    public static <T extends Comparable<? super T>> T findMax(T [] arr) {
        int maxIndex = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].compareTo(arr[maxIndex]) > 0) {
                maxIndex = i;
            }
        }
        return arr[maxIndex];
    }

    static class CaseCompare implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareToIgnoreCase(o2);
        }
    }

    public static <T> T findMax(T[] arr, Comparator<? super T> cmp) {
        int maxIndex = 0;

        for (int i = 0; i < arr.length; i++) {
            if (cmp.compare(arr[i], arr[maxIndex]) > 0) {
                maxIndex = i;
            }
        }
        return arr[maxIndex];
    }

    public static double totalArea(Shape[] arr) {
        double total = 0;
        for (Shape s : arr) {
            if (s != null)
                total += s.computeArea();
        }
        return total;
    }


    public static double totalArea(Collection<? extends Shape> arr) {
        double total = 0;
        for (Shape s : arr) {
            if (s != null)
                total += s.computeArea();
        }
        return total;
    }

    public static void main(String[] args) {
        Shape[] sh1 = {new Circle(4.0), new Square(3.0), new Rectangle(3.0, 2.0)};
        String[] sh2 = {"joe", "bob", "Bill", "Zksda"};
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Square(3.0));
        shapes.add(new Circle(4.0));
        System.out.println(findMax(sh1));
        System.out.println(findMax(sh2));
        System.out.println(totalArea(sh1));
        System.out.println(totalArea(shapes));
        System.out.println(findMax(sh2, new CaseCompare()));
    }
}
