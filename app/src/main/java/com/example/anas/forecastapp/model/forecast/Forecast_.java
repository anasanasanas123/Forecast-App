package com.example.anas.forecastapp.model.forecast;

/**
 * Created by anas on 19/05/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Forecast_ {

    @SerializedName("txt_forecast")
    @Expose
    private TxtForecast txtForecast;
    @SerializedName("simpleforecast")
    @Expose
    private Simpleforecast simpleforecast;

    /**
     *
     * @return
     * The txtForecast
     */
    public TxtForecast getTxtForecast() {
        return txtForecast;
    }

    /**
     *
     * @param txtForecast
     * The txt_forecast
     */
    public void setTxtForecast(TxtForecast txtForecast) {
        this.txtForecast = txtForecast;
    }

    /**
     *
     * @return
     * The simpleforecast
     */
    public Simpleforecast getSimpleforecast() {
        return simpleforecast;
    }

    /**
     *
     * @param simpleforecast
     * The simpleforecast
     */
    public void setSimpleforecast(Simpleforecast simpleforecast) {
        this.simpleforecast = simpleforecast;
    }

}