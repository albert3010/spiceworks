package leetcode.contests.ContetAD;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Contests13 {


    @Test
    public void ContestsSolution() {
        TweetCounts tweetCounts = new TweetCounts();
        tweetCounts.recordTweet("tweet3", 0);
        tweetCounts.recordTweet("tweet3", 60);
        tweetCounts.recordTweet("tweet3", 10);

        System.out.println(tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 59));
        tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 60);
        tweetCounts.recordTweet("tweet3", 120);
        tweetCounts.getTweetCountsPerFrequency("hour", "tweet3", 0, 210);
        tweetCounts.getTweetCountsPerFrequency("hour", "tweet0", 89, 3045);
//        System.out.println(tweetCounts.getTweetCountsPerFrequency("minute","tweet4",59,9302).size());

    }

    class TweetCounts {
        HashMap<Integer, HashMap<String, Integer>> secMap;
        HashMap<Integer, HashMap<String, Integer>> minMap;
        HashMap<Integer, HashMap<String, Integer>> hrMap;
        HashMap<Integer, HashMap<String, Integer>> dayMap;

        public TweetCounts() {
            secMap = new HashMap<>();
            minMap = new HashMap<>();
            hrMap = new HashMap<>();
            dayMap = new HashMap<>();
        }

        public void recordTweet(String tweetName, int time) {
            HashMap<String, Integer> map = secMap.getOrDefault(time, new HashMap<>());
            map.put(tweetName, map.getOrDefault(tweetName, 0) + 1);
            secMap.put(time, map);

            HashMap<String, Integer> mMap = minMap.getOrDefault(time / 60, new HashMap<>());
            mMap.put(tweetName, mMap.getOrDefault(tweetName, 0) + 1);
            minMap.put(time / 60, mMap);

            HashMap<String, Integer> hMap = hrMap.getOrDefault(time / 3600, new HashMap<>());
            hMap.put(tweetName, hMap.getOrDefault(tweetName, 0) + 1);
            hrMap.put(time / 3600, hMap);

            HashMap<String, Integer> dMap = dayMap.getOrDefault(time / 86400, new HashMap<>());
            dMap.put(tweetName, dMap.getOrDefault(tweetName, 0) + 1);
            dayMap.put(time / 86400, dMap);
        }

        public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
            HashMap<Integer, HashMap<String, Integer>> tMap = secMap;
            HashMap<Integer, Integer> resultMap = new HashMap<>();

            for (int i = startTime; i <= endTime; i++) {
                int stime = getTime(i, freq);
                HashMap<String, Integer> map = tMap.get(i);

                if (map != null)
                    resultMap.put(stime, resultMap.getOrDefault(stime, 0) + map.getOrDefault(tweetName, 0));
                else resultMap.put(stime, resultMap.getOrDefault(stime, 0));

            }
            List<Integer> list = new ArrayList<>();
            int cr = 0;
            for (Integer key : resultMap.keySet()) {
                if (0 == resultMap.get(key))
                    cr++;
                list.add(resultMap.get(key));
            }
            resultMap.clear();
            if (cr > 0 && cr == list.size()) {
                list.remove(0);
            }
            return list;
        }

        public int getTime(int time, String freq) {
            int time_ = time / 60;
            if (freq.equals("hour")) {
                time_ = time / 3600;
//                System.out.println(freq + "__" + time_);
            }
            if (freq.equals("day")) {
                time_ = time / 86400;
            }
            return time_;
        }

        public HashMap<Integer, HashMap<String, Integer>> getMap(String freq) {
            HashMap<Integer, HashMap<String, Integer>> tMap = minMap;
            if (freq.equals("hour")) {
                tMap = hrMap;
            }
            if (freq.equals("day")) {
                tMap = dayMap;
            }
            return tMap;
        }
    }


}
