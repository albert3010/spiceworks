package leetcode.contests.ContetsAE;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Contests6 {

    @Test
    public void ContestsSolution() {
        LRUCache cache = new LRUCache(1);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));  // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
    }

    class LRUCache {
        class Node {
            int key;
            int val;
            Node forward;
            Node back;

            Node(int key, int val) {
                this.key = key;
                this.val = val;
            }

            void set(int val) {
                this.val = val;
            }
        }

        Map<Integer, Node> map = new HashMap<>();
        int limit;
        int nodes = 0;
        Node head;
        Node tail;

        public LRUCache(int capacity) {
            limit = capacity;
            head = null;
            tail = null;
        }

        public int get(int key) {
            if (map.get(key) == null || limit == 0) {
                return -1;
            }
            updateList(map.get(key));
            return map.get(key).val;
        }

        public void updateList(Node nd) {
            if (head != null && nd.key != head.key) {
                Node h1 = nd.forward;
                Node b1 = nd.back;
                h1.back = b1;

                if (b1 != null) {
                    b1.forward = h1;
                } else {
                    tail = h1;
                }
                head.forward = nd;
                nd.back = head;
                nd.forward = null;
                head = nd;
            }
        }

        public void put(int key, int value) {
            if (map.get(key) != null) {
                Node nd = map.get(key);
                nd.set(value);
                updateList(nd);
            } else {
                Node nd = new Node(key, value);
                if (head == null) {
                    if (limit > 0) {
                        head = nd;
                        map.put(key, nd);
                        tail = head;
                        nodes++;
                    }
                } else {
                    map.put(key, nd);
                    head.forward = nd;
                    nd.back = head;
                    nd.forward = null;
                    head = nd;

                    nodes++;
                    if (nodes > limit) {
                        Node h1 = tail.forward;
                        int v = tail.key;
                        h1.back = null;
                        tail = h1;
                        nodes--;
                        map.remove(v);
                    }
                }


            }
        }
    }
}
