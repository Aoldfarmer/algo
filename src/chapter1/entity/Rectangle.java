package chapter1.entity;

/**
 * @author yunqiangdi
 * @version 1.0
 * @since 2017-06-10 2:57 PM
 */
public class Rectangle extends Shape {

    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double computeArea() {
        return length * width;
    }
}
