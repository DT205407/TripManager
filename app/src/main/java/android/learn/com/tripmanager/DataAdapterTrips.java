package android.learn.com.tripmanager;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Gopikrishna on 4/3/2017.
 */

public class DataAdapterTrips extends Application {
    private ArrayList<Trip> upcomingtrips=new ArrayList<Trip>();

    public ArrayList<Trip> getTrips() {

        if(upcomingtrips.isEmpty()) {
            ArrayList<String> temp = new ArrayList<>();
            temp.add("Charger");
            temp.add("Camera");
            temp.add("Torch");

            upcomingtrips.add(new Trip(1, "Trip 1", "Dallas", "Florida", Trip.formatDate("03/15/2018"), Trip.formatDate("03/25/2018"), "Hey first Trip", Mode.car));
            upcomingtrips.add(new Trip(2, "Trip 2", "Dallas", "Florida", Trip.formatDate("03/15/2018"), Trip.formatDate("03/25/2018"), "Hey Second Trip", Mode.car, null, temp));
            upcomingtrips.add(new Trip(3, "Trip 3", "Dallas", "Florida", Trip.formatDate("03/15/2018"), Trip.formatDate("03/25/2018"), "Hey third Trip", Mode.car, null, temp));
            upcomingtrips.add(new Trip(4, "Trip 4", "Dallas", "Florida", Trip.formatDate("03/15/2018"), Trip.formatDate("03/25/2018"), "Hey fourth Trip", Mode.car));
            upcomingtrips.add(new Trip(5, "Trip 5", "Dallas", "Florida", Trip.formatDate("03/15/2018"), Trip.formatDate("03/25/2018"), "Hey fifth Trip", Mode.car));

            Log.e("Data", "Data appended in application file");
        }
        else{
            Log.e("Data", "Data not appended in application file");
        }

        return upcomingtrips;
    }

    public Trip getTrip(int id){
        return upcomingtrips.get(id);
    }

    public void addUpcomingTrips(Trip tempTrip){
        tempTrip.id=upcomingtrips.size()+1;
        this.upcomingtrips.add(tempTrip);
    }

}
