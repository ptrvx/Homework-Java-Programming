import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Machine } from '../machine';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  url: String;
  constructor(private http: HttpClient) { 
    this.url = 'http://localhost:8081/machine';
  }

  getMachines() {
    return this.http.get(this.url + '/search');
  }

  createMachine(name: string): Observable<Machine> {
    return this.http.post<Machine>(this.url + '/create' + '?name=' + name, {'name': name});
  }


  searchMachines(name, stop, run, dateTo, dateFrom) {
    // console.warn("name: " + name + ' stop: ' + stop + " run: " + run + " date: "+ dateFrom);
    // console.warn(this.url + '/search?' + 
    // (name != null)? "name="+name:"" +
    // (stop != null)? "status=STOPPED":"" +
    // (run != null)? "status=RUNNING":"" +
    // (dateTo != null)? "dateTo="+dateTo: "" +
    // (dateFrom != null)? "dateFrom="+dateFrom: "");

    return this.http.get(this.url + '/search?' + 
      ((name !== '')? "name="+name+"&":"") +
      ((stop)? "status=STOPPED"+"&":"") +
      ((run)? "status=RUNNING"+"&":"")
    );
  }


  startMachine(id) {
    return this.http.post(this.url + "/start?id=" + id, {"id": id});
  }

  stopMachine(id: number) {
    return this.http.post(this.url + '/stop?id=' + id, {"id": id} );
  }

  restartMachine(id: number) {
    return this.http.post(this.url + '/restart?id=' + id, {"id": id} );
  }

  deleteMachine(id: number) {
    return this.http.delete(this.url + "?id=" + id);
  }

}
