package test;

public interface Map {

    void put(String key, int val) throws InterruptedException;
    int get(String key);

}
