package android.learn.com.tripmanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

public class CreateTrip extends AppCompatActivity {
    private EditText startdate,enddate,temp;
    private int year,month,day;
    Trip tempTrip=new Trip();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        startdatelistener();
        enddatelistener();

    }

    //this is button click action
    public void onAddTripClick(View view)
    {
        tempTrip.name=((EditText) findViewById(R.id.tripName)).getText().toString();
        tempTrip.source=((EditText) findViewById(R.id.sourcecity)).getText().toString();
        tempTrip.destination=((EditText) findViewById(R.id.desinationcity)).getText().toString();
        tempTrip.description=((EditText) findViewById(R.id.descriptionvalue)).getText().toString();
        //tempTrip.mode= ((Spinner)findViewById(R.id.travelmode));
        tempTrip.mode=Mode.car;
        tempTrip.startdate=Trip.formatDate(((EditText) findViewById(R.id.startdate)).getText().toString());
        tempTrip.enddate=Trip.formatDate(((EditText) findViewById(R.id.enddate)).getText().toString());

        Intent myTrips=new Intent(this,HomePage.class);
        myTrips.putExtra("newTrip",tempTrip);
        startActivity(myTrips);

    }


    public void startdatelistener() {

        startdate = (EditText) findViewById(R.id.startdate);

        //setdate(startdate);

        startdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(CreateTrip.this,
                        new mStartDateSetListener(), mYear, mMonth, mDay);
                dialog.show();
            }
        });
    }


    class mStartDateSetListener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            // getCalender();
            int mYear = year;
            int mMonth = monthOfYear;
            int mDay = dayOfMonth;
            startdate.setText(new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mMonth + 1).append("/").append(mDay).append("/")
                    .append(mYear).append(" "));


        }
    }


    public void enddatelistener() {

        enddate = (EditText) findViewById(R.id.enddate);

        //setdate(enddate);

        enddate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                System.out.println("the selected " + mDay);
                DatePickerDialog dialog = new DatePickerDialog(CreateTrip.this,
                        new mEndDateSetListener(), mYear, mMonth, mDay);
                dialog.show();
            }
        });
    }


    class mEndDateSetListener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            // getCalender();
            int mYear = year;
            int mMonth = monthOfYear;
            int mDay = dayOfMonth;
            enddate.setText(new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mMonth + 1).append("/").append(mDay).append("/")
                    .append(mYear).append(" "));


        }
    }

}
