package com.example.anas.forecastapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.anas.forecastapp.model.forecast.Forecast;
import com.example.anas.forecastapp.network.ForecastService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CallForecastService();


    }

    public void CallForecastService() {
        ForecastService forecastService = ForecastService.retrofit.create(ForecastService.class);
        final Call<Forecast> call = forecastService.repoContributors();

        call.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {

                if (response != null) {
                    Forecast forecast = response.body();
                    Log.i(TAG, response.toString());
                }

            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                if (t != null) {
                    Log.i(TAG, t.toString());
                }
            }

        });
    }
}
