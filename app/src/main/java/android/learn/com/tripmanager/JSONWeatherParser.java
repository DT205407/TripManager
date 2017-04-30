package android.learn.com.tripmanager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Shraddhapiparia on 4/20/2017.
 */

public class JSONWeatherParser {

    public static WeatherList getWeather(String data) throws JSONException  {


        // We create out JSONObject from the data
        JSONObject jObj = new JSONObject(data);
        WeatherList weatherList = new WeatherList();

        JSONArray jArr = jObj.getJSONArray("list");
        JSONObject JSONWeather = jArr.getJSONObject(0);
        JSONObject mainObj = getObject("main", JSONWeather);

        //Humidity and Pressure
        weatherList.currentCondition.setHumidity(getInt("humidity", mainObj));
        weatherList.currentCondition.setPressure(getInt("pressure", mainObj));

        //Temperature
        weatherList.temperature.setMaxTemp(getFloat("temp_max", mainObj));
        weatherList.temperature.setMinTemp(getFloat("temp_min", mainObj));
        weatherList.temperature.setTemp(getFloat("temp", mainObj));

        // Wind
        JSONObject wObj = getObject("wind", JSONWeather);
        weatherList.wind.setSpeed(getFloat("speed", wObj));

        //weatherIcon
        JSONObject iVal= JSONWeather.getJSONArray("weather").getJSONObject(0);
        weatherList.weather.setIcon(getString("icon",iVal));
        weatherList.weather.setIconDesc(getString("description",iVal));

        return weatherList;
    }


    private static JSONObject getObject(String tagName, JSONObject jObj)  throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }

    private static float  getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(tagName);
    }

    private static int  getInt(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getInt(tagName);
    }

}