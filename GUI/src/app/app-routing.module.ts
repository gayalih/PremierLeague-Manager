import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {PltableComponent} from './pltable/pltable.component';
import {StableComponent} from './stable/stable.component';
import {WtableComponent} from './wtable/wtable.component';
import {AddComponent} from './add/add.component';
import {GenerateComponent} from './generate/generate.component';
import {TablesComponent} from './tables/tables.component';
import {HomeComponent} from './home/home.component';
import {MatchesComponent} from './matches/matches.component';


const routes: Routes = [
  {path:'matches' ,component:MatchesComponent},
  {path:'add' , component:AddComponent},
  {path:'generate' , component:GenerateComponent},
  {
    path:'tables', 
    component:TablesComponent,
    children: [
      {path: 'point',component:PltableComponent},
      {path: 'score',component:StableComponent},
      {path: 'win',component:WtableComponent},
      {path: '',   redirectTo: 'point', pathMatch: 'full' },
    ]
  },
  {path:'home', component:HomeComponent},
  { path: '',   redirectTo: '/home', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
