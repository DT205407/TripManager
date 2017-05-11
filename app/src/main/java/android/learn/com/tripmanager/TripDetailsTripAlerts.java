package android.learn.com.tripmanager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;


public class TripDetailsTripAlerts extends AppCompatActivity {
    Trip currenttrip;
    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast?id=524901&APPID=da36dc229f24ee037ecb4a9fc4f43a55&q=";
    //private static String IMG_URL = "http://openweathermap.org/img/w/";

    private TextView cityText;
    private TextView imgDesc;
    private ImageView imgView;

    private PieChart mChart1,mChart2,mChart3;
    private float[] yData=new float[2];



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
        View alertsView= this.getWindow().getDecorView();
        alertsView.setBackgroundColor(Color.rgb(93,138,168));
        cityText = (TextView) findViewById(R.id.cityText);
        imgView = (ImageView) findViewById(R.id.condIcon);
        imgDesc= (TextView) findViewById(R.id.iconDescription);

        mChart1 = (PieChart) findViewById(R.id.chart1);
        mChart2 = (PieChart) findViewById(R.id.chart2);
        mChart3 = (PieChart) findViewById(R.id.chart3);

        //configure pie chart
        mChart1.setUsePercentValues(true);
        mChart1.setDescription(" ");
        mChart2.setUsePercentValues(true);
        mChart2.setDescription(" ");
        mChart3.setUsePercentValues(true);
        mChart3.setDescription(" ");

        //enable hole and configure
        mChart1.setDrawHoleEnabled(true);
        mChart1.setHoleColor(Color.rgb(93,138,168));
        mChart1.setHoleRadius(80);
        mChart1.setTransparentCircleRadius(80);
        mChart2.setDrawHoleEnabled(true);
        mChart2.setHoleColor(Color.rgb(93,138,168));
        mChart2.setHoleRadius(80);
        mChart2.setTransparentCircleRadius(80);
        mChart3.setDrawHoleEnabled(true);
        mChart3.setHoleColor(Color.rgb(93,138,168));
        mChart3.setHoleRadius(80);
        mChart3.setTransparentCircleRadius(80);

        //enable rotation by touch
        mChart1.setRotationAngle(0);
        mChart1.setRotationEnabled(true);
        mChart2.setRotationAngle(0);
        mChart2.setRotationEnabled(true);
        mChart3.setRotationAngle(0);
        mChart3.setRotationEnabled(true);


        mChart1.getLegend().setEnabled(false);
        mChart2.getLegend().setEnabled(false);
        mChart3.getLegend().setEnabled(false);
        //mChart1.setDescription("Temp");
        mChart1.setCenterText("Temperature");
        mChart1.setCenterTextColor(Color.WHITE);
        mChart2.setCenterText("Pressure");
        mChart2.setCenterTextColor(Color.WHITE);
        mChart3.setCenterText("Humidity");
        mChart3.setCenterTextColor(Color.WHITE);
        //set a chart value select listener
        mChart1.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, int i, Highlight highlight) {
                //display message when value selected

                if(entry==null)
                {
                    return;
                }
                Toast.makeText(TripDetailsTripAlerts.this,"Temperature ="+ entry.getVal(),
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected() {

            }
        });
        mChart2.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, int i, Highlight highlight) {
                if(entry==null)
                    return;
                Toast.makeText(TripDetailsTripAlerts.this,"Pressure ="+ entry.getVal(),
                       Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
        mChart3.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, int i, Highlight highlight) {
                if(entry==null)
                    return;
                Toast.makeText(TripDetailsTripAlerts.this,"Humidity ="+ entry.getVal()+"%",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

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
                weatherList.iconData=((new WeatherHttpClient()).getImage(weatherList.weather.getIcon()));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weatherList;

        }
        protected  void onPostExecute(WeatherList weatherList)
        {
            super.onPostExecute(weatherList);

            if (weatherList.iconData != null && weatherList.iconData.length > 0) {
                Bitmap img = BitmapFactory.decodeByteArray(weatherList.iconData, 0, weatherList.iconData.length);
                imgView.setImageBitmap(img);
            }

            ArrayList<Entry> yVal1=new ArrayList<Entry>();
            ArrayList<Entry> yVal2=new ArrayList<Entry>();
            ArrayList<Entry> yVal3=new ArrayList<Entry>();

            yVal1.add(new Entry(weatherList.temperature.getTemp(), 0));
            yVal1.add(new Entry(100-weatherList.temperature.getTemp(), 1));

            yVal2.add(new Entry(weatherList.currentCondition.getPressure(), 0));
            yVal2.add(new Entry(100-weatherList.currentCondition.getPressure(), 1));

            yVal3.add(new Entry(weatherList.currentCondition.getHumidity(), 0));
            yVal3.add(new Entry(100-weatherList.currentCondition.getHumidity(), 1));

            imgDesc.setText(weatherList.weather.getIconDesc());

            ArrayList<String > xVal1=new ArrayList<String>();
            xVal1.add(" ");
            xVal1.add(" ");
            ArrayList<String > xVal2=new ArrayList<String>();
            xVal2.add("");
            xVal2.add("");
            ArrayList<String > xVal3=new ArrayList<String>();
            xVal3.add("");
            xVal3.add("");
            /*for(int i=0;i<xData.length;i++)
                xVal1.add(xData[i]); */

            //create pie data set
            PieDataSet pieDataSet1=new PieDataSet(yVal1,"Temperature");
            pieDataSet1.setSliceSpace(3);
            pieDataSet1.setSelectionShift(5);
            PieDataSet pieDataSet2=new PieDataSet(yVal2,"Pressure");
            pieDataSet2.setSliceSpace(3);
            pieDataSet2.setSelectionShift(5);
            PieDataSet pieDataSet3=new PieDataSet(yVal3,"Humidity");
            pieDataSet3.setSliceSpace(3);
            pieDataSet3.setSelectionShift(5);

            //add colors
            ArrayList<Integer> colors=new ArrayList<Integer>();
                colors.add(Color.LTGRAY);
                colors.add(Color.DKGRAY);

            colors.add(ColorTemplate.getHoloBlue());
            pieDataSet1.setColors(colors);
            pieDataSet2.setColors(colors);
            pieDataSet3.setColors(colors);


            //Instantiate pie data object now
            PieData data1=new PieData(xVal1,pieDataSet1);
            data1.setValueFormatter(new PercentFormatter());
            data1.setValueTextSize(1l);
            //data1.setv
            data1.setValueTextColor(Color.GRAY);
            PieData data2=new PieData(xVal2,pieDataSet2);
            data2.setValueFormatter(new PercentFormatter());
            data2.setValueTextSize(1l);
            data2.setValueTextColor(Color.GRAY);
            PieData data3=new PieData(xVal3,pieDataSet3);
            data3.setValueFormatter(new PercentFormatter());
            data3.setValueTextSize(1l);
            data3.setValueTextColor(Color.GRAY);

            mChart1.setData(data1);
            mChart2.setData(data2);
            mChart3.setData(data3);
            //undo all highlights
            mChart1.highlightValues(null);
            mChart2.highlightValues(null);
            mChart3.highlightValues(null);
            //update pie chart
            mChart1.invalidate();
            mChart2.invalidate();
            mChart3.invalidate();
        }
    }
}
