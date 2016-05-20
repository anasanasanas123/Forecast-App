package com.example.anas.forecastapp.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anas.forecastapp.R;
import com.example.anas.forecastapp.model.forecast.Forecast;
import com.example.anas.forecastapp.utils.StringUtils;
import com.squareup.picasso.Picasso;

/**
 * Created by anas on 20/05/2016.
 */

public class ForecastRecyclerViewAdapter extends RecyclerView.Adapter<ForecastRecyclerViewAdapter.ViewHolder> {
    private Forecast forecast;
    private Activity activity;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView todayDay;
        private TextView todayDate;
        private TextView todayMaxWind;
        private TextView todayAvgWind;
        private TextView todayHumidity;
        private TextView todayCondition;
        private TextView todayHighTemperature;
        private TextView todayLowTemperature;
        private ImageView todayIcon;

        public ViewHolder(View v) {
            super(v);
            todayDay = (TextView) v.findViewById(R.id.todayDay);
            todayDate = (TextView) v.findViewById(R.id.todayDate);
            todayMaxWind = (TextView) v.findViewById(R.id.todayMaxWind);
            todayAvgWind = (TextView) v.findViewById(R.id.todayAvgWind);
            todayHumidity = (TextView) v.findViewById(R.id.todayHumidity);
            todayCondition = (TextView) v.findViewById(R.id.todayCondition);
            todayHighTemperature = (TextView) v.findViewById(R.id.todayHighTemperature);
            todayLowTemperature = (TextView) v.findViewById(R.id.todayLowTemperature);
            todayIcon = (ImageView) v.findViewById(R.id.todayIcon);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ForecastRecyclerViewAdapter(Forecast forecast, Activity activity) {
        this.forecast = forecast;
        this.activity = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ForecastRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

//        holder.mTextView.setText(mDataset[position]);

        // set today day
        holder.todayDay.setText(forecast.getForecast().getSimpleforecast().getForecastday().get(position).getDate().getWeekday());

        // set today date
        String strDate = String.format(activity.getResources().getString(R.string.date), forecast.getForecast().getSimpleforecast().getForecastday().get(position).getDate().getDay(), forecast.getForecast().getSimpleforecast().getForecastday().get(position).getDate().getMonthnameShort(), forecast.getForecast().getSimpleforecast().getForecastday().get(position).getDate().getYear());
        holder.todayDate.setText(strDate);

        // set today max wind
        String strMaxWindMsg = String.format(activity.getResources().getString(R.string.max_wind), forecast.getForecast().getSimpleforecast().getForecastday().get(position).getMaxwind().getKph());
        holder.todayMaxWind.setText(strMaxWindMsg);

        // set today avg wind
        String strAvgWindMsg = String.format(activity.getResources().getString(R.string.avg_wind), forecast.getForecast().getSimpleforecast().getForecastday().get(position).getAvewind().getKph());
        holder.todayAvgWind.setText(strAvgWindMsg);

        // set today humidity
        String strHumidity = String.format(activity.getResources().getString(R.string.humidity), forecast.getForecast().getSimpleforecast().getForecastday().get(position).getAvehumidity());
        holder.todayHumidity.setText(strHumidity);

        // set today condition
        holder.todayCondition.setText(forecast.getForecast().getSimpleforecast().getForecastday().get(position).getConditions());

        // set today high temperature
        String strHighTemperature = String.format(activity.getResources().getString(R.string.celsius), forecast.getForecast().getSimpleforecast().getForecastday().get(position).getHigh().getCelsius());
        holder.todayHighTemperature.setText(strHighTemperature);

        // set today low temperature
        String strLowTemperature = String.format(activity.getResources().getString(R.string.celsius), forecast.getForecast().getSimpleforecast().getForecastday().get(position).getLow().getCelsius());
        holder.todayLowTemperature.setText(strLowTemperature);

        // set today condition  icon
        if (!StringUtils.isBlank(forecast.getForecast().getSimpleforecast().getForecastday().get(position).getIconUrl()))
            Picasso.with(activity).load(forecast.getForecast().getSimpleforecast().getForecastday().get(position).getIconUrl()).into(holder.todayIcon);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return forecast.getForecast().getSimpleforecast().getForecastday().size();
    }
}