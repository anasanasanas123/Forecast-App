package com.example.anas.forecastapp.model.forecast;

/**
 * Created by anas on 19/05/2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Features {

    @SerializedName("forecast")
    @Expose
    private Integer forecast;

    /**
     *
     * @return
     * The forecast
     */
    public Integer getForecast() {
        return forecast;
    }

    /**
     *
     * @param forecast
     * The forecast
     */
    public void setForecast(Integer forecast) {
        this.forecast = forecast;
    }

}