package leetcode_problems.contests_2020.ContestQ1;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

class FoodRatings {
    Map<String, TreeSet<Node>> map = new HashMap<>();
    Map<String, Node> foodMap = new HashMap<>();

    public class Node implements Comparable<Node>{
        String food;
        String cuisine;
        int rating;
        Node(String food, int rating, String cuisine){
            this.food = food;
            this.rating = rating;
            this.cuisine = cuisine;
        }

        @Override
        public int compareTo(Node o) {
            if(o.rating == this.rating){
                return o.food.compareTo(this.food);
            }
            return o.rating - this.rating;
        }
    }

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        int n = foods.length;
        for (int i = 0; i < n; i++) {
            map.putIfAbsent(cuisines[i], new TreeSet<Node>());
            Node nd = new Node(foods[i], ratings[i], cuisines[i]);
            map.get(cuisines[i]).add(nd);
            foodMap.put(foods[i], nd);
        }
    }

    public void changeRating(String food, int newRating) {
            Node nd = foodMap.get(food);
            TreeSet<Node> treeSet = map.get(nd.cuisine);
            treeSet.remove(nd);
            nd.rating = newRating;
            treeSet.add(nd);
            map.put(nd.cuisine,treeSet);
    }

    public String highestRated(String cuisine) {
        return map.get(cuisine).first().food;
    }
}
