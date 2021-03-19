package com.example.w1761100;

import com.example.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins="*", allowedHeaders = "*")
public class Rest {
    public static PremierLeagueManager plm=new PremierLeagueManager();
    public static Date date=new Date();

    //method to return premier league table sorted by points
    @GetMapping("/points")
    public ArrayList<FootballClub> getPoints(FootballClub footballClub){
        return plm.points(footballClub);
    }
    //method to return premier league table sorted by goals
    @GetMapping("/goals")
    public ArrayList<FootballClub> getGoals(FootballClub footballClub){
        return plm.goals(footballClub);
    }
    //method to return premier league table sorted by wins
    @GetMapping("/wins")
    public ArrayList<FootballClub> getWins(FootballClub footballClub){
        return plm.wins(footballClub);
    }
    //method to get the match arraylist
    @GetMapping("/matches")
    public ArrayList<Match> getMatchList(Match match){
        return plm.getMatches(match);
    }
    //method to add club
    @PostMapping("/clubs")
    public String addClub(@RequestBody FootballClub footballClub){
       return plm.add(footballClub);
    }
    //method to add match manually
    @PostMapping("/formmatch")
    public String addMatchAng(@RequestBody Match match){
        return plm.addMatchAng(match);
    }
    //method to generate match
    @PostMapping("/matches")
    public ArrayList<Match> genMatchAng(@RequestBody Match match){
        return plm.genMatchAng(match);
    }
    //method to find match by date
    @RequestMapping(value = "/findmatch/{stdate}")
    public ArrayList<Match> getUser(@PathVariable String stdate) {
        return plm.findMatch(stdate);
    }
    //method to delete club
    @CrossOrigin(allowedHeaders = "Access-Control-Allow-Origin")
    @RequestMapping(value = "/delmatches/{clubName}")
    public String deleteMatch(@PathVariable String clubName){
        return plm.deleteMatch(clubName);
    }
}
