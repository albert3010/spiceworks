package leetcode_contest_2022.group1;

import java.util.*;

public class Contest_may {

    static Map<Integer, PriorityQueue<String>> scoreMap = new HashMap<>();
    static Map<String, Integer> keyMap = new HashMap<>();
    static PriorityQueue<Integer> heap = new PriorityQueue<>((a, b)-> b-a);

    public static void main(String[] args) {
        Contest_may contest_may = new Contest_may();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int j = 0; j < n; j++) {
            String srt = in.nextLine();
            String[] ss = srt.split(" ");
            if (ss[0].equals("A")) {
                assign(ss[1], Integer.parseInt(ss[2]));
            }
            if (ss[0].equals("M")) {
                maxScore();
            }
            if (ss[0].equals("D")) {
                delete(ss[1]);
            }
            if (ss[0].equals("G")) {
                getScore(ss[1]);
            }
        }
    }

    public static void getScore(String key) {
        System.out.println(keyMap.get(key));
    }

    public static void maxScore() {
        System.out.println("tets1");
        if (!heap.isEmpty()) {
            System.out.println("tets2");
            int val = heap.poll();
            PriorityQueue<String> priorityQueue = scoreMap.get(val);
            String ans = priorityQueue.peek();
            System.out.println(ans + " " + val);
        }
        System.out.println("tets3");
    }

    public static void delete(String s) {
        if (keyMap.get(s) != null) {
            int sc1 = keyMap.get(s);
            heap.remove(sc1);
            keyMap.remove(s);
            Integer sc = keyMap.get(s);
            PriorityQueue<String> set2 = scoreMap.getOrDefault(sc, new PriorityQueue<>());
            set2.remove(s);
            scoreMap.put(sc, set2);
        }
    }

    public static void assign(String s, int score) {
        if (scoreMap.get(score) == null) {
            heap.add(score);
            if (keyMap.get(s) != null) {
                Integer sc = keyMap.get(s);
                PriorityQueue<String> set = scoreMap.get(sc);
                set.remove(s);
                scoreMap.put(sc, set);
            }
        } else {
            if (keyMap.get(s) != null) {
                Integer sc = keyMap.get(s);
                PriorityQueue<String> set = scoreMap.get(sc);
                set.remove(s);
                scoreMap.put(sc, set);
            }
        }
        keyMap.put(s, score);
        PriorityQueue<String> heap1 = new PriorityQueue<>();
        PriorityQueue<String> set2 = scoreMap.getOrDefault(score, heap1);
        set2.add(s);
        scoreMap.put(score, set2);
    }
}
