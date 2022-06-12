package test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class HackerAPI2 {

    public static void main(String[] args) {
        // Dallas
        LinkedHashMap<String, Double> lhm = new LinkedHashMap();

        // Put elements to the map
        lhm.put("Zara", new Double(3434.34));
        lhm.put("Mahnaz", new Double(123.22));
        lhm.put("Ayan", new Double(1378.00));
        lhm.put("Daisy", new Double(99.22));
        lhm.put("Qadir", new Double(-19.08));

        for (Map.Entry<String, Double> entry: lhm.entrySet()) {
            System.out.println(entry.getKey());
        }
        System.out.println(getTopRatedFoodOutlets("Omaha"));


    }

    public static List<String> getTopRatedFoodOutlets(String city) {
        try {
            String urlPath = "https://jsonmock.hackerrank.com/api/food_outlets?city=" + city;
            int total = getPages(urlPath);
            return getRating(urlPath, total);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }


    static List<String> getRating(String urlPath, int pages) throws Exception {
        LinkedHashMap<String, Double> map = new LinkedHashMap<>();
        double maxRating = 0;
        for (int pg = 1; pg <= pages; pg++) {
            String urlPath_ = urlPath + "&page=" + pg;

            URL url = new URL(urlPath_);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (con.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                JsonParser parser = new JsonParser();
                JsonObject obj = (JsonObject) parser.parse(output);
                JsonArray list = obj.get("data").getAsJsonArray();
                int l = list.size();

                for (int i = 0; i < l; i++) {
                    JsonObject jsonObject = list.get(i).getAsJsonObject();
                    JsonObject userRating = jsonObject.get("user_rating").getAsJsonObject();
                    String name = jsonObject.get("name").getAsString();
                    double rating = userRating.get("average_rating").getAsDouble();
                    if(rating>=maxRating){
                        map.put(name, rating);
                    }
                    maxRating = Math.max(maxRating, rating);
                }
            }
        }
        List<String> ans = new ArrayList<>();
        System.out.println(maxRating);
        for (Map.Entry<String, Double> entry: map.entrySet()) {
            if(entry.getValue()==maxRating){
                ans.add(entry.getKey());
            }
        }
        return ans;
    }


    static int getPages(String urlPath) throws IOException {
        URL url = new URL(urlPath);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (con.getInputStream())));
        String output;
        while ((output = br.readLine()) != null) {
            JsonParser parser = new JsonParser();
            JsonObject obj = (JsonObject) parser.parse(output);
            return Integer.parseInt(obj.get("total_pages").toString());
        }
        return 0;
    }
}
