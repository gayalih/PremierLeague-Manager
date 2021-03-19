import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PlServiceService } from '../pl-service.service';

@Component({
  selector: 'app-matches',
  templateUrl: './matches.component.html',
  styleUrls: ['./matches.component.css']
})
export class MatchesComponent implements OnInit {

  matches:any;
  stdate:string;
  day: number;
  month:number;
  year:number;

   constructor(private plService:PlServiceService,
    private route:ActivatedRoute,
    private router:Router) { }

  ngOnInit(){
    this.getMatches();
  }

  public getMatches(){
    let respon=this.plService.matches();
    respon.subscribe((data)=>this.matches=data);
  }
  public findMatches(){
    this.stdate=this.day+"-"+this.month+"-"+this.year;
    let respon= this.plService.findMatches(this.stdate);
    respon.subscribe((data)=>this.matches=data);
   }
   sortMatches(){
    this.getMatches();
  }


}
