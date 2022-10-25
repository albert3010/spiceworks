package leetcode_contest_2022.contest1;

import java.util.HashMap;
import java.util.Map;

public class SnapshotArray {
    int arr[];
    Map<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
    HashMap<Integer, Integer> snapshot = new HashMap<>();
    int snapId = 0;

    public SnapshotArray(int length) {
        arr = new int[length];
    }

    public void set(int index, int val) {
        snapshot.put(index, val);
    }

    public int snap() {
        map.put(snapId, snapshot);
        snapshot = new HashMap<>();
        return snapId++;
    }

    public int get(int index, int snap_id) {
        while (snap_id >= 0) {
            if (map.get(snap_id).get(index) != null) {
                return map.get(snap_id).get(index);
            } else {
                snap_id--;
            }
        }
        return arr[index];
    }

}
