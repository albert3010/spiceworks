package test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class SolutionParser {
    public static void main(String[] args) {
        List<String> filters = Arrays.asList("username", "EQUALS", "Wilsonp");
        List<Integer> ans = apiResponseParser(filters, 3);
        System.out.println(ans);
    }


    public static List<Integer> apiResponseParser(List<String> inputList, int size) {

        String url = "https://raw.githubusercontent.com/arcjsonapi/ApiSampleData/master/api/users";
        String key = inputList.get(0);
        String operation = inputList.get(1);
        String val = inputList.get(2);
        List<Integer> ans = new ArrayList<>();
        try {
            JsonArray users = getDataFromAPI(url);
            String[] keys = key.split("\\.");
            String[] values = val.split(",");
            Set<String> setVal = new HashSet<>();
            for (int i = 0; i < values.length; i++) {
                setVal.add(values[i].toLowerCase());
            }
            int l = users.size();


            for (int i = 0; i < l; i++) {
                JsonObject jsonObject = users.get(i).getAsJsonObject();
                int k = keys.length;
                String val_ = "";
                if (k == 1) {
                    val_ = jsonObject.get(keys[0]).getAsString();
                }
                if (k == 2) {
                    val_ = jsonObject.get(keys[0]).getAsJsonObject().get(keys[1]).getAsString();
                }
                if (k == 3) {
                    val_ = jsonObject.get(keys[0]).getAsJsonObject().get(keys[1]).getAsJsonObject().get(keys[2]).getAsString();
                }

                if (setVal.contains(val_.toLowerCase())) {
                    ans.add(jsonObject.get("id").getAsInt());
                }
            }
        } catch (Exception e) {

        }
        System.out.println(inputList.get(0));
        if (ans.size() == 0) {
            ans.add(-1);
        }
        return ans;

    }


    public static JsonArray getDataFromAPI(String apiUrl) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        String jsonString = result.toString();

        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(jsonString);
        JsonArray jsonArray = jsonElement.getAsJsonArray();

        return jsonArray;
    }

}
