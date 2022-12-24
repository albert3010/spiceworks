package leetcode_problems;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {
    private String key;
    private Integer val;
    private long expiredAt;
    private Node prev;
    private Node next;

    public Node(String key, Integer val, long expiredAt) {
        this.key = key;
        this.val = val;
        this.expiredAt = expiredAt;
        this.prev =null;
        this.next =null;
    }
    public void updateVal(Integer val){
        this.val = val;
    }
    public void updateExpiredAt(long expiredAt){
        this.expiredAt = expiredAt;
    }
}
