package com.example.nitinvarun.imdbapp;

/**
 * Created by NitinVarun on 6/4/2015.
 */
public class InformationData {

    String movieName;
    String posterURL;
    String directorName;
    String rating;

    public InformationData(String movieName,String posterURL, String directorName,String rating){
        this.movieName = movieName;
        this.posterURL = posterURL;
        this.directorName = directorName;
        this.rating = rating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public String getRating() {
        return rating;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public String getMovieName() {
        return movieName;
    }

}
