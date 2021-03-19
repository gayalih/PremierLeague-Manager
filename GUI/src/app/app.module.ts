import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {HttpClientModule } from '@angular/common/http';
import { PltableComponent } from './pltable/pltable.component';
import { StableComponent } from './stable/stable.component';
import { WtableComponent } from './wtable/wtable.component';
import { FormsModule } from '@angular/forms';
import { AddComponent } from './add/add.component';
import { PlServiceService } from './pl-service.service';
import { GenerateComponent } from './generate/generate.component';
import { TablesComponent } from './tables/tables.component';
import { HomeComponent } from './home/home.component';
import {MatchesComponent} from './matches/matches.component';

@NgModule({
  declarations: [
    AppComponent,
    PltableComponent,
    StableComponent,
    WtableComponent,
    AddComponent,
    GenerateComponent,
    TablesComponent,
    HomeComponent,
    MatchesComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule

  ],
  providers: [PlServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
