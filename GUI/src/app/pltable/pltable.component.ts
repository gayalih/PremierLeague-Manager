import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { PlServiceService } from '../pl-service.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-pltable',
  templateUrl: './pltable.component.html',
  styleUrls: ['./pltable.component.css']
})

export class PltableComponent implements OnInit {

 
  footballClubs:any;

  constructor(private plService:PlServiceService, 
              private route:ActivatedRoute,
              private router:Router) { }
  
  ngOnInit() {
    this.getPoints();
  }
  public getPoints(){
    let respon=this.plService.points();
    respon.subscribe((data)=>this.footballClubs=data);
  }
 

}
