package com.retweet.rtsm.app;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.Future;

public class XenoCantoService {
    private static String baseUrl = "http://www.xeno-canto.org/api/2/recordings?query=";

    public static GetRequest makeRequest(String queryString) throws UnirestException {
        String urlEncoded = "";

        try {
            urlEncoded = URLEncoder.encode(queryString, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        GetRequest getRequest = Unirest.get(baseUrl + urlEncoded);

        return getRequest;
    }
}
