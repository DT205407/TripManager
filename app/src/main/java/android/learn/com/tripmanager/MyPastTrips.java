package android.learn.com.tripmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MyPastTrips extends AppCompatActivity {

    //size of trips is static but this has to be set after fetching data from database
    public ArrayList<Trip> trips=null;
    GridView gridview;
    //Below code is to show toolbar and layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_past_trips);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.menu1);


        gridview = (GridView) findViewById(R.id.my_past_trips_grid);
        gridview.setAdapter(new TripsAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(MyPastTrips.this, "" + trips.get(position).id,
                        Toast.LENGTH_SHORT).show();
            }
        });


    }




    public Date formatDate(String strdate)
    {
        Date date=new Date();
        try {
            date = new SimpleDateFormat("mm/dd/yyyy").parse(strdate);
        }
        catch (ParseException e) {

        }
        return date;
    }

    //This code is to overlay menu items on action bar
    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    //below code to perform appropriate actions when user hits item in action bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Intent createNewTrip=new Intent(this,CreateTrip.class);
                startActivity(createNewTrip);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                Toast.makeText(this, "Invalid Option selected", Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);

        }
    }


    class TripsAdapter extends BaseAdapter{
        private Context mcontext;
        public TripsAdapter(Context c) {
            mcontext=c;
            trips=new ArrayList<Trip>();
            trips.add(new Trip(1,"Trip 1","Dallas","Florida", formatDate("03/15/2018"),formatDate("03/25/2018"),"Hey first Trip",Mode.car,null,null));
            trips.add(new Trip(2,"Trip 2","Dallas","Florida", formatDate("03/15/2018"),formatDate("03/25/2018"),"Hey Second Trip",Mode.car,null,null));
            trips.add(new Trip(3,"Trip 3","Dallas","Florida", formatDate("03/15/2018"),formatDate("03/25/2018"),"Hey third Trip",Mode.car,null,null));
            trips.add(new Trip(4,"Trip 4","Dallas","Florida", formatDate("03/15/2018"),formatDate("03/25/2018"),"Hey fourth Trip",Mode.car,null,null));
            trips.add(new Trip(5,"Trip 5","Dallas","Florida", formatDate("03/15/2018"),formatDate("03/25/2018"),"Hey fifth Trip",Mode.car,null,null));
        }

        @Override
        public int getCount() {
            return trips.size();
        }

        @Override
        public Object getItem(int position) {
            return trips.get(position);
        }

        //look on this how to modify
        @Override
        public long getItemId(int position) {
            return trips.get(position).id;
        }

        class TileHolder{
            TextView tile;
            public TileHolder(View view){
                tile=(TextView) view.findViewById(R.id.tileid);

            }
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=convertView;
            TileHolder tileholder=null;
            if (view==null){
                LayoutInflater inflater=(LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view=inflater.inflate(R.layout.single_tile_in_grid,parent,false);
                tileholder=new TileHolder(view);
                view.setTag(tileholder);
            }
            else{
                tileholder=(TileHolder) view.getTag();
            }
            Trip temp=trips.get(position);
            tileholder.tile.setText(temp.name);
            return view;
        }
    }
}
