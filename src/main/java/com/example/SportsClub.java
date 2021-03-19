package com.example;

import java.io.Serializable;

public class SportsClub implements Serializable {
    private String clubName;
    private String location;
    public SportsClub(){}
    public SportsClub(String clubName){
        this.clubName=clubName;
    }
    public SportsClub(String clubName, String location){
        this.clubName=clubName;
        this.location=location;
    }
    public String getClubName() {
        return clubName;
    }
    public String getLocation() {
        return location;
    }
    public void setClubName(String clubName) {
        this.clubName = clubName;
    }
    public void setLocation(String location) {
        this.location = location;
    }
}
