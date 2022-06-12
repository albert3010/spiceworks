import java.util.HashMap;
import java.util.Map;

public class Solution {

    static class Node {
        int i;
        int j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static String getWinningCombo(String[][] ticket, int[] sequence) {
        Map<String, Node> map = new HashMap<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                if (!ticket[i][j].equals("")) {
                    map.put(ticket[i][j], new Node(i, j));
                }
            }
        }
        int n = sequence.length;
        for (int i = 0; i < n; i++) {
            String ss = String.valueOf(sequence[i]);
            Node nd = map.get(ss);
            if(nd!=null){
                ticket[nd.i][nd.j] ="$";
            }

        }
        return ""
;    }
    static boolean checkCorner(String[][] ticket){
        boolean row1left = true;
        boolean row1right = true;
        boolean row3left = true;
        boolean row3right = true;
        for (int j = 0; j < 9; j++) {
            if (row1left && !(ticket[0][j].equals("$") || ticket[0][j].equals(""))) {
                row1left = false;
            }
            if (row3left && !(ticket[2][j].equals("$") || ticket[2][j].equals(""))) {
                row3left = false;
            }
        }
        for (int j = 9; j>=0; j--) {
            if (row1right && !(ticket[0][j].equals("$") || ticket[0][j].equals(""))) {
                row1right = false;
            }
            if (row3right && !(ticket[2][j].equals("$") || ticket[2][j].equals(""))) {
                row3right = false;
            }
        }
        return row1left && row1right && row3left && row3right;
    }

}
