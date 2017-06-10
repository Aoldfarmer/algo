package chapter1.entity;

/**
 * @author yunqiangdi
 * @version 1.0
 * @since 2017-06-10 2:36 PM
 */
public class Circle extends Shape {

    private double raudis;

    public Circle(double raudis) {
        this.raudis = raudis;
    }

    @Override
    public double computeArea() {
        return Math.PI * raudis * raudis;
    }

}
