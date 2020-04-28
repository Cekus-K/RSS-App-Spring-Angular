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

  getRssById(rssId: number): Observable<Rss> {
    return this.http.get<Rss>(this.baseUrl + rssId);
  }

  addRss(rss: Rss): Observable<Rss> {
    return this.http.post<Rss>(this.baseUrl, JSON.stringify(rss));
  }

  deleteRss(rssId: number) {
    return this.http.delete(this.baseUrl + rssId);
  }
}
