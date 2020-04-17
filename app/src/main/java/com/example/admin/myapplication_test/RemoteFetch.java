package com.example.admin.myapplication_test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import android.content.Context;

public class RemoteFetch {

    //static double lat = 11.9597239;
    //static double lon = 108.4116525;
    //private static final String OPEN_WEATHER_MAP_API =
        //    "http://api.openweathermap.org/data/2.5/weather?lat=11.9597239&lon=108.4116525&units=metric&appid=6ddc87bd83dcb22e368fa4d8b2655483";


    public static JSONObject getJSON(Context context, String city, double lat, double lon){

        String OPEN_WEATHER_MAP_API = "http://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+
                "&units=metric&appid=6ddc87bd83dcb22e368fa4d8b2655483";

        try {
            URL url = new URL(String.format(OPEN_WEATHER_MAP_API, city));
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

           // connection.addRequestProperty("x-api-key", context.getString(R.string.open_weather_maps_app_id));

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer json = new StringBuffer(1024);
            String tmp = "";
            while((tmp = bufferedReader.readLine()) != null){
                json.append(tmp).append("\n");

            }
            bufferedReader.close();

            JSONObject data = new JSONObject(json.toString());

            if(data.getInt("cod") != 200){
                return null;
            }
            return data;
        }catch (Exception e){
            return null;
        }
    }
}
