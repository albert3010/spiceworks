package test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class HashMap_ implements Map_ {
    int capacity;
    int []keymap;

    AtomicInteger act = new AtomicInteger(0);

    int [] group;
    int bCount;
    Semaphore semaphore;
    HashMap_(){
        this.capacity = 1000;
        keymap = new int[1000];
        group = new int [10];
        bCount = 10;
        semaphore = new Semaphore(bCount);
    }

   public int get(String key){
        int hash  = getHash(key);
       act.incrementAndGet();
        return keymap[hash];
    }

   public void put(String key, int val) throws InterruptedException {
       int hash  = getHash(key);
       int bucket = hash%bCount;
       semaphore.acquire();
       keymap[hash] = val;
       semaphore.release();
   }

    int getHash(String key){
        return key.hashCode();
    }

}
