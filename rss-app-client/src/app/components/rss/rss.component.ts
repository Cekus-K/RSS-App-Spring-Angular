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
  contentGets = false;
  afterSend: Content = new Content();

  constructor(private rssService: RssService) {
  }

  ngOnInit(): void {
    this.getRssList();
  }

  getRssList(): void {
    this.rssService.getRssList().subscribe(data => this.rssList = data);
  }

  getRssContent(): void {
    this.contentGets = true;
    this.rssService.getRssContent().subscribe(data => this.content = data);
  }

  addRss(): void {
    this.rssService.addRss(this.rss).subscribe(() => this.getRssList());
    this.reset();
  }

  sendToEmail(): void {
    this.rssService.send().subscribe();
    this.afterSend.text = 'The content was sent to the email address!';
    this.content = this.afterSend;
  }

  private reset() {
    this.rss.link = null;
  }
}
