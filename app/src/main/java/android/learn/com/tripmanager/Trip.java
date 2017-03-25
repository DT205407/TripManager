package android.learn.com.tripmanager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Gopikrishna on 3/20/2017.
 */

enum Mode { bike ,car, train, plane};

public class Trip {
    public int id;
    public String name;
    public String source;
    public String destination;
    public Date startdate;
    public Date enddate;
    public String description;
    public Mode mode;
    public List<String> itenary;
    public List<String> checklist;

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

    public Trip(){
        this.id=0;
        this.name=null;
        this.source=null;
        this.destination=null;
        this.startdate=null;
        this.enddate=null;
        this.description=null;
        this.mode=null;
        this.itenary=null;
        this.checklist=null;
    }


    public Trip(int id,String name, String source, String destination, Date startdate, Date enddate, String description, Mode mode, List<String>  itenary, List<String> checklist) {
        this.id=id;
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.startdate = startdate;
        this.enddate = enddate;
        this.description = description;
        this.mode = mode;
        this.itenary = itenary;
        this.checklist = checklist;
    }


};

