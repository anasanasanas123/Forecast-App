package com.example.anas.forecastapp.model.forecast;

/**
 * Created by anas on 19/05/2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SnowNight {

    @SerializedName("in")
    @Expose
    private Double in;
    @SerializedName("cm")
    @Expose
    private Double cm;

    /**
     *
     * @return
     * The in
     */
    public Double getIn() {
        return in;
    }

    /**
     *
     * @param in
     * The in
     */
    public void setIn(Double in) {
        this.in = in;
    }

    /**
     *
     * @return
     * The cm
     */
    public Double getCm() {
        return cm;
    }

    /**
     *
     * @param cm
     * The cm
     */
    public void setCm(Double cm) {
        this.cm = cm;
    }

}
