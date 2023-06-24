package Artist;

import java.util.UUID;

public class Artist {

    // Attributes
    private String iD;

    public String name;
    private String password;
    private String emailAddress;
    public String biography;
    // private String social media links
    // list of albums and playlist

    /*Constructor*/

    // Constructor For Login

    public Artist(String iD, String name, String password, String emailAddress, String biography) {
        this.iD = iD;
        this.name = name;
        this.password = password;
        this.emailAddress = emailAddress;
        this.biography = biography;
    }

    // Constructor For Signup

    public Artist(String name, String password, String emailAddress, String biography) {
        this.name = name;
        this.password = password;
        this.emailAddress = emailAddress;
        this.biography = biography;
    }

    // Constructor For Observable
    public Artist(String name, String biography){
        this.name = name;
        this.biography = biography;
    }
    public Artist(String name) {
        this.name = name;
    }
    // Getter


    public String getiD() {
        return iD;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getBiography() {
        return biography;
    }
}
