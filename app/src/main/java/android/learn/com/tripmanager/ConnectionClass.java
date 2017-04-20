package android.learn.com.tripmanager;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Gopikrishna on 4/18/2017.
 */

public class ConnectionClass {

    //website: https://my.gearhost.com/Databases/Details/projects1
    //userid: travelmanager5933@gmail.com
    String ip = "mssql5.gear.host";
    String classs = "net.sourceforge.jtds.jdbc.Driver";
    String db = "projects1";
    String un = "projects1";
    String password = "Op5B7F-2Zy?D";


    @SuppressLint("NewApi")
    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;
        try {

            Class.forName(classs);
            ConnURL = "jdbc:jtds:sqlserver://" + ip + ";"
                    + "databaseName=" + db + ";user=" + un + ";password="
                    + password + ";";
            conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO", "Class not found");
            Log.e("ERRO", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        }
        return conn;
    }

}
