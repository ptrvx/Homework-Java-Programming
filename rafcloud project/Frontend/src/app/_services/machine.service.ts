import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Machine } from '../_models/machine';
import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MachineService {

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<Machine[]>(`${environment.restUrl}search`);
  }

  create(name: string) {
    return this.http.post<any>(`${environment.restUrl}create`, name );

  }

  search(name: string, stopped: boolean, running: boolean, dateFrom: string, dateTo: string) {
    let params = new HttpParams();
    if (name) params = params.set('name', name);
    if (stopped) params = params.append('status', 'STOPPED');
    if (running) params = params.append('status', 'RUNNING');
    if (dateFrom) params = params.set('dateFrom', dateFrom);
    if (dateTo) params = params.set('dateTo', dateTo);

    return this.http.get<Machine[]>(`${environment.restUrl}search`, { params });
  }

  start(id: number) {
    return this.http.post<any>(`${environment.restUrl}start?id=${id}`, {});
  }

  stop(id: number) {
    return this.http.post(`${environment.restUrl}stop?id=${id}`, {});
  }

  restart(id: number) {
    return this.http.post<any>(`${environment.restUrl}restart?id=${id}`, {});
  }

  destroy(id: string) {
    let params = new HttpParams().set('id', id);
    return this.http.delete(`http://localhost:8081/rest?id=${id}`, {});
  }

}
