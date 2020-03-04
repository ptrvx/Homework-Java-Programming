import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BaseService {
  url: string;
  public token: string = null;

  constructor(protected http: HttpClient) {
    this.url = 'http://localhost:8080/'
   }

   public extractData( res: Response ) {
     const body = res.json();
     return body || {};
   }

   public handleError( error: Response | any) {
    let errMsg: string;
    if ( error instanceof Response ) {
      const body = error.json() || '';
      const err = body || JSON.stringify( body );
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error( errMsg );
    return Observable.throw( errMsg );

   }


}
