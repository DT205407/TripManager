package android.learn.com.tripmanager;

import android.app.Application;
import android.util.Log;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by Gopikrishna on 4/3/2017.
 */

public class DataAdapterTrips extends Application {
    private static ArrayList<Trip> upcomingtrips=new ArrayList<Trip>();


    public ArrayList<Trip> getTrips() {

        if(upcomingtrips.isEmpty()) {
            gettripssqlserver();
        }
        else{
        }

        return upcomingtrips;
    }

    public Trip getTrip(int id){
        return upcomingtrips.get(id);
}

    public String getTotalTrips() {

        int no_of_upcoming_trips = upcomingtrips.size();
        int no_of_past_trips = 5;
        String no_of_trips = String.valueOf(no_of_past_trips + no_of_upcoming_trips);

        return no_of_trips;
    }
    public String getTotalDays() {
        long no_of_days = 0;
        for (Trip tempTrip : upcomingtrips) {
            Date stDate = tempTrip.startdate;
            Date endDate = tempTrip.enddate;
            no_of_days += endDate.getTime() - endDate.getTime();
            Log.d("startdate---",String.valueOf(stDate.getTime()));
            Log.d("enddate---",String.valueOf(endDate.getTime()));
        }
        return String.valueOf(no_of_days);
    }
    public String getTotalCities() {

        Set<List<String>> citySet = new HashSet<List<String>>();

        for (Trip tempTrip : upcomingtrips) {
            citySet.add(Arrays.asList(tempTrip.destination));
        }

        return String.valueOf(citySet.size());
    }
    public String getTotalDistance(){

        String distance="2334 Miles";
        return distance;
    }

    public void addUpcomingTrips(Trip tempTrip){
        tempTrip.id=upcomingtrips.size()+1;
        this.upcomingtrips.add(tempTrip);


    }


    public void  gettripssqlserver(){
        String z=new String();
        try {
            Connection con = new ConnectionClass().CONN();
            if (con == null) {
                z = "Error in connection with SQL server";
            } else {
                String query = "select * from tblTrips";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                ArrayList<String> temp = new ArrayList<>();
                temp.add("Charger");
                temp.add("Camera");
                temp.add("Torch");

                ArrayList<String> tempItenary = new ArrayList<>();
                tempItenary.add("Today\n\nGo to Restaurent and meet client");
                tempItenary.add("Tommorow\n\nGo to beach");

                while(rs.next())
                {

                    z = "Login successfull";
                    upcomingtrips.add(
                    new Trip(
                            rs.getInt("Id"),
                            rs.getString("name"),
                            rs.getString("sourcecity"),
                            rs.getString("destinationcity"),
                            Trip.formatDate(rs.getString("startdate")),
                            Trip.formatDate(rs.getString("enddate")),
                            rs.getString("tripdescription"),
                            rs.getString("mode"),
                            tempItenary,
                            temp
                    ));
                }
                Log.e("Sample Data",String.valueOf(upcomingtrips.size()));

            }
        }
        catch (Exception ex)
        {
            z = "Exceptions";
            ArrayList<String> temp = new ArrayList<>();
            temp.add("Charger");
            temp.add("Camera");
            temp.add("Torch");
            ArrayList<String> friends = new ArrayList<>();
            friends.add("Gopi");
            friends.add("Shraddha");
            friends.add("Aditya");
            upcomingtrips.add(new Trip(1, "Trip 1", "Dallas", "Florida", Trip.formatDate("03/15/2018"), Trip.formatDate("03/25/2018"), "Hey first Trip", Mode.car));
            upcomingtrips.add(new Trip(2, "Trip 2", "Dallas", "Florida", Trip.formatDate("03/15/2018"), Trip.formatDate("03/25/2018"), "Hey Second Trip", Mode.car, null, temp,friends));
            upcomingtrips.add(new Trip(3, "Trip 3", "Dallas", "Florida", Trip.formatDate("03/15/2018"), Trip.formatDate("03/25/2018"), "Hey third Trip", Mode.car, null, temp,friends));
            upcomingtrips.add(new Trip(4, "Trip 4", "Dallas", "Florida", Trip.formatDate("03/15/2018"), Trip.formatDate("03/25/2018"), "Hey fourth Trip", Mode.car));
            upcomingtrips.add(new Trip(5, "Trip 5", "Dallas", "Florida", Trip.formatDate("03/15/2018"), Trip.formatDate("03/25/2018"), "Hey fifth Trip", Mode.car));
        }
        finally {
            Log.e("Sql server Message",z);
    }
    }

    public void insertTripinDatabase(Trip trip){
        String z=new String();
        try {
            Connection con = new ConnectionClass().CONN();
            if (con == null) {
                z = "Error in connection with SQL server";
            } else {

                String query = "INSERT INTO [dbo].[tblTrips]\n" +
                        "           ([Id]\n" +
                        "           ,[name]\n" +
                        "           ,[sourcecity]\n" +
                        "           ,[destinationcity]\n" +
                        "           ,[startdate]\n" +
                        "           ,[enddate]\n" +
                        "           ,[tripdescription]\n" +
                        "           ,[mode])\n" +
                        "     VALUES ("+trip.id+",'"+trip.name+"','"+trip.source+"','"+trip.destination+"','"+Trip.datetostring(trip.startdate)+"','"+Trip.datetostring(trip.enddate)+"','"+trip.description+"','"+trip.mode+"')";
                Statement stmt = con.createStatement();

                stmt.executeUpdate(query);
                Log.e("Sample Data",query);
                Log.e("Sample Data","Succesfully inserted");

            }
        }
        catch (Exception ex)
        {
            z = "Exceptions";
        }
        finally {
            Log.e("Sql server Message",z);
        }
    }

    //we have to write a function that can encode arraylist to string and them decode back
    private static void fromString( String s ) throws IOException,
            ClassNotFoundException {

    }
}
