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
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
//Test commit

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //hides keyboard
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        final Intent i = getIntent();
        tempTrip= ((DataAdapterTrips) this.getApplication()).getTrip(i.getIntExtra("TripId",0));


        Log.d("View Invoked", "New view created" );
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle(tempTrip.name);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        //((FloatingActionButton) this.findViewById(R.id.savetripdetails)).setVisibility(View.GONE);
        FloatingActionButton edittripdetails = (FloatingActionButton) findViewById(R.id.edittripdetails);

        //Below code will help us to alternate edit and save buttons
        if(i.getStringExtra("mode").equals("normal"))
        {
            ((FloatingActionButton) this.findViewById(R.id.savetripdetails)).setVisibility(View.GONE);
            ((FloatingActionButton) this.findViewById(R.id.edittripdetails)).setVisibility(View.VISIBLE);
        }
        //this is default action if anything messesup
        else
        {
            ((FloatingActionButton) this.findViewById(R.id.savetripdetails)).setVisibility(View.VISIBLE);
            ((FloatingActionButton) this.findViewById(R.id.edittripdetails)).setVisibility(View.GONE);
        }


        edittripdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent temp=new Intent(view.getContext(),TripDetails.class);
                temp.putExtra("TripId",i.getIntExtra("TripId",0));
                if(i.getStringExtra("currentscreen").equals("TripDetailsDetailed") && i.getStringExtra("mode").equals("normal"))
                {
                    //update Trip object for details here
                }
                else if(i.getStringExtra("currentscreen").equals("TripDetailsChecklist") && i.getStringExtra("mode").equals("normal"))
                {
                    //update checklist here
                }
                else if(i.getStringExtra("currentscreen").equals("TripDetailsItenary") && i.getStringExtra("mode").equals("normal"))
                {
                    //update itenary here
                }

                temp.putExtra("mode","edit");
                temp.putExtra("currentscreen",i.getStringExtra("currentscreen"));
                view.getContext().startActivity(temp);


            }
        });

        FloatingActionButton saveeditdetails = (FloatingActionButton) findViewById(R.id.savetripdetails);
        saveeditdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent temp=new Intent(view.getContext(),TripDetails.class);
                temp.putExtra("TripId",i.getIntExtra("TripId",0));
                view.getContext().startActivity(temp);

                if(i.getStringExtra("currentscreen").equals("TripDetailsDetailed") && i.getStringExtra("mode").equals("edit"))
                {
                    //update Trip object for details here
                }
                else if(i.getStringExtra("currentscreen").equals("TripDetailsChecklist") && i.getStringExtra("mode").equals("edit"))
                {
                    //update Trip object for checklist here
                }
                else if(i.getStringExtra("currentscreen").equals("TripDetailsItenary") && i.getStringExtra("mode").equals("edit"))
                {
                    //update Trip object for itenary here
                }

                temp.putExtra("mode","normal");
                temp.putExtra("currentscreen",i.getStringExtra("currentscreen"));
                view.getContext().startActivity(temp);

            }
        });

        mViewPager.setCurrentItem(1,true);
        //this is to set Trip detail tab as default view
        /*if(i.getStringExtra("currentscreen").equals("TripDetailsItenary"))
            mViewPager.setCurrentItem(0,false);
        else if (i.getStringExtra("currentscreen").equals("TripDetailsDetailed"))
            mViewPager.setCurrentItem(1,false);
        else if(i.getStringExtra("currentscreen").equals("TripDetailsChecklist"))
            mViewPager.setCurrentItem(2,false);*/



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
        switch(id){
            case R.id.maps:
                tempintent=new Intent(this,TripDetailsMapsActivity.class);
                tempintent.putExtra("sourcecity",tempTrip.source);
                tempintent.putExtra("destinationcity",tempTrip.destination);
                startActivity(tempintent);
                return true;
            case R.id.alerts:
                tempintent=new Intent(this,TripDetailsTripAlerts.class);
                startActivity(tempintent);
                return true;
            case R.id.tripfriends:
                tempintent=new Intent(this,TripDetailsFriends.class);
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

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = null;
            TextView textView;
            ListView listView;
            List<String> temp = new ArrayList<String>();
            Trip trip = ((DataAdapterTrips) getActivity().getApplication()).getTrips().get(getActivity().getIntent().getIntExtra("TripId",0));
            Log.d("currentscreenNumber",String.valueOf(getArguments().getInt(ARG_SECTION_NUMBER)));

            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1:
                    rootView = inflater.inflate(R.layout.fragment_trip_details_iternary, container, false);
                    textView = (TextView) rootView.findViewById(R.id.section_label);
                    getActivity().getIntent().putExtra("currentscreen","TripDetailsItenary");
                    Log.d("currentscreen",getActivity().getIntent().getStringExtra("currentscreen"));
                    textView.setText(getString(R.string.tab1));
                    Log.d("Tabs invoked", "Itenary" );
                    break;
                case 2:
                    rootView = inflater.inflate(R.layout.fragment_trip_details_detailed, container, false);
                    getActivity().getIntent().putExtra("currentscreen","TripDetailsDetailed");
                    Log.d("currentscreen",getActivity().getIntent().getStringExtra("currentscreen"));
                    Log.d("Tabs invoked", "Tripdetails" );
                    textView = (TextView) rootView.findViewById(R.id.sourcecity);
                    textView.setText(trip.source);


                    //if value is Homepage then normal checklist is diplayed
                    if(getActivity().getIntent().getStringExtra("mode").equals("normal"))
                    {
                        showtripdetail(rootView,trip);

                    }
                    //if it is TripDetailsEdit then edittrip screen is displayed
                    else
                    {
                        edittripdetail(rootView, trip);

                    }
                    break;
                case 3:
                    rootView = inflater.inflate(R.layout.fragment_trip_details_checklist, container, false);
                    //this is to check if you have any checklist
                    getActivity().getIntent().putExtra("currentscreen","TripDetailsChecklist");
                    Log.d("currentscreen",getActivity().getIntent().getStringExtra("currentscreen"));
                    Log.d("Tabs invoked", "Checklist" );
                    if (trip.checklist.isEmpty()) {
                        temp.add("There is nothing yet");
                    } else {
                        temp = trip.checklist;
                    }
                    ArrayAdapter adapter = new ArrayAdapter<String>(rootView.getContext(), R.layout.fragment_tripdetails_checklist_singleelement, temp);
                    //this is to add element to checklist
                    if(getActivity().getIntent().getStringExtra("mode").equals("normal")){
                        ((EditText) rootView.findViewById(R.id.editchecklist)).setVisibility(View.GONE);
                    }
                    else{
                        ((EditText) rootView.findViewById(R.id.editchecklist)).setVisibility(View.VISIBLE);
                    }

                    listView = (ListView) rootView.findViewById(R.id.tripdetails_checklist_listview);
                    listView.setAdapter(adapter);

                    break;
            }
            return rootView;
        }

        public void showtripdetail(View view, Trip trip) {
            ((TextView) view.findViewById(R.id.sourcecity)).setText(trip.source);
            ((TextView) view.findViewById(R.id.desinationcity)).setText(trip.destination);
            ((TextView) view.findViewById(R.id.startdate)).setText(Trip.datetostring(trip.startdate));
            ((TextView) view.findViewById(R.id.enddate)).setText(Trip.datetostring(trip.enddate));
            ((TextView) view.findViewById(R.id.description)).setText(trip.description);
            ((TextView) view.findViewById(R.id.travelmode)).setText(trip.mode.toString());
            //hides edittext
            ((EditText) view.findViewById(R.id.sourcecityedit)).setVisibility(View.GONE);
            ((EditText) view.findViewById(R.id.desinationcityedit)).setVisibility(View.GONE);
            ((EditText) view.findViewById(R.id.startdateedit)).setVisibility(View.GONE);
            ((EditText) view.findViewById(R.id.enddateedit)).setVisibility(View.GONE);
            ((EditText) view.findViewById(R.id.descriptionedit)).setVisibility(View.GONE);
            ((Spinner) view.findViewById(R.id.travelmodeedit)).setVisibility(View.GONE);

        }


        public void edittripdetail(View view, Trip trip) {
            ((EditText) view.findViewById(R.id.sourcecityedit)).setText(trip.source);
            ((EditText) view.findViewById(R.id.desinationcityedit)).setText(trip.destination);
            ((EditText) view.findViewById(R.id.startdateedit)).setText(Trip.datetostring(trip.startdate));
            ((EditText) view.findViewById(R.id.enddateedit)).setText(Trip.datetostring(trip.enddate));
            ((EditText) view.findViewById(R.id.descriptionedit)).setText(trip.description);
            //have to fix spinner selection text
            ((Spinner) view.findViewById(R.id.travelmodeedit)).setSelection(1);

            //hides textview
            ((TextView) view.findViewById(R.id.sourcecity)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.desinationcity)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.startdate)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.enddate)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.description)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.travelmode)).setVisibility(View.GONE);
        }
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        //this is to extract which tab is being clicked
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
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
