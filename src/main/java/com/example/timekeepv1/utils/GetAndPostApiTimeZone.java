package com.example.timekeepv1.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetAndPostApiTimeZone {
    public static void MyGETRequest() throws IOException {
        URL urlForGetRequest = new URL("http://worldtimeapi.org/api/timezone/Asia/Vientiane");
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
//        conection.setRequestProperty("userId", "a1bcdef"); // set userId its a sample here
        int responseCode = conection.getResponseCode();


        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in .close();
            // print result
            System.out.println("JSON String Result " + response.toString());
            //GetAndPost.POSTRequest(response.toString());
        } else {
            System.out.println("GET NOT WORKED");
        }

    }
}
