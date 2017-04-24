package android.learn.com.tripmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class TravelHistoryStats extends AppCompatActivity {
    TextView cities;
    TextView distance;
    TextView days;
    TextView trips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_history_stats);

        cities= (TextView) findViewById(R.id.totalcities);
        distance=(TextView) findViewById(R.id.totaldistance);
        days=(TextView) findViewById(R.id.totaldays);
        trips=(TextView) findViewById(R.id.totaltrips);

        cities.setText(((DataAdapterTrips) this.getApplication()).getTotalCities());
        days.setText(((DataAdapterTrips) this.getApplication()).getTotalDays());
        distance.setText(((DataAdapterTrips) this.getApplication()).getTotalDistance());
        trips.setText(((DataAdapterTrips) this.getApplication()).getTotalTrips());
        /*Log.d("cities--",ci);
        Log.d("days--",days);
        Log.d("dist--",dist);
        Log.d("trips--",tps);*/

    }
}
