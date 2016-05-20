package com.example.anas.forecastapp.network;

import com.example.anas.forecastapp.model.forecast.Forecast;
import com.example.anas.forecastapp.utils.Constants;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by anas on 20/05/2016.
 */
public interface ForecastService {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.SERVICE_URI)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    @GET(Constants.FORECASE_URI)
    Call<Forecast> repoContributors();

}
