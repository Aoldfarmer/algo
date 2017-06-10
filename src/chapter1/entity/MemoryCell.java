package chapter1.entity;

/**
 * @author yunqiangdi
 * @version 1.0
 * @since 2017-06-10 1:41 PM
 */
public class MemoryCell {

    private Object storedValue;

    public Object read() {
        return storedValue;
    }

    public void write(Object x) {
        storedValue = x;
    }
}
