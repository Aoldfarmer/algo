package chapter1.entity;

/**
 * @author yunqiangdi
 * @version 1.0
 * @since 2017-06-10 2:56 PM
 */
public class Square extends Shape {

    private double length;

    public Square(double length) {
        this.length = length;
    }

    @Override
    public double computeArea() {
        return length * length;
    }
}
