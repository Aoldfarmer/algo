package chapter1;

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
    }
}
