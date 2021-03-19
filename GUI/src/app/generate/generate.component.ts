import { Component, OnInit } from '@angular/core';
import { Date } from '../date';
import { Match } from '../match';
import { PlServiceService } from '../pl-service.service';

@Component({
  selector: 'app-generate',
  templateUrl: './generate.component.html',
  styleUrls: ['./generate.component.css']
})
export class GenerateComponent implements OnInit {

  match: Match=new Match("","",0,0,null,"");
  message:any;
  matches:any;
  day: number;
  month:number;
  year: number;
  stdate:string;
  message2:any;

  awayVal:string;
  homeVal:string;
  dayVal:number;
  monthVal:number;
  yearVal:number;

  constructor(private plService:PlServiceService) { }

  ngOnInit(): void {
  }
  public addMatch(){
    let respon=this.plService.addMatch(this.match);
    respon.subscribe((data)=>this.matches=data);
    this.message2="GENERATED MATCH IS ADDED"
  }

  public formMatch(){
    this.awayVal='';
    this.homeVal='';
    this.dayVal=0;
    this.monthVal=0;
    this.yearVal=0;
    this.awayVal=this.match.homeTeamName;
    this.homeVal=this.match.awayTeamName;
    this.dayVal=this.day;
    this.monthVal=this.month;
    this.yearVal=this.year;
    if((this.awayVal == null) ||(this.homeVal==null) ||(this.dayVal=0)||(this.monthVal=0)||(this.yearVal=0) ){
      this.message="PLEASE FILL OUT ALL FIELDS";
    }
    else{
    this.stdate=this.day+"-"+this.month+"-"+this.year;
    this.match.stdate=this.stdate;
    let respon=this.plService.formMatch(this.match);
    respon.subscribe((data)=>this.message=data);
    this.match=new Match("","",0,0,null,"");
    }
  }
 
}      


