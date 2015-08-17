package com.pedroarthursimao.masterapp.retrofit.rest.api;

import com.pedroarthursimao.masterapp.retrofit.rest.pojo.weather.OpenWeatherData;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface GetWeatherApi {

    @GET("/weather")
    void getWeather(@Query("q") String city, Callback<OpenWeatherData> callback);

    @GET("/weather")
    void getWeather(@Query("q") String city);

}
