package com.retweet.rtsm.app;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class XenoCantoService {
    private static String baseUrl = "http://www.xeno-canto.org/api/2/recordings?query=";

    public static String makeRequest(String queryString) throws UnirestException {
        String urlEncoded = "";

        try {
            urlEncoded = URLEncoder.encode(queryString, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpResponse<String> response = Unirest.get(baseUrl + urlEncoded)
                .asString();

        return response.getBody();
    }
}
