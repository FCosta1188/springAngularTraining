//Created by CLI with "ng g service service/server"

import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CustomResponse } from '../interface/custom-response';
import { Observable, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { Server } from '../interface/server';

@Injectable({ providedIn: 'root' })
export class ServerService {
  private readonly baseApiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { } // dependency injection

  //HTTP requests ----------------------------- 
  //procedural approach 
/*   getServers(): Observable<CustomResponse> {
    return this.http.get<CustomResponse> ('http://localhost:8080/server/list');
  } */

  //reactive approach
  //getServers
  servers$ = <Observable<CustomResponse>> //cast
    this.http.get<CustomResponse>(`${this.baseApiUrl}/server/list`)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );

  //saveServer
  save$ = (server: Server) => <Observable<CustomResponse>> //cast
  this.http.post<CustomResponse>(`${this.baseApiUrl}/server/save`, server)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  //pingServer
  ping$ = (ipAddress: string) => <Observable<CustomResponse>> //cast
  this.http.get<CustomResponse>(`${this.baseApiUrl}/server/ping/${ipAddress}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  //deleteServer
  delete$ = (serverId: number) => <Observable<CustomResponse>> //cast
  this.http.delete<CustomResponse>(`${this.baseApiUrl}/server/delete/${serverId}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );
  //------------------------------------------- 

  
  //Error Handling ----------------------------- 
  //deprecated
 /*  handleError(handleError: any): Observable<never> {
    return throwError ('Method not implemented.');
  } */

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error)
    return throwError(() => 'Method not implemented.');
  }
  //--------------------------------------------
}
