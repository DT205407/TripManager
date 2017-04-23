package android.learn.com.tripmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TripDetailsFriends extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details_friends);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FloatingActionButton tripaddfriend = (FloatingActionButton) findViewById(R.id.addfriend);
        tripaddfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent temp=new Intent(view.getContext(),TripDetailsFriends.class);
                temp.putExtra("TripId",getIntent().getIntExtra("TripId",0));
                temp.putExtra("mode","edit");
                startActivity(temp);
            }
        });

        FloatingActionButton  tripsavefriend= (FloatingActionButton) findViewById(R.id.savenewfriend);
        tripsavefriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent temp=new Intent(view.getContext(),TripDetailsFriends.class);
                temp.putExtra("TripId",getIntent().getIntExtra("TripId",0));
                temp.putExtra("mode","normal");
                startActivity(temp);
            }
        });

        if(getIntent().getStringExtra("mode").equals("normal"))
        {
            ((EditText) findViewById(R.id.addnewfriend)).setVisibility(View.GONE);
            tripsavefriend.setVisibility(View.GONE);
            tripaddfriend.setVisibility(View.VISIBLE);


        }
        //if it is TripDetailsEdit then edittrip screen is displayed
        else
        {
            ((EditText) findViewById(R.id.addnewfriend)).setVisibility(View.VISIBLE);
            tripsavefriend.setVisibility(View.VISIBLE);
            tripaddfriend.setVisibility(View.GONE);

        }

        Trip trip=((DataAdapterTrips) this.getApplication()).getTrip(getIntent().getIntExtra("TripId",0));

        List<String> temp = new ArrayList<String>();
        if (trip.tripFriends.isEmpty()) {
            temp.add("There is nothing yet! Click Add button to add more");
        } else {
            temp = trip.tripFriends;
        }

        ArrayAdapter adapter1 = new ArrayAdapter<String>(this, R.layout.fragment_tripdetails_checklist_singleelement, temp);
        ((ListView) findViewById(R.id.tripdetails_friends_listview)).setAdapter(adapter1);


    }

}
