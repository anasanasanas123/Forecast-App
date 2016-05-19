package com.example.anas.forecastapp.model.forecast;

/**
 * Created by anas on 19/05/2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Simpleforecast {

    @SerializedName("forecastday")
    @Expose
    private List<Forecastday_> forecastday = new ArrayList<Forecastday_>();

    /**
     *
     * @return
     * The forecastday
     */
    public List<Forecastday_> getForecastday() {
        return forecastday;
    }

    /**
     *
     * @param forecastday
     * The forecastday
     */
    public void setForecastday(List<Forecastday_> forecastday) {
        this.forecastday = forecastday;
    }

}
