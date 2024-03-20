//Created by CLI with "ng g service service/server"

import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CustomResponse } from '../interface/custom-response';
import { Observable, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { Server } from '../interface/server';
import { Status } from '../enum/status.enum';

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

  //filter servers (frontend only, no HTTP request to backend)
  filter$ = (status: Status, response: CustomResponse) => <Observable<CustomResponse>> //cast
  new Observable<CustomResponse>(
    subscriber => {
      console.log(response);
      subscriber.next( // next() admits values to subscribers of this observable 
        status === Status.ALL ? 
                   //TRUE: no filter
                   { ...response, message: `Showing all servers`} : 
                   //FALSE
                   { ...response,
                    message: response.data.servers.filter(server => server.status === status).length > 0 ? 
                             `Servers filtered by ${status.replace("_", " ")} status` : 
                             `No servers of status ${status.replace("_", " ")} found`,
                    data: { servers: response.data.servers.filter(server => server.status === status) }
                  } //error: 'response.data.servers' is possibly 'undefined' -> solved by setting "strict" to false in tsconfi.json     
      );
      subscriber.complete(); //data admission completed 
    }
  )
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
    return throwError(() => `An error occurred - Error code: ${error.status}`);
  }
  //--------------------------------------------
}
