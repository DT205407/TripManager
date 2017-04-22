package android.learn.com.tripmanager;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MyPastTrips extends AppCompatActivity {

    //size of trips is static but this has to be set after fetching data from database
    public ArrayList<Trip> trips=null;
    GridView gridview;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    //Below code is to show toolbar and layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_trips);

        Log.d("Tabs","ooncreate created");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.menu1);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        gridview = (GridView) findViewById(R.id.my_past_trips_grid);
        gridview.setAdapter(new TripsAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(MyPastTrips.this, "" + trips.get(position).id,
                        Toast.LENGTH_SHORT).show();
            }
        });


        mViewPager.setCurrentItem(1,true);

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
            MyPastTrips.PlaceholderFragment fragment = new MyPastTrips.PlaceholderFragment();
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
                    rootView = inflater.inflate(R.layout.activity_my_trips, container, false);
                    //gridView = (GridView) rootView.findViewById(R.id.my_past_trips_grid_fragment);
                    //gridView. .setText("Past Trips");
                case 2:
                    // show current trips
                    rootView = inflater.inflate(R.layout.activity_my_trips, container, false);
                    //gridView = (GridView) rootView.findViewById(R.id.my_upcoming_trips_grid_fragment);
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
                    return getString(R.string.tab1);
                case 1:
                    return getString(R.string.tab2);
            }
            return null;
        }
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
