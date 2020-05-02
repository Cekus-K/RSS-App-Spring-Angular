import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Rss } from '../common/rss';
import { Observable } from 'rxjs';
import {Content} from '../common/content';

@Injectable({
  providedIn: 'root'
})
export class RssService {

  baseUrl = 'http://localhost:8080/api/rss/';

  constructor(private http: HttpClient) { }

  getRssList(): Observable<Rss[]> {
    return this.http.get<Rss[]>(this.baseUrl);
  }

  getRssContent(): Observable<Content> {
    return this.http.get<Content>(this.baseUrl + 'content');
  }

  addRss(rss: Rss): Observable<Rss> {
    return this.http.post<Rss>(this.baseUrl, JSON.stringify(rss));
  }

  send(): Observable<void> {
    return this.http.get<void>(this.baseUrl + 'send');
  }
}
