package leetcode_contest_2022.group1;

import java.util.HashMap;
import java.util.Set;

public class AuthenticationManager {
    int timeToLive;
    HashMap<String, Integer> map = new HashMap<>();

    public AuthenticationManager(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    public void generate(String tokenId, int currentTime) {
        map.put(tokenId, currentTime + timeToLive);
    }

    public void renew(String tokenId, int currentTime) {
        if (map.get(tokenId) != null) {
            int time = map.get(tokenId);
            if (currentTime < time) {
                map.put(tokenId, currentTime + timeToLive);
            }
        }

    }

    public int countUnexpiredTokens(int currentTime) {
        Set<String>  set = map.keySet();
        int ans =0;
        for (String key : set){
            if(map.get(key)>currentTime){
                ans++;
            }
        }
        return ans;
    }
}
//[null,null,0,0,0,null,null,2,2,2,2,1,null,0,0,null,0,null,null,1,1,null,null,null,null,null,null,2,2,null,1,1,1,1,null,null,null,null,3,null,null,4,4,null,null,null,4,3,3,2,1,null,null,null,0,0]
//[null,null,0,0,0,null,null,2,2,2,2,1,null,1,1,null,1,null,null,2,1,null,null,null,null,null,null,2,2,null,1,1,1,1,null,null,null,null,3,null,null,4,4,null,null,null,4,3,3,2,1,null,null,null,0,0]
