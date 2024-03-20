//Created by CLI, by generating the frontend app with "ng new serverapp --no-standalone"

import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent }  from './app.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
imports: [ 
    BrowserModule,
    HttpClientModule
  ],
  declarations: [ AppComponent ],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }