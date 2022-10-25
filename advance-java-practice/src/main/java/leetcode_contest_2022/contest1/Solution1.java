package leetcode_contest_2022.contest1;

import org.junit.Test;

import java.util.*;

public class Solution1 {

    @Test
    public void main1() {
//        [[1,2],[2,4],[3,2],[4,1]]
        int[][] meet = {{1, 2}, {2, 4}, {3, 2}, {4, 1}};
//        findAllPeople(6, meet, 1);
        getOrder(meet);

        int i = 0;
    }

    class Node {
        int u;
        int time;

        public Node(int u, int time) {
            this.u = u;
            this.time = time;
        }
    }

    class NodeA {
        int et;
        int pt;
        int i;

        public NodeA(int et, int pt, int i) {
            this.et = et;
            this.pt = pt;
            this.i = i;
        }
    }

    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        List<NodeA> list = new ArrayList<>();
        int ans[] = new int[n];
        int k = 0;
        for (int i = 0; i < n; i++) {
            list.add(new NodeA(tasks[i][0], tasks[i][1], i));
        }
        Collections.sort(list, (a, b) -> {
            if (a.et == b.et) {
                return a.pt - b.pt;
            }
            return a.et - b.et;
        });

        PriorityQueue<NodeA> priorityQueue = new PriorityQueue<>((a, b) -> {
            if (a.pt == b.pt) {
                return a.i - b.i;
            }
            return a.pt - b.pt;
        });
        int currentTime = 0;

        int i = 0;
        NodeA nd_ = list.get(0);
        if (currentTime < nd_.et) {
            currentTime = nd_.et;
        }
        priorityQueue.add(list.get(i++));

        while (!priorityQueue.isEmpty()) {
            NodeA node = priorityQueue.poll();
            currentTime += node.pt;
            ans[k++] = node.i;
            if (i < n) {
                NodeA nd = list.get(i);
                if (currentTime < nd.et) {
                    currentTime = nd.et;
                }
            }
            System.out.println();
            while (i < n && currentTime >= list.get(i).et) {
                priorityQueue.add(list.get(i++));
            }

        }
        return ans;
    }

    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        Map<Integer, Map<Integer, List<Integer>>> map = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> a.time - b.time);
        Arrays.sort(meetings, (a, b) -> a[2] - b[2]);
        int m = meetings.length;

        for (int i = 0; i < m; i++) {
            map.put(meetings[i][0], new HashMap<>());
            map.put(meetings[i][1], new HashMap<>());
        }
        for (int i = 0; i < m; i++) {
            List<Integer> list = map.get(meetings[i][0]).get(meetings[i][1]);
            if (list == null) {
                List<Integer> arr = new ArrayList<>();
                map.get(meetings[i][0]).put(meetings[i][1], arr);
                map.get(meetings[i][1]).put(meetings[i][0], arr);
            }
            map.get(meetings[i][0]).get(meetings[i][1]).add(meetings[i][2]);
            map.get(meetings[i][1]).get(meetings[i][0]).add(meetings[i][2]);
        }


        queue.add(new Node(0, 0));
        queue.add(new Node(firstPerson, 0));
        List<Integer> ans = new ArrayList<>();
        int[] notified = new int[n];
        Arrays.fill(notified, -1);

        while (!queue.isEmpty()) {
            Node nd = queue.poll();
            if (notified[nd.u] != -1) {
                continue;
            }
            notified[nd.u] = nd.time;
            ans.add(nd.u);
            for (Map.Entry<Integer, List<Integer>> mp : map.getOrDefault(nd.u, new HashMap<>()).entrySet()) {
                for (Integer time : mp.getValue()) {
                    if (time >= notified[nd.u]) {
                        queue.add(new Node(mp.getKey(), time));
                    }
                }
            }
        }
        return ans;
    }


    public int maxLength(int[] ribbons, int k) {
        int l = ribbons.length;
        int minLen = 1;
        int maxLen = Arrays.stream(ribbons).max().getAsInt();
        int ans = 0;
        while (minLen <= maxLen) {
            int mid = (minLen + maxLen) / 2;

            if (isPossible(ribbons, mid, k)) {
                minLen = mid + 1;
                ans = Math.max(ans, mid);
            } else {
                maxLen = mid - 1;
            }
        }
        return ans;
    }

    private boolean isPossible(int[] ribbons, int cutLen, int k) {
        int l = ribbons.length;
        int count = 0;

        for (int i = 0; i < l; i++) {
            if (ribbons[i] >= cutLen) {
                count += ribbons[i] / cutLen;
            }
            if (count >= k) return true;
        }
        return false;
    }

    public boolean placeWordInCrossword(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != '#') {
                    if (check(board, i - 1, j) && valid(board, i, j, 0, true, -1, word)) return true;
                    if (check(board, i + 1, j) && valid(board, i, j, 0, true, 1, word)) return true;
                    if (check(board, i, j - 1) && valid(board, i, j, 0, false, -1, word)) return true;
                    if (check(board, i, j + 1) && valid(board, i, j, 0, false, 1, word)) return true;
                }
            }
        }
        return false;
    }

    private boolean valid(char[][] board, int i, int j, int pos, boolean vertical, int dir, String word) {
        if (pos == word.length()) {

            if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) {
                return true;
            }
            return board[i][j] == '#';
        }
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) {
            return false;
        }
        if (board[i][j] == '#') return false;
        if (board[i][j] != ' ' && board[i][j] != word.charAt(pos)) return false;
        i = i + getInt(vertical) * dir;
        j = j + getInt(!vertical) * dir;
        return valid(board, i, j, pos + 1, vertical, dir, word);
    }

    private boolean check(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) {
            return true;
        }
        return board[i][j] == '#';
    }

    public int getInt(boolean bool) {
        return bool ? 1 : 0;
    }

}
