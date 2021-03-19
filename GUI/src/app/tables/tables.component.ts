import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLinkActive } from '@angular/router';
import { PlServiceService } from '../pl-service.service';

@Component({
  selector: 'app-tables',
  templateUrl: './tables.component.html',
  styleUrls: ['./tables.component.css']
})
export class TablesComponent implements OnInit {

  clubName:string;
  message:any;
  clubVal:string;

  constructor(private route:ActivatedRoute,
    private router:Router,
    private plService:PlServiceService) { }

  ngOnInit(): void {
  }
  showPoints(){
    this.router.navigate(['point'],{relativeTo:this.route });
  }
  showGoals(){
    this.router.navigate(['score'],{relativeTo:this.route });
  }
  showWins(){
    this.router.navigate(['win'],{relativeTo:this.route });
  }
  deleteMatch(){
    this.clubVal='';
    this.clubVal=this.clubName;
    if(this.clubVal == null){
      this.message="PLEASE ENTER CLUB NAME";
    }
    else{
    let respon= this.plService.deleteMatch(this.clubName);
    
    respon.subscribe((data)=>this.message=data);
    }
  }

}
