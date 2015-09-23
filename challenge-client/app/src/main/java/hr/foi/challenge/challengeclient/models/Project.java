package hr.foi.challenge.challengeclient.models;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Tomislav Turek on 23.09.15..
 */
public class Project {

    long id;
    String name;
    Date date;
    String description;
    boolean flag;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

