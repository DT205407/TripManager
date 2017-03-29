package android.learn.com.tripmanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<Trip> upcomingtrips=new ArrayList<Trip>();
    Trip tempTrip=new Trip();
    GridView gridview;
    Intent tempIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if((tempTrip=(Trip)this.getIntent().getSerializableExtra("newTrip"))!=null){
            upcomingtrips.add(tempTrip);
            Log.e("Data","Data Inserted");
        }
        else {
            Log.e("Data","Data Not Inserted");
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent createNewTrip=new Intent(view.getContext(),CreateTrip.class);
                startActivity(createNewTrip);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        getSupportActionBar().setTitle("TM");
        //test comment


        gridview = (GridView) findViewById(R.id.my_upcoming_trips_grid);
        gridview.setAdapter(new HomePage.TripsAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                tempIntent=new Intent(HomePage.this,TripDetails.class);
                tempIntent.putExtra("Trip",upcomingtrips.get(position));
                tempIntent.putExtra("TripName",upcomingtrips.get(position).name);
                HomePage.this.startActivity(tempIntent);

                HomePage.this.finish();

            }
        });

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.contacts) {
            // Handle the camera action
        } else if (id == R.id.stats) {

        } else if (id == R.id.pastTrips) {

        } else if (id == R.id.editProfile) {

        } else if (id == R.id.about) {

        } else if (id == R.id.logout) {

        }else if (id == R.id.nav_send) {

        }else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //This class used to overlay text on top of images in gridview
    class TripsAdapter extends BaseAdapter {
        private Context mcontext;
        public TripsAdapter(Context c) {
            mcontext=c;
            ArrayList<String> temp= new ArrayList<>();
            temp.add("Charger");
            temp.add("Camera");
            temp.add("Torch");

            upcomingtrips.add(new Trip(1,"Trip 1","Dallas","Florida", Trip.formatDate("03/15/2018"),Trip.formatDate("03/25/2018"),"Hey first Trip",Mode.car));
            upcomingtrips.add(new Trip(2,"Trip 2","Dallas","Florida", Trip.formatDate("03/15/2018"),Trip.formatDate("03/25/2018"),"Hey Second Trip",Mode.car,null, temp));
            upcomingtrips.add(new Trip(3,"Trip 3","Dallas","Florida", Trip.formatDate("03/15/2018"),Trip.formatDate("03/25/2018"),"Hey third Trip",Mode.car,null,temp));
            upcomingtrips.add(new Trip(4,"Trip 4","Dallas","Florida", Trip.formatDate("03/15/2018"),Trip.formatDate("03/25/2018"),"Hey fourth Trip",Mode.car));
            upcomingtrips.add(new Trip(5,"Trip 5","Dallas","Florida", Trip.formatDate("03/15/2018"),Trip.formatDate("03/25/2018"),"Hey fifth Trip",Mode.car));
        }

        @Override
        public int getCount() {
            return upcomingtrips.size();
        }

        @Override
        public Object getItem(int position) {
            return upcomingtrips.get(position);
        }

        //look on this how to modify
        @Override
        public long getItemId(int position) {
            return upcomingtrips.get(position).id;
        }

        class TileHolder{
            TextView tile;
            public TileHolder(View view){
                tile=(TextView) view.findViewById(R.id.tileidtext);

            }
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=convertView;
            TileHolder tileholder=null;
            if (view==null){
                LayoutInflater inflater=(LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view=inflater.inflate(R.layout.single_tile_in_grid,parent,false);
                tileholder=new TripsAdapter.TileHolder(view);
                view.setTag(tileholder);
            }
            else{
                tileholder=(TileHolder) view.getTag();
            }
            Trip temp=upcomingtrips.get(position);
            tileholder.tile.setText(temp.name);
            return view;
        }
    }
}
