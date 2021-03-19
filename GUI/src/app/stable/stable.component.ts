import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FootballClub } from '../football-club';
import { PlServiceService } from '../pl-service.service';

@Component({
  selector: 'app-stable',
  templateUrl: './stable.component.html',
  styleUrls: ['./stable.component.css']
})
export class StableComponent implements OnInit {

  footballClubs:any;

  constructor(private plService:PlServiceService) { }


  ngOnInit() {
    this.getGoals();
  }
  public getGoals(){
    let respon=this.plService.goals();
    respon.subscribe((data)=>this.footballClubs=data);
  }

}
