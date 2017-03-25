package android.learn.com.tripmanager;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;


public class CreateTrip extends AppCompatActivity {

    private EditText startdate,enddate;
    private int year,month,day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);
        //this is to displat date picker
        startdatelistener();
        enddatelistener();


    }



    //this is button click action
    public void onAddTripClick(View view)
    {
        Intent myTrips=new Intent(this,HomePage.class);
        startActivity(myTrips);
    }


/*  //use this function to display date
    public void setdate(EditText date){
        Calendar datetoday=Calendar.getInstance();
        year=datetoday.get(Calendar.YEAR);
        month= datetoday.get(Calendar.MONTH);
        day=datetoday.get(Calendar.DAY_OF_MONTH);

        date.setText(new StringBuilder().append(month + 1)
                .append("-").append(day).append("-").append(year)
                .append(" "));

    }*/

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

