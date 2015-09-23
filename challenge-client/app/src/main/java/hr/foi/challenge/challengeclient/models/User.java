package hr.foi.challenge.challengeclient.models;

/**
 * Created by Tomislav Turek on 23.09.15..
 */
public class User {

    private long id;

    private Credentials credentials;

    private String name;

    private String surname;

    private String mail;

    private String skype;

    private boolean evaluator;

    public User(Credentials credentials, String name, String surname, String mail, String skype) {
        this.credentials = credentials;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.skype = skype;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public boolean isEvaluator() {
        return evaluator;
    }

    public void setEvaluator(boolean evaluator) {
        this.evaluator = evaluator;
    }
}
