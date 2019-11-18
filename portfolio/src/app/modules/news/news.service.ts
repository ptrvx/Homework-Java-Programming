import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  private url: string = 'https://newsapi.org/v2/everything';
  private param: string = '?language=en&pageSize=20&page=1&apiKey=';
  private apiKey: string = 'a81a15bb0c7a473abe42ee3a69a38314';
  private qParam: string = '&q=';

  constructor(private http: HttpClient) { }

  getNews(keyword: string) {
    return this.http.get(this.url + this.param + this.apiKey + this.qParam + keyword);
  }

}
