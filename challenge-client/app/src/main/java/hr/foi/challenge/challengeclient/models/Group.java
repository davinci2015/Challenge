package hr.foi.challenge.challengeclient.models;

import java.io.Serializable;

/**
 * Created by Tomislav Turek on 24.09.15..
 */
public class Group implements Serializable {

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
