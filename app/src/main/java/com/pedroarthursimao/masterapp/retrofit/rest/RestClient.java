package com.pedroarthursimao.masterapp.retrofit.rest;

import com.pedroarthursimao.masterapp.retrofit.rest.api.GetWeatherApi;

import retrofit.RestAdapter;

public class RestClient {

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5";
    private GetWeatherApi getWeatherApi;

    public RestClient(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                .build();
        getWeatherApi = restAdapter.create(GetWeatherApi.class);
    }

    public GetWeatherApi getClient() {
        return getWeatherApi;
    }
}
