package com.example;

import java.util.ArrayList;

public class FootballClub extends SportsClub {
    private int numberOfWins;
    private int numberOfDraws;
    private int numberOfDefeats;
    private int goalsReceived;
    private int goalsScored;
    private int points;
    private int numberOfMatches;
    private int goalDifference;
    public FootballClub(){}
    public FootballClub(String clubName, String location){
        super(clubName, location);
        numberOfDraws=0;
        numberOfDefeats=0;
        numberOfWins=0;
        goalsReceived=0;
        goalsScored=0;
        points=(numberOfWins*3)+numberOfDraws;
        numberOfMatches=numberOfWins+numberOfDefeats+numberOfDraws;
        goalDifference=goalsScored-goalsReceived;
    }
    public FootballClub(String clubName, int points,int numberOfWins,int numberOfDefeats,int numberOfDraws, int goalsScored,int goalsReceived, int goalDifference) {
        super(clubName);
        this.numberOfWins=numberOfWins;
        this.numberOfDefeats=numberOfDefeats;
        this.numberOfDraws=numberOfDraws;
        this.goalsScored=goalsScored;
        this.goalsReceived=goalsReceived;
        this.points=points;
        this.goalDifference=goalDifference;
    }
    public int getNumberOfWins() {
        return numberOfWins;
    }
    public int getNumberOfDraws() {
        return numberOfDraws;
    }
    public int getNumberOfDefeats() {
        return numberOfDefeats;
    }
    public int getGoalsReceived() {
        return goalsReceived;
    }
    public int getGoalsScored() {
        return goalsScored;
    }
    public int getGoalDifference(){return (goalsScored-goalsReceived);}
    public int getPoints() {
        return (numberOfWins*3)+numberOfDraws;
    }
    public int getNumberOfMatches() {
        return numberOfWins+numberOfDefeats+numberOfDraws;
    }
    public void setGoalsReceived(int goalsReceived) {
        this.goalsReceived = goalsReceived;
    }
    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }
    public void setNumberOfDefeats(int numberOfDefeats) {
        this.numberOfDefeats = numberOfDefeats;
    }
    public void setNumberOfDraws(int numberOfDraws) {
        this.numberOfDraws = numberOfDraws;
    }
    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }
   public void setNumberOfMatches(int numberOfWins, int numberOfDraws, int numberOfDefeats) {
        numberOfMatches = this.numberOfWins+this.numberOfDraws+this.numberOfDefeats;
    }
    public void setPoints() {
        points=(this.numberOfWins*3)+this.numberOfDraws;
    }
    //method to sort all clubs by points
    public void pointSort(ArrayList<FootballClub> premierLeagueTeams){
        for (int i = 0; i <( premierLeagueTeams.size()-1); i++) {
            for (int j = i+1; j < premierLeagueTeams.size(); j++) {
                if (premierLeagueTeams.get(i).getPoints() < premierLeagueTeams.get(j).getPoints()) {
                    FootballClub temp=premierLeagueTeams.get(i);
                    premierLeagueTeams.set(i,premierLeagueTeams.get(j));
                    premierLeagueTeams.set(j,temp);
                }
                if (premierLeagueTeams.get(i).getPoints() == premierLeagueTeams.get(j).getPoints()){
                    if (premierLeagueTeams.get(i).getGoalDifference()<premierLeagueTeams.get(j).getGoalDifference()){
                        FootballClub temp1=premierLeagueTeams.get(i);
                        premierLeagueTeams.set(i,premierLeagueTeams.get(j));
                        premierLeagueTeams.set(j,temp1);
                    }
                }
            }
        }
    }
    //method to sort all clubs by goals
    public void goalSort(ArrayList<FootballClub> premierLeagueTeams){
        for (int i = 0; i <( premierLeagueTeams.size()-1); i++) {
            for (int j = i + 1; j < premierLeagueTeams.size(); j++) {
                if (premierLeagueTeams.get(i).getGoalsScored() < premierLeagueTeams.get(j).getGoalsScored()){
                    FootballClub temp = premierLeagueTeams.get(i);
                    premierLeagueTeams.set(i, premierLeagueTeams.get(j));
                    premierLeagueTeams.set(j, temp);
                }
            }
        }
    }
    //method to sort all clubs by wins
    public void winSort(ArrayList<FootballClub> premierLeagueTeams){
        for (int i = 0; i <( premierLeagueTeams.size()-1); i++) {
            for (int j = i + 1; j < premierLeagueTeams.size(); j++) {
                if (premierLeagueTeams.get(i).getNumberOfWins() < premierLeagueTeams.get(j).getNumberOfWins()){
                    FootballClub temp = premierLeagueTeams.get(i);
                    premierLeagueTeams.set(i, premierLeagueTeams.get(j));
                    premierLeagueTeams.set(j, temp);
                }
            }
        }
    }
    @Override
    public String toString() {
        return "FootballClub{" +
                "numberOfWins=" + numberOfWins +
                ", numberOfDraws=" + numberOfDraws +
                ", numberOfDefeats=" + numberOfDefeats +
                ", goalsReceived=" + goalsReceived +
                ", goalsScored=" + goalsScored +
                ", points=" + points +
                ", numberOfMatches=" + numberOfMatches +
                ", goalDifference=" + goalDifference +
                '}';
    }
    //method to show stats of all clubs
    public String showStats() {
        return "\nFootball Club name: "+getClubName()+
                "\nPoints: "+getPoints()+
                "\nGoals Scored: "+getGoalsScored()+
                "\nGoals Received: "+getGoalsReceived()+
                "\nMatches Won: "+getNumberOfWins()+
                "\nMatches Drawn: "+getNumberOfDraws()+
                "\nMatches Defeated: "+getNumberOfDefeats()+
                "\nTotal Matches Played: "+getNumberOfMatches();
    }
    public String tableString(){
        return "Club Name: "+getClubName()+
                "  |  Points: "+getPoints()+
                "  |  Goals Scored: "+getGoalsScored()+
                "  |  Goal Difference: "+getGoalDifference()+
                "  |  Number of Wins: "+getNumberOfWins()+
                "  |  Total Matches: "+getNumberOfMatches();

    }
}
