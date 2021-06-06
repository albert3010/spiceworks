package leetcode_contest_2021.group1;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class HackerAPI {

    public static void main(String[] args) {
//        getUsernames(10);
        String sentence1 = "My name is Haley", sentence2 = "My Haley";
//        System.out.println(areSentencesSimilar(sentence1, sentence2));
//        System.out.println(reverse(3200));

    }


    static List<String> getUsernames(int threshold) {
        try {
            String urlPath = "https://jsonmock.hackerrank.com/api/article_users";
            int total = getPages(urlPath);
            return getUsersHelper(urlPath, threshold, total);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    static List<String> getUsersHelper(String urlPath, int threshold, int pages) throws Exception {
        Set<String> set = new HashSet<>();
        List<String> names = new ArrayList<>();
        for (int pg = 1; pg <= pages; pg++) {
            String urlPath_ = urlPath + "?page=" + pg;

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
                    int submission_count = jsonObject.get("submission_count").getAsInt();
                    if (submission_count > threshold) {
                        String user = jsonObject.get("username").toString();
                        if (!set.contains(user)) {
                            names.add(user);
                            set.add(user);
                        }
                    }
                }
            }

        }

        return names;
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
