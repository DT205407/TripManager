package android.learn.com.tripmanager;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class TripDetailsTripAlerts extends AppCompatActivity {
    Trip currenttrip;
    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast?id=524901&APPID=da36dc229f24ee037ecb4a9fc4f43a55&q=";
    //private static String IMG_URL = "http://openweathermap.org/img/w/";

    private TextView cityText;
    private TextView temp;
    private TextView press;
    private TextView windSpeed;

    private TextView hum;
    //private ImageView imgView;


    public String getWeatherData(String location) {
        HttpURLConnection con = null ;
        InputStream is = null;

        try {
            con = (HttpURLConnection) ( new URL(BASE_URL + location)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            // Let's read the response
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while (  (line = br.readLine()) != null )
                buffer.append(line + "\r\n");

            is.close();
            con.disconnect();
            return buffer.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }

        return null;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details_trip_alerts);

        //String city="Rome,it";// get it from DB

        cityText = (TextView) findViewById(R.id.cityText);
        temp = (TextView) findViewById(R.id.tempAvg);
        hum = (TextView) findViewById(R.id.hum);
        press = (TextView) findViewById(R.id.press);
        windSpeed = (TextView) findViewById(R.id.windSpeed);

        Intent i = getIntent();
        currenttrip= ((DataAdapterTrips) this.getApplication()).getTrip(i.getIntExtra("TripId",0),i.getStringExtra("upcomingorpast"));

        cityText.setText(currenttrip.destination);
        String city=currenttrip.destination;

        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(new String[]{city});
    }

    private class JSONWeatherTask extends AsyncTask<String, Void, WeatherList> {
        WeatherList weatherList = new WeatherList();
        @Override
        protected WeatherList doInBackground(String... params) {

            String data = ((new WeatherHttpClient()).getWeatherData(params[0]));

            try {
                weatherList = JSONWeatherParser.getWeather(data);

                // Let's retrieve the icon
                //WeatherList.iconData = ((new WeatherHttpClient()).getImage(weatherList.currentCondition.getIcon()));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weatherList;

        }
        protected  void onPostExecute(WeatherList weatherList)
        {
            super.onPostExecute(weatherList);

            windSpeed.setText(String.valueOf(weatherList.wind.getSpeed()));
            press.setText(String.valueOf(weatherList.currentCondition.getPressure()));
            hum.setText(String.valueOf(weatherList.currentCondition.getHumidity()));
            temp.setText(String.valueOf(weatherList.temperature.getTemp()));
        }
    }
}
