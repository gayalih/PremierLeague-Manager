/*
package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PremierLeagueManagerTest {
    PremierLeagueManager plm=new PremierLeagueManager();
    @Test
    void add() {
        FootballClub club=new FootballClub("jj","kk");
        assertEquals("CLUB IS ADDED",plm.add(club));
        club=new FootballClub("jj","kk");
        assertEquals("CLUB ALREADY EXISTS",plm.add(club));
    }
    @Test
    void deleteMatch() {
        FootballClub club=new FootballClub("jj","kk");
        plm.add(club);
        String clubName="jj";
        assertEquals("CLUB IS REMOVED",plm.deleteMatch(clubName));
        assertEquals("CLUB DOESN'T EXIST",plm.deleteMatch("ll"));
    }
    @Test
    void addMatchAng() {
        Date date=new Date(1,1,2019);
        FootballClub club=new FootballClub("jj","kk");
        FootballClub club2=new FootballClub("gg","ll");
        plm.add(club);
        plm.add(club2);
        Match match=new Match("jj","gg",3,4,date);
        assertEquals("MATCH ADDED",plm.addMatchAng(match));
        Match match2=new Match("jj","jj",3,4,date);
        assertEquals("SAME CLUB NAME!",plm.addMatchAng(match2));
        Match match3=new Match("jj","mm",3,4,date);
        assertEquals("CLUB DOESN'T EXIST",plm.addMatchAng(match3));
    }
    @Test
    void findMatch() {
        Date date=new Date(1,1,2019);
        Match match=new Match("jj","gg",3,4,date);
        plm.getMatchesList().clear();
        plm.getMatchesList().add(match);
        assertEquals(plm.getMatchesList(),plm.findMatch("1-1-2019"));
    }
    @Test
    void getMatches() {
        Date date=new Date(1,1,2019);
        Match match=new Match("jj","gg",3,4,date);
        plm.getMatchesList().clear();
        plm.getMatchesList().add(match);
        assertEquals(plm.getMatchesList(),plm.getMatches(match));
    }
    @Test
    void addFootballClub(){
        int before=plm.getPremierLeagueTeams().size();
        FootballClub club=new FootballClub("jj","kk");
        plm.getPremierLeagueTeams().add(club);
        int after=plm.getPremierLeagueTeams().size();
        assertEquals((before+1),after);
    }
    @Test
    void deleteClub(){
        FootballClub club=new FootballClub("jj","kk");
        plm.getPremierLeagueTeams().add(club);
        int before=plm.getPremierLeagueTeams().size();
        String clubName="jj";
        for(int i=0; i<plm.getPremierLeagueTeams().size();i++){
            if (plm.getPremierLeagueTeams().get(i).getClubName().equals(clubName)){
                plm.getPremierLeagueTeams().remove(i);
            }
        }
        int after=plm.getPremierLeagueTeams().size();
        assertEquals((before-1),after);
    }
    @Test
    void addMatch(){
        Date date=new Date(1,1,2019);
        int before=plm.getMatchesList().size();
        Match match=new Match("gg","kk",3,4,date);
        plm.getMatchesList().add(match);
        int after=plm.getMatchesList().size();
        assertEquals((before+1),after);
    }
}*/
