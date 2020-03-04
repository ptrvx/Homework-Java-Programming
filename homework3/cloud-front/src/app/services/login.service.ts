import { Injectable } from '@angular/core';
import { User } from '../login/user';
import { HttpClient } from '@angular/common/http';
import { BaseService } from './base.service';
import { Observable } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class LoginService extends BaseService {
  public loggedUser: User = null;

  constructor(protected http: HttpClient) {
    super(http);
   }

   public authenticate( user: User ): Observable<User> {
     const headers: Headers = new Headers();
     headers.append( 'Content-Type', 'application/json' );
     return this.http.post( this.url + '/authenticate', user, { headers: headers, withCredentials: true})
        .map( res => this.saveRes( res, this ))
        .catch( this.handleError );

   }

   public saveRes( res: Response, srv: LoginService ) {
     srv.loggedUser = srv.extractData( res );
     if (srv.loggedUser.token) {
       this.token = srv.loggedUser.token;
     }
     return this.loggedUser;
   }

   


}
