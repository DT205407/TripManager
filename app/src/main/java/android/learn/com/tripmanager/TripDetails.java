/*balance work
1. underline text in trip details
2. Add box to description text

*/
package android.learn.com.tripmanager;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TripDetails extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    //Trip tempTrip=new Trip(2,"Trip 2","Dallas","Florida", Trip.formatDate("03/15/2018"),Trip.formatDate("03/25/2018"),"Hey Second Trip",Mode.car,new ArrayList<String>(), new ArrayList<String>());
    Trip tempTrip;



    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent i = getIntent();

        tempTrip=(Trip) i.getSerializableExtra("Trip");
        //tempTrip.checklist.add("Charger");
        //tempTrip.checklist.add("Torch");
        //tempTrip.checklist.add("Calculator");
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle(tempTrip.name);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),tempTrip);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //this is to set Trip detail tab as default view
        mViewPager.setCurrentItem(1,false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_trip_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Intent tempintent;
        //noinspection SimplifiableIfStatement
        if (id == R.id.maps) {
            tempintent=new Intent(this,TripDetailsMapsActivity.class);
            tempintent.putExtra("sourcecity",tempTrip.source);
            tempintent.putExtra("destinationcity",tempTrip.destination);
            startActivity(tempintent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //fix to back button redirected to signup screeen
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,HomePage.class));
    }

    /**
     * A placeholder fragment containing a simple view.
     */
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
        public static PlaceholderFragment newInstance(int sectionNumber,Trip trip) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putSerializable("Trip",trip);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView=null;
            TextView textView;
            ListView listView;
            //Intent i = this.getActivity().getIntent();
            //List<String> temp= Arrays.asList("element1","element2","element3");
            List<String> temp= new ArrayList<String>();
            Trip trip=(Trip) getArguments().getSerializable("Trip");


            //temp.add((String) i.getSerializableExtra("TripName"));
            switch(getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1:
                    rootView = inflater.inflate(R.layout.fragment_trip_details_iternary, container, false);
                    textView = (TextView) rootView.findViewById(R.id.section_label);
                    textView.setText(getString(R.string.tab1));
                    break;
                case 2:
                    rootView = inflater.inflate(R.layout.fragment_trip_details_detailed, container, false);
                    textView=(TextView) rootView.findViewById(R.id.sourcecity);
                    textView.setText(trip.source);

                    textView=(TextView) rootView.findViewById(R.id.desinationcity);
                    textView.setText(trip.destination);

                    textView=(TextView) rootView.findViewById(R.id.startdate);
                    textView.setText(Trip.datetostring(trip.startdate));

                    textView=(TextView) rootView.findViewById(R.id.enddate);
                    textView.setText(Trip.datetostring(trip.enddate));

                    textView=(TextView) rootView.findViewById(R.id.description);
                    textView.setText(trip.description);

                    textView=(TextView) rootView.findViewById(R.id.travelmode);
                    textView.setText(trip.mode.toString());

                    break;
                case 3:
                    rootView = inflater.inflate(R.layout.fragment_trip_details_checklist, container, false);
                    if (trip.checklist.isEmpty())
                    {
                        temp.add("There is nothing yet");
                    }
                    else
                    {
                        temp=trip.checklist;
                    }
                    ArrayAdapter adapter = new ArrayAdapter<String>(rootView.getContext(), R.layout.fragment_tripdetails_checklist_singleelement, temp);

                    listView = (ListView) rootView.findViewById(R.id.tripdetails_checklist_listview);
                    listView.setAdapter(adapter);

                    break;
            }
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        Trip saveTripDetails;

        public SectionsPagerAdapter(FragmentManager fm,Trip trip) {
            super(fm);
            saveTripDetails=trip;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1,saveTripDetails);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.tab1);
                case 1:
                    return getString(R.string.tab2);
                case 2:
                    return getString(R.string.tab3);
            }
            return null;
        }
    }
}
