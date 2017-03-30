package android.learn.com.tripmanager;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Gopikrishna on 3/20/2017.
 */

enum Mode { bike ,car, train, plane};
@SuppressWarnings("serial")
public class Trip implements Serializable{
    public int id;
    public String name;
    public String source;
    public String destination;
    public Date startdate;
    public Date enddate;
    public String description;
    public Mode mode;
    public List<String> itinerary;
    public List<String> checklist;

    public Trip(int id, String name, String source, String destination, Date startdate, Date enddate, String description, Mode mode) {
        this.id = id;
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.startdate = startdate;
        this.enddate = enddate;
        this.description = description;
        this.mode = mode;
        this.itinerary=new ArrayList<String>();
        this.checklist=new ArrayList<String>();
    }

    static Date formatDate(String strdate)
    {
        Date date=new Date();
        try {
            date = new SimpleDateFormat("mm/dd/yyyy").parse(strdate);
        }
        catch (ParseException e) {


        }
        return date;
    }

    static String datetostring(Date date)
    {
        return new SimpleDateFormat("mm/dd/yyyy").format(date);
    }

    public Trip(){
        this.id=0;
        this.name="";
        this.source="";
        this.destination="";
        this.startdate=null;
        this.enddate=null;
        this.description="";
        this.mode=Mode.car;
        this.itinerary=new ArrayList<String>();
        this.checklist=new ArrayList<String>();
    }


    public Trip(int id,String name, String source, String destination, Date startdate, Date enddate, String description, Mode mode, ArrayList<String>  itinerary, ArrayList<String> checklist) {
        this.id=id;
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.startdate = startdate;
        this.enddate = enddate;
        this.description = description;
        this.mode = mode;
        if (itinerary==null){
            this.itinerary=new ArrayList<String>();
        }
        else{
            this.itinerary = itinerary;
        }

        if (checklist==null){
            this.checklist=new ArrayList<String>();
        }
        else{
            this.checklist = checklist;
        }

    }


};

