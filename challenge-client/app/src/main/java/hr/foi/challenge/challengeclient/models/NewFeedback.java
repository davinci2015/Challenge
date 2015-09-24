package hr.foi.challenge.challengeclient.models;

/**
 * Created by igor on 9/24/15.
 */
public class NewFeedback {

    private String text;

    private int type;

    private String group_name;

    private long person_id;

    public NewFeedback(String text, int type, String group_name, long person_id) {
        this.text = text;
        this.type = type;
        this.group_name = group_name;
        this.person_id = person_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(long person_id) {
        this.person_id = person_id;
    }
}
