package com.example;

import java.io.Serializable;
import java.util.ArrayList;

public class Match implements Serializable {
    private String homeTeamName;
    private String awayTeamName;
    private int hGoalsScored;
    private int aGoalsScored;
    private Date date;
    private String stdate;

    public Match() {
    }
    public Match(String homeTeamName,String awayTeamName,int hGoalsScored,int aGoalsScored, Date date) {
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.hGoalsScored = hGoalsScored;
        this.aGoalsScored = aGoalsScored;
        this.date = date;
        stdate = date.toString();
    }
    public Match(String homeTeamName,String awayTeamName,int hGoalsScored,int aGoalsScored, String stdate){
        this.homeTeamName=homeTeamName;
        this.awayTeamName=awayTeamName;
        this.hGoalsScored=hGoalsScored;
        this.aGoalsScored=aGoalsScored;
        this.stdate=stdate;
    }
    public String getStdate() {
        return stdate;
    }
    public int getAGoalsScored() {
        return aGoalsScored;
    }
    public int getHGoalsScored() {
        return hGoalsScored;
    }
    public void sethGoalsScored(int hGoalsScored) {
        this.hGoalsScored = hGoalsScored;
    }
    public void setaGoalsScored(int aGoalsScored) {
        this.aGoalsScored = aGoalsScored;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Date getDate() {
        return date;
    }
    public String getAwayTeamName() {
        return awayTeamName;
    }
    public String getHomeTeamName() {
        return homeTeamName;
    }
    //sort matches according to date played ascending order
    public void matchSort(ArrayList<Match> matches){
        for (int i = 0; i < matches.size() - 1; i++){
            for (int j = 0; j < (matches.size() - i - 1); j++){

                if (matches.get(j).getDate().getYear() > matches.get(j+1).getDate().getYear()) {
                    Match temp = matches.get(j);
                    matches.set(j,matches.get(j + 1));
                    matches.set((j+1),temp);
                }
                if (matches.get(j).getDate().getYear()== matches.get(j+1).getDate().getYear()) {
                    if (matches.get(j).getDate().getMonth() > matches.get(j + 1).getDate().getMonth()) {
                        Match temp1 = matches.get(j);
                        matches.set(j, matches.get(j + 1));
                        matches.set((j + 1), temp1);
                    }
                    if (matches.get(j).getDate().getMonth()== matches.get(j+1).getDate().getMonth()){
                        if (matches.get(j).getDate().getDay()>matches.get(j+1).getDate().getDay()){
                            Match temp1=matches.get(j);
                            matches.set(j,matches.get(j+1));
                            matches.set((j+1),temp1);
                        }
                    }
                }
            }
        }
    }
    //method to update club stats when a match is added
    public void addMatch(ArrayList<FootballClub> premierLeagueTeams, int i, int j, int h,int a){
        premierLeagueTeams.get(i).setGoalsScored(premierLeagueTeams.get(i).getGoalsScored()+h);
        premierLeagueTeams.get(j).setGoalsScored(premierLeagueTeams.get(j).getGoalsScored()+a);
        premierLeagueTeams.get(i).setGoalsReceived(premierLeagueTeams.get(i).getGoalsReceived()+a);
        premierLeagueTeams.get(j).setGoalsReceived(premierLeagueTeams.get(j).getGoalsReceived()+h);
        if(h>a){
            premierLeagueTeams.get(i).setNumberOfWins(premierLeagueTeams.get(i).getNumberOfWins()+1);
            premierLeagueTeams.get(j).setNumberOfDefeats(premierLeagueTeams.get(j).getNumberOfDefeats()+1);
        }
        else if (a>h){
            premierLeagueTeams.get(j).setNumberOfWins(premierLeagueTeams.get(j).getNumberOfWins()+1);
            premierLeagueTeams.get(i).setNumberOfDefeats(premierLeagueTeams.get(i).getNumberOfDefeats()+1);
        }
        else{
            premierLeagueTeams.get(i).setNumberOfDraws(premierLeagueTeams.get(i).getNumberOfDraws()+1);
            premierLeagueTeams.get(j).setNumberOfDraws(premierLeagueTeams.get(j).getNumberOfDraws()+1);
        }
    }
    public String toString() {
        if (hGoalsScored>aGoalsScored){
            return  "Date: "+date.toString()+
                    "\nWinning Team (Home):\n        Name: "+homeTeamName+" - Goals Scored: "+hGoalsScored+
                    "\nLosing Team (Away):\n        Name: "+awayTeamName+" - Goals Scored: "+aGoalsScored+"\n";
        }
        else if (hGoalsScored==aGoalsScored){
            return  "Match is a Draw"+
                    "\n\nDate: "+date.toString()+
                    "\nHome:\n        Name: "+homeTeamName+" - Goals Scored: "+hGoalsScored+
                    "\nAway:\n        Name: "+awayTeamName+" - Goals Scored: "+aGoalsScored+"\n";
        }
        else{
            return  "Date: "+date.toString()+
                    "\nWinning Team (Away):\n        Name: "+awayTeamName+" - Goals Scored: "+aGoalsScored+
                    "\nLosing Team (Home):\n        Name: "+homeTeamName+" - Goals Scored: "+hGoalsScored+"\n";
        }
    }
}
