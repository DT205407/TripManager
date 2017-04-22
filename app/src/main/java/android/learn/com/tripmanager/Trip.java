package android.learn.com.tripmanager;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
    public List<String> tripFriends;

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
        this.tripFriends=new ArrayList<String>();
    }
    public Trip(int id, String name, String source, String destination, Date startdate, Date enddate, String description, String mode) {
        this.id = id;
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.startdate = startdate;
        this.enddate = enddate;
        this.description = description;
        this.itinerary=new ArrayList<String>();
        this.checklist=new ArrayList<String>();
        this.tripFriends=new ArrayList<String>();
        switch(mode){
            case "car":
                this.mode = Mode.car;
                break;
            case "bike":
                this.mode = Mode.bike;
                break;
            case "plane":
                this.mode = Mode.plane;
                break;
            case "train":
                this.mode = Mode.train;
                break;
            default:
                this.mode = Mode.car;
                break;

        }

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
        //this generates random number between 1000 and 10,000
        this.id=new Random().nextInt((10000-1000)+1)+1000;
        this.name="Sample test";
        this.source="Dallas";
        this.destination="Chicago";
        this.startdate=Trip.formatDate("01/10/2020");
        this.enddate=Trip.formatDate("01/10/2020");
        this.description="this is samle trip automatically generated";
        this.mode=Mode.car;
        this.itinerary=new ArrayList<String>();
        this.checklist=new ArrayList<String>();
        this.tripFriends=new ArrayList<String>();
    }


    public Trip(int id, String name, String source, String destination, Date startdate, Date enddate, String description, Mode mode, List<String> itinerary, List<String> checklist, List<String> tripFriends) {
        this.id = id;
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
        if (tripFriends==null){
            this.tripFriends=new ArrayList<String>();
        }
        else{
            this.tripFriends = tripFriends;
        }
    }

    public Trip(int id, String name, String source, String destination, Date startdate, Date enddate, String description, Mode mode, ArrayList<String>  itinerary, ArrayList<String> checklist) {
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


    public Trip(int id,String name, String source, String destination, Date startdate, Date enddate, String description, String mode, ArrayList<String>  itinerary, ArrayList<String> checklist) {
        this.id=id;
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.startdate = startdate;
        this.enddate = enddate;
        this.description = description;
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
        switch(mode){
            case "car":
                this.mode = Mode.car;
                break;
            case "bike":
                this.mode = Mode.bike;
                break;
            case "plane":
                this.mode = Mode.plane;
                break;
            case "train":
                this.mode = Mode.train;
                break;
            default:
                this.mode = Mode.car;
                break;

        }
    }
};

