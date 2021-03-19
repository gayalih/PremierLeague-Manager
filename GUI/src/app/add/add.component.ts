import { Component, OnInit } from '@angular/core';
import { FootballClub } from '../football-club';
import { PlServiceService } from '../pl-service.service';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

  footballclub: FootballClub=new FootballClub("","",0,0,0,0,0,0,0,0,);
  message:any;

  clubVal:string;
  locVal:string;
  message2 : string;

  constructor(private plService:PlServiceService) { }

  ngOnInit(): void {
  }
 
  public addClub(){
    this.clubVal='';
    this.locVal='';
    this.clubVal = this.footballclub.clubName;
    this.locVal =this.footballclub.location;
    if((this.clubVal == null) ||(this.locVal==null)){
      this.message2="PLEASE FILL OUT THE FORM";
    }
    else{
      this.message2='';
      let respon=this.plService.addClub(this.footballclub);
      this.footballclub=new FootballClub("","",0,0,0,0,0,0,0,0,);
      respon.subscribe((data)=>this.message=data);
    }
  }
}
