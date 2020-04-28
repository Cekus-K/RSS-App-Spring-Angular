import { Component, OnInit } from '@angular/core';
import { RssService } from 'src/app/services/rss.service';
import { Rss } from 'src/app/common/rss';
import {Content} from '../../common/content';

@Component({
  selector: 'app-rss',
  templateUrl: './rss.component.html',
  styleUrls: ['./rss.component.css']
})
export class RssComponent implements OnInit {

  rssList: Rss[];
  rss = new Rss();
  content: Content;

  constructor(private rssService: RssService) {
  }

  ngOnInit(): void {
  }

  getRssList(): void {
    this.rssService.getRssList().subscribe(data => this.rssList = data);
  }

  getRssContent(): void {
    this.rssService.getRssContent().subscribe(data => this.content = data);
  }

  addRss(): void {
    this.rssService.addRss(this.rss).subscribe(this.getRssList, this.reset);
  }

  private reset() {
    this.rss.link = null;
  }
}
