package chapter1.entity;

/**
 * @author yunqiangdi
 * @version 1.0
 * @since 2017-06-10 1:41 PM
 */
public class MemoryCell<T> {

    private T storedValue;

    public T read() {
        return storedValue;
    }

    public void write(T x) {
        storedValue = x;
    }
}
