package leetcode_problems;

import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SolutionKeyValue {

    Node tail;
    Node head;
    long expiry;
    Map<String, Node> keyMap;
    Integer totalSum;
    Executor executor;

    public SolutionKeyValue(long expiry, int threadCount) {
        this.expiry = expiry;
        this.keyMap = new ConcurrentHashMap<>();
        this.totalSum = 0;
        this.executor = Executors.newFixedThreadPool(threadCount);
    }

    public Integer get(String key)  {
        Node node = keyMap.get(key);
        long currentTime = Calendar.getInstance().getTime().getTime();
        if (node == null || node.getExpiredAt() < currentTime) {
            removeNode(node);
            System.out.println("Key not found: "+key);
            return null;
        }
//        executor.execute(this::removeExpiredKeys);

        return keyMap.get(key).getVal();

    }

    public void put(String key, Integer val) {
        Node node = keyMap.get(key);
        removeNode(node);

        long currentTime = Calendar.getInstance().getTime().getTime();

        Node newNode = new Node(key, val, currentTime + expiry);
        keyMap.put(key, newNode);

        addNodeToHead(newNode);
        totalSum += val ;
        executor.execute(this::removeExpiredKeys);
    }

    public double getAverage() {
        removeExpiredKeys();
        if (keyMap.size() > 0) {
            return (double) (totalSum) / keyMap.size();
        }
        return 0.0;
    }

    private void removeExpiredKeys() {
        long currentTime = Calendar.getInstance().getTime().getTime();
        while (true) {
            if (tail != null) {
                if (tail.getExpiredAt() < currentTime) {
                    removeNode(tail);
                } else {
                    break;
                }
            }
            if (tail == null) head = null;
        }
    }

    private void removeNode(Node node) {
        if (node == null) return;

        keyMap.remove(node.getKey());
        totalSum -= node.getVal();

        Node prev = node.getPrev();
        Node next = node.getNext();
        if (next == null) {
            if (prev == null) {
                head = null;
                tail = head;
            } else {
                prev.setNext(null);
                head = prev;
            }
        } else {
            if (prev == null) {
                next.setPrev(null);
                tail = next;
            } else {
                next.setPrev(prev);
                prev.setNext(next);
            }
        }
    }

    private void addNodeToHead(Node node) {
        if (node == null) return;
        if (head == null) {
            head = node;
            tail = node;
        } else {
            head.setNext(node);
            node.setPrev(head);
            head = node;
        }
    }
    void print(){
        for (String key :keyMap.keySet()){
            System.out.println(key);
        }
    }

    public static void main(String[] args) throws Exception {
        SolutionKeyValue keyValueService = new SolutionKeyValue(15000, 3);
        keyValueService.put("k3", 60); // 15
        Thread.sleep(5000);
        keyValueService.put("k2", 20);// 20
        Thread.sleep(2000);
        keyValueService.put("k1", 10); // 22, 7sec

        System.out.println(keyValueService.getAverage());
        keyValueService.print();
        Thread.sleep(10000);
        System.out.println(keyValueService.get("k3"));
        System.out.println(keyValueService.getAverage()); // 17 sec
        keyValueService.print();
        Thread.sleep(4000);
        keyValueService.put("k3", 90); // 21 sec
        System.out.println(keyValueService.getAverage());
        keyValueService.print();


//        Thread.sleep(15000);
//        System.out.println(keyValueService.get("k3"));
//        System.out.println(keyValueService.getAverage());
//        System.out.println("---------after-------");
////        Thread.sleep(4000);
////        System.out.println(keyValueService.get("k1"));
//        System.out.println(keyValueService.get("k3"));
//        System.out.println(keyValueService.getAverage());


    }


}
