package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPRequests {

    public static String requestSneakerPage(String url) throws IOException {

        java.net.URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional request header
        con.setRequestProperty("User-Agent", "Mozilla 5.0");
        int responseCode = con.getResponseCode();
        System.out.println("Response code: " + responseCode);

        //reads response and converts it to a string
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        String html = response.toString();
        return html;
    }

}
