package com.example;

import java.io.IOException;
import java.util.ArrayList;

//Interface for Premier League Manager
public interface LeagueManager {
    void cliMenu();
    void addFootballClub();
    void deleteClub();
    void displayStats();
    void displayPremierLeagueTable();
    void addMatch();
    void saveInfile() throws IOException;
    void loadFromFile();
    void showGUI();
    void premierGUI();
    void genMatchGUI();
    void showMatchGUI();
    ArrayList<Match> getMatchesList();
    ArrayList<FootballClub> getPremierLeagueTeams();
    ArrayList<FootballClub> points(FootballClub footballClub);
    ArrayList<FootballClub> goals(FootballClub footballClub);
    ArrayList<FootballClub> wins(FootballClub footballClub);
    ArrayList<Match> getMatches(Match match);
    String add(FootballClub footballClub);
    ArrayList<Match> genMatchAng(Match match);
    ArrayList<Match> findMatch(String stdate);
    String deleteMatch(String clubName);
    String addMatchAng(Match match);
}
