package chapter1.test;

import chapter1.entity.MemoryCell;

/**
 * @author yunqiangdi
 * @version 1.0
 * @since 2017-06-10 1:42 PM
 */
public class TestMemoryCell {

    public static void main(String[] args) {

        MemoryCell<Integer> m2 = new MemoryCell<>();
        m2.write(4);
        int val2 = m2.read();
        System.out.println("Val2 is : " + val2);


        MemoryCell<String> m = new MemoryCell<>();
        m.write("fs");
        String val = m.read();
        System.out.println("Val is : " + val);

    }
}
