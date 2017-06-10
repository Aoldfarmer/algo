package chapter1.entity;

/**
 * @author yunqiangdi
 * @version 1.0
 * @since 2017-06-10 2:25 PM
 */
public abstract class Shape implements Comparable<Shape> {


    public abstract double computeArea();


    @Override
    public int compareTo(Shape o) {
        if((this.computeArea() - o.computeArea()) > 0) {
            return 1;
        }
        if (this.computeArea() - o.computeArea() == 0) {
            return 0;
        }
        if (this.computeArea() - o.computeArea() < 0) {
            return -1;
        }
        return 1;
    }

}
