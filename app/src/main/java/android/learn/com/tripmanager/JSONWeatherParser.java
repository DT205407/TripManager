package android.learn.com.tripmanager;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Shraddhapiparia on 4/20/2017.
 */

public class JSONWeatherParser {

    public static List getWeather(String data) throws JSONException  {


        // We create out JSONObject from the data
        JSONObject jObj = new JSONObject(data);
        List list= new List();

        JSONArray jArr = jObj.getJSONArray("list");
        JSONObject JSONWeather = jArr.getJSONObject(0);
        JSONObject mainObj = getObject("main", JSONWeather);

        //Humidity and Pressure
        list.currentCondition.setHumidity(getInt("humidity", mainObj));
        list.currentCondition.setPressure(getInt("pressure", mainObj));

        //Temperature
        list.temperature.setMaxTemp(getFloat("temp_max", mainObj));
        list.temperature.setMinTemp(getFloat("temp_min", mainObj));
        list.temperature.setTemp(getFloat("temp", mainObj));

        // Wind
        JSONObject wObj = getObject("wind", JSONWeather);
        list.wind.setSpeed(getFloat("speed", wObj));

        return list;
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