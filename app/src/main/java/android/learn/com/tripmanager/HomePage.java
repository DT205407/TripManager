package android.learn.com.tripmanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
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

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar_mytrips);
        setSupportActionBar(toolbar);

        if(upcomingtrips.isEmpty())
        {
            upcomingtrips=((DataAdapterTrips) this.getApplication()).getTrips();
            Log.e("Data","Data appended");
        }
        else
        {
            Log.e("Data","Data Not appended");
        }



        /*
        if((tempTrip=(Trip)this.getIntent().getSerializableExtra("newTrip"))!=null){
            upcomingtrips.add(tempTrip);
            Log.e("Data","Data Inserted");
        }
        else {
            Log.e("Data","Data Not Inserted");
        }

        */

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


        getSupportActionBar().setTitle("My Trips");
        //test comment
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container_mytrips);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs_mytrips);
        tabLayout.setupWithViewPager(mViewPager);

        gridview = (GridView) findViewById(R.id.my_upcoming_trips_grid_fragment);


        /*-- commented on 20-04 to add past and upcoming trips
        gridview = (GridView) findViewById(R.id.my_upcoming_trips_grid);
        gridview.setAdapter(new HomePage.TripsAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                tempIntent=new Intent(HomePage.this,TripDetails.class);
                tempIntent.putExtra("TripId",position);
                tempIntent.putExtra("previousscreen","HomePage");
                tempIntent.putExtra("currentscreen","TripDetailsDetailed");
                tempIntent.putExtra("mode","normal");
                //tempIntent.putExtra("Trip",upcomingtrips.get(position));
                tempIntent.putExtra("TripName",upcomingtrips.get(position).name);
                HomePage.this.startActivity(tempIntent);

                HomePage.this.finish();

            }
        });*/

    }
    public static class PlaceholderFragment extends Fragment {

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = null;
            TextView textView;
            GridView gridView;
            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1:
                    // show past trips
                    rootView = inflater.inflate(R.layout.content_home_page, container, false);
                    gridView = (GridView) rootView.findViewById(R.id.my_past_trips_grid_fragment);
                    //gridView.setAdapter(new TripsAdapter(rootView.getContext()));
                    //gridView. .setText("Past Trips");
                case 2:
                    // show current trips
                    rootView = inflater.inflate(R.layout.content_home_page, container, false);
                    gridView = (GridView) rootView.findViewById(R.id.my_upcoming_trips_grid_fragment);
                    //textView.setText("Upcoming Trips");
            }
            return rootView;
        }
    }
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        Trip trips;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            // trips=trip;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position+1);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Log.d("Tabs","Tabs created");
            switch (position) {
                case 0:
                    return getString(R.string.tab4);
                case 1:
                    return getString(R.string.tab5);
            }
            return null;
        }
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
        Intent tempIntent;

        if (id == R.id.contacts) {

            Log.e("Option", "onOptionsItemSelected: contacts selected ");
            tempIntent = new Intent(this, Contacts.class);
            startActivity(tempIntent);

        } else if (id == R.id.stats) {
            tempIntent= new Intent(this, TravelHistoryStats.class);
            startActivity(tempIntent);
        } else if (id == R.id.pastTrips) {
            tempIntent= new Intent(this, MyPastTrips.class);
            startActivity(tempIntent);

        } else if (id == R.id.editProfile) {
            tempIntent= new Intent(this, EditUserProfile.class);
            startActivity(tempIntent);

        } else if (id == R.id.about) {
            tempIntent= new Intent(this, AboutPageTripManager.class);
            startActivity(tempIntent);
        } else if (id == R.id.logout) {

            Log.e("Option", "onOptionsItemSelected: Logout selected ");
            tempIntent= new Intent(this, SignInActivity.class);
            startActivity(tempIntent);

        }else if (id == R.id.nav_send) {
            Toast.makeText(this, "This feature is not available please check later", Toast.LENGTH_SHORT).show();

        }else if (id == R.id.nav_share) {
            Toast.makeText(this, "This feature is not available please check later", Toast.LENGTH_SHORT).show();
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
            /*
            ArrayList<String> temp= new ArrayList<>();
            temp.add("Charger");
            temp.add("Camera");
            temp.add("Torch");

            upcomingtrips.add(new Trip(1,"Trip 1","Dallas","Florida", Trip.formatDate("03/15/2018"),Trip.formatDate("03/25/2018"),"Hey first Trip",Mode.car));
            upcomingtrips.add(new Trip(2,"Trip 2","Dallas","Florida", Trip.formatDate("03/15/2018"),Trip.formatDate("03/25/2018"),"Hey Second Trip",Mode.car,null, temp));
            upcomingtrips.add(new Trip(3,"Trip 3","Dallas","Florida", Trip.formatDate("03/15/2018"),Trip.formatDate("03/25/2018"),"Hey third Trip",Mode.car,null,temp));
            upcomingtrips.add(new Trip(4,"Trip 4","Dallas","Florida", Trip.formatDate("03/15/2018"),Trip.formatDate("03/25/2018"),"Hey fourth Trip",Mode.car));
            upcomingtrips.add(new Trip(5,"Trip 5","Dallas","Florida", Trip.formatDate("03/15/2018"),Trip.formatDate("03/25/2018"),"Hey fifth Trip",Mode.car));*/


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
