import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FootballClub } from './football-club';

@Injectable({
  providedIn: 'root'
})
export class PlServiceService {

  constructor(private http:HttpClient) { }

  public points(){
    return this.http.get("http://localhost:8080/points");
  }
  public goals(){
    return this.http.get("http://localhost:8080/goals");
  }
  public wins(){
    return this.http.get("http://localhost:8080/wins");
  }
  public matches(){
    return this.http.get("http://localhost:8080/matches");
  }

  public addClub(footballclub) {
    return this.http.post("http://localhost:8080/clubs", footballclub,{responseType:'text' as 'json'});
  }
  public addMatch(match){
    return this.http.post("http://localhost:8080/matches",match);
  }
  public findMatches(stdate){
    return this.http.get("http://localhost:8080/findmatch/"+stdate);
  }
  public deleteMatch(clubName){
    return this.http.get("http://localhost:8080/delmatches/"+clubName,{responseType:'text' as 'json'});
  }
  public formMatch(match){
    return this.http.post("http://localhost:8080/formmatch", match,{responseType:'text' as 'json'});
  }
 
}
