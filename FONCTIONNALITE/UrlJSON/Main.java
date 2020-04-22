import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.*;

//from  w  ww  .  java  2  s  . c o m
public class Main {
  public static void main(String[] args) {
    String getjson = jsonGetRequest("https://world.openfoodfacts.org/api/v0/product/8002470023154.json");

    JSONObject json = new JSONObject(getjson);

    System.out.println(json.getJSONObject("product").getString("product_name_fr"));
  }

  private static String streamToString(InputStream inputStream) {
    String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
    return text;
  }

  public static String jsonGetRequest(String urlQueryString) {
    String json = null;
    try {
      URL url = new URL(urlQueryString);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setDoOutput(true);
      connection.setInstanceFollowRedirects(false);
      connection.setRequestMethod("GET");
      connection.setRequestProperty("Content-Type", "application/json");
      connection.setRequestProperty("charset", "utf-8");
      connection.connect();
      InputStream inStream = connection.getInputStream();
      json = streamToString(inStream); // input stream to string
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return json;
  }

}