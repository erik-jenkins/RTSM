package com.retweet.rtsm.app;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class XenoCantoService {
    private static String baseUrl = "http://www.xeno-canto.org/api/2/recordings?query=";

    public static String makeRequest(String queryString) throws UnirestException {
        HttpResponse<String> response = Unirest.get(baseUrl + queryString)
                .asString();

        return response.getBody();
    }
}
