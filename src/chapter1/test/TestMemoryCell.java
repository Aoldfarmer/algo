package chapter1.test;

import chapter1.entity.MemoryCell;

/**
 * @author yunqiangdi
 * @version 1.0
 * @since 2017-06-10 1:42 PM
 */
public class TestMemoryCell {

    public static void main(String[] args) {
        MemoryCell m = new MemoryCell();
        m.write("37");
        String val = (String) m.read();
        System.out.println("Val is : " + val);


        MemoryCell m2 = new MemoryCell();
        m2.write(2);
        int val2 = (int) m2.read();
        System.out.println("Val2 is : " + val2);

    }
}
