package com.example;

public class UniversityFootballClub  {
    private String universityName;
    UniversityFootballClub(){}
    UniversityFootballClub(String universityName, String location, int points, int goalsScored, int goalsReceived){
        super();
        this.universityName=universityName;
    }
    public String getUniversityName() {
        return universityName;
    }
    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }
}
