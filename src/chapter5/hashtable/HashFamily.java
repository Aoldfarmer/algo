package chapter5.hashtable;

public interface HashFamily<T> {

    int hash(T t, int which);

    int getNumberOfFunctions();

    void generateNewFunctions();
}
