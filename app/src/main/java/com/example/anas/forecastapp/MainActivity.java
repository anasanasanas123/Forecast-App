package com.example.anas.forecastapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.anas.forecastapp.adapter.ForecastRecyclerViewAdapter;
import com.example.anas.forecastapp.model.forecast.Forecast;
import com.example.anas.forecastapp.model.forecast.Forecastday_;
import com.example.anas.forecastapp.network.ForecastService;
import com.example.anas.forecastapp.utils.Constants;
import com.example.anas.forecastapp.utils.SnackbarUtils;
import com.example.anas.forecastapp.utils.StringUtils;
import com.example.anas.forecastapp.utils.Utils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

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

    private TextView todayDay;
    private TextView todayDate;
    private TextView todayMaxWind;
    private TextView todayAvgWind;
    private TextView todayHumidity;
    private TextView todayCondition;
    private TextView todayHighTemperature;
    private TextView todayLowTemperature;
    private ImageView todayIcon;

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

                    setMainLayout(forecast.getForecast().getSimpleforecast().getForecastday().get(0));
                    forecast.getForecast().getSimpleforecast().getForecastday().remove(0);
                    mAdapter = new ForecastRecyclerViewAdapter(forecast, activity);
                    mRecyclerView.setAdapter(mAdapter);

                }
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                if (t != null) {
                    Forecast forecast = getForecastCache(activity);
                    if (forecast != null) {

                        setMainLayout(forecast.getForecast().getSimpleforecast().getForecastday().get(0));
                        forecast.getForecast().getSimpleforecast().getForecastday().remove(0);
                        mAdapter = new ForecastRecyclerViewAdapter(forecast, activity);
                        mRecyclerView.setAdapter(mAdapter);
                        SnackbarUtils.loadSnackBar(activity.getResources().getString(R.string.load_offline_data), activity);

                    } else {
                        setErrorLayoutVisible();
                    }
                } else
                    setErrorLayoutVisible();
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

        todayDay = (TextView) findViewById(R.id.todayDay);
        todayDate = (TextView) findViewById(R.id.todayDate);
        todayMaxWind = (TextView) findViewById(R.id.todayMaxWind);
        todayAvgWind = (TextView) findViewById(R.id.todayAvgWind);
        todayHumidity = (TextView) findViewById(R.id.todayHumidity);
        todayCondition = (TextView) findViewById(R.id.todayCondition);
        todayHighTemperature = (TextView) findViewById(R.id.todayHighTemperature);
        todayLowTemperature = (TextView) findViewById(R.id.todayLowTemperature);
        todayIcon = (ImageView) findViewById(R.id.todayIcon);

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

    public Forecast getForecastCache(Activity activity) {
        SharedPreferences sharedPreference = Utils.getAppSharedPreference(activity);
        String jsonUser = sharedPreference.getString(Constants.DATA_CACHE, null);
        Gson gson = new Gson();
        return gson.fromJson(jsonUser, Forecast.class);
    }

    private void setMainLayout(Forecastday_ forecastday_) {
        loadingLayout.setVisibility(View.GONE);
        internetErrorLayout.setVisibility(View.GONE);
        appBarLayout.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);


        // set today day
        todayDay.setText(forecastday_.getDate().getWeekday());

        // set today date
        String strDate = String.format(getResources().getString(R.string.date), forecastday_.getDate().getDay(), forecastday_.getDate().getMonthnameShort(), forecastday_.getDate().getYear());
        todayDate.setText(strDate);

        // set today max wind
        String strMaxWindMsg = String.format(getResources().getString(R.string.max_wind), forecastday_.getMaxwind().getKph());
        todayMaxWind.setText(strMaxWindMsg);

        // set today avg wind
        String strAvgWindMsg = String.format(getResources().getString(R.string.avg_wind), forecastday_.getAvewind().getKph());
        todayAvgWind.setText(strAvgWindMsg);

        // set today humidity
        String strHumidity = String.format(getResources().getString(R.string.humidity), forecastday_.getAvehumidity());
        todayHumidity.setText(strHumidity);

        // set today condition
        todayCondition.setText(forecastday_.getConditions());

        // set today high temperature
        String strHighTemperature = String.format(getResources().getString(R.string.celsius), forecastday_.getHigh().getCelsius());
        todayHighTemperature.setText(strHighTemperature);

        // set today low temperature
        String strLowTemperature = String.format(getResources().getString(R.string.celsius), forecastday_.getLow().getCelsius());
        todayLowTemperature.setText(strLowTemperature);

        // set today condition  icon
        if (!StringUtils.isBlank(forecastday_.getIconUrl()))
            Picasso.with(activity).load(forecastday_.getIconUrl()).into(todayIcon);
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
