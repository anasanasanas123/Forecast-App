package com.example.anas.forecastapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.anas.forecastapp.adapter.ForecastRecyclerViewAdapter;
import com.example.anas.forecastapp.model.forecast.Forecast;
import com.example.anas.forecastapp.network.ForecastService;
import com.example.anas.forecastapp.utils.Constants;
import com.example.anas.forecastapp.utils.SnackbarUtils;
import com.example.anas.forecastapp.utils.Utils;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getName();

    private Activity activity;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private AppBarLayout appBarLayout;
    private RelativeLayout loadingLayout;
    private RelativeLayout internetErrorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();

        CallForecastService();
    }

    /**
     * Get forecast json data and serialize it to ForeCase Object
     */
    public void CallForecastService() {
        ForecastService forecastService = ForecastService.retrofit.create(ForecastService.class);
        final Call<Forecast> call = forecastService.repoContributors();

        call.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {

                if (response != null) {
                    Forecast forecast = response.body();
                    setForecastCache(forecast, activity);
                    Log.i(TAG, response.toString());
                    // specify an adapter (see also next example)
                    mAdapter = new ForecastRecyclerViewAdapter(forecast);
                    mRecyclerView.setAdapter(mAdapter);
                    setMainLayoutVisible();
                }
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                if (t != null) {
                    Forecast forecast = getForecaseCache(activity);
                    if (forecast != null) {
                        mAdapter = new ForecastRecyclerViewAdapter(forecast);
                        mRecyclerView.setAdapter(mAdapter);
                        setMainLayoutVisible();
                        SnackbarUtils.loadSnackBar(activity.getResources().getString(R.string.load_offline_data), activity);
                    } else {
                        setErrorLayoutVisible();
                    }

                }
            }

        });
    }

    public void tryAgain(View v) {
        setLoadingLayoutVisible();
        CallForecastService();
    }

    private void init() {

        activity = this;
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        loadingLayout = (RelativeLayout) findViewById(R.id.loadingLayout);
        internetErrorLayout = (RelativeLayout) findViewById(R.id.internetErrorLayout);
    }

    public void setForecastCache(Forecast forecast, Activity activity) {
        SharedPreferences.Editor editor = Utils.getAppSharedPreference(activity).edit();
        Gson gson = new Gson();
        String jsonUser = gson.toJson(forecast);
        editor.putString(Constants.DATA_CACHE, jsonUser);
        editor.apply();
    }

    public Forecast getForecaseCache(Activity activity) {
        SharedPreferences sharedPreference = Utils.getAppSharedPreference(activity);
        String jsonUser = sharedPreference.getString(Constants.DATA_CACHE, null);
        Gson gson = new Gson();
        return gson.fromJson(jsonUser, Forecast.class);
    }

    private void setMainLayoutVisible() {
        loadingLayout.setVisibility(View.GONE);
        internetErrorLayout.setVisibility(View.GONE);
        appBarLayout.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void setLoadingLayoutVisible() {
        loadingLayout.setVisibility(View.VISIBLE);
        internetErrorLayout.setVisibility(View.GONE);
        appBarLayout.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
    }

    private void setErrorLayoutVisible() {
        loadingLayout.setVisibility(View.GONE);
        internetErrorLayout.setVisibility(View.VISIBLE);
        appBarLayout.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
    }
}
