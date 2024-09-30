package leetcode_contest_2022.group1;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class HackerAPI {

    public static void main(String[] args) {
//        getUsernames(10);
        String sentence1 = "My name is Haley", sentence2 = "My Haley";
//        System.out.println(areSentencesSimilar(sentence1, sentence2));
//        System.out.println(reverse(3200));

    }
    public class WeatherResponse {
        int page;

        @SerializedName("per_page")
        int perPage;

        int total;

        @SerializedName("total_pages")
        int totalPages;
        List<CityData> data;

    }
    class CityData {
        String name;
        String weather;
        List<String> status;
    }

    public static List<String> weatherStation(String keyword) {
        Gson gson = new Gson();
        List<String> result  = new ArrayList<>();
        String urlPath ="https://jsonmock.hackerrank.com/api/weather/search?name="+keyword;
        WeatherResponse weatherResponse;
        try {
            weatherResponse = getDataWeatherData(urlPath);
        }catch (Exception e ){
            return null;
        }

        for (CityData cityData : weatherResponse.data){

            String cityName = cityData.name;
            String weather = cityData.weather;
            String temperature = weather.split(" ")[0];

            String wind = cityData.status.get(0).split(":")[1].replace("Kmph", "").trim(); // Extracting wind value
            String humidity = cityData.status.get(1).split(":")[1].replace("%", "").trim(); // Extracting humidity value
            result.add(cityName + "," + temperature + "," + wind + "," + humidity);
        }
        return result;
    }

    static WeatherResponse getDataWeatherData(String urlPath) throws IOException {
        URL url = new URL(urlPath);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        br.close();

        String jsonResponse = response.toString();

        Gson gson = new Gson();
        return gson.fromJson(jsonResponse, WeatherResponse.class);
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
