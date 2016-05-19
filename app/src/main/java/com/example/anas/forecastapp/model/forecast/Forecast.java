package com.example.anas.forecastapp.model.forecast;

/**
 * Created by anas on 19/05/2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Forecast {

    @SerializedName("response")
    @Expose
    private Response response;
    @SerializedName("forecast")
    @Expose
    private Forecast_ forecast;

    /**
     *
     * @return
     * The response
     */
    public Response getResponse() {
        return response;
    }

    /**
     *
     * @param response
     * The response
     */
    public void setResponse(Response response) {
        this.response = response;
    }

    /**
     *
     * @return
     * The forecast
     */
    public Forecast_ getForecast() {
        return forecast;
    }

    /**
     *
     * @param forecast
     * The forecast
     */
    public void setForecast(Forecast_ forecast) {
        this.forecast = forecast;
    }

}