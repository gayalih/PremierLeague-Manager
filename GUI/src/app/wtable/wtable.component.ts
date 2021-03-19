import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { PlServiceService } from '../pl-service.service';

@Component({
  selector: 'app-wtable',
  templateUrl: './wtable.component.html',
  styleUrls: ['./wtable.component.css']
})
export class WtableComponent implements OnInit {
  footballClubs:any;

  constructor(private plService:PlServiceService) { }


  ngOnInit() {
    this.getWins();
  }
  public getWins(){
    let respon=this.plService.wins();
    respon.subscribe((data)=>this.footballClubs=data);
  }

}
