import { Component, OnInit } from '@angular/core';
import { NewsService } from 'src/app/modules/news/news.service';

import { faHeart as fasHeart } from '@fortawesome/free-solid-svg-icons';
import { faHeart as farHeart } from '@fortawesome/free-regular-svg-icons';
import { faExternalLinkAlt } from '@fortawesome/free-solid-svg-icons'

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.scss']
})
export class NewsComponent implements OnInit {

  public news: Array<object>;
  public keyword: string = 'programming';
  public likes: Array<string> = [];

  public fasHeart = fasHeart;
  public farHeart = farHeart;
  public faLink = faExternalLinkAlt;

  constructor(public newsService: NewsService) { }

  ngOnInit() {
    this.getStoredLikes();
    this.showNews();
  }

  showNews(): void {
    this.newsService.getNews(this.keyword).subscribe((data) => {
      this.news = data['articles'];
      for (let n of this.news) {
        n['liked'] = false;
      }
      this.checkLikes();
    }
    );
  }

  getStoredLikes(): void {
    this.likes = JSON.parse(localStorage.getItem('likes')) || [];
  }

  storeLikes(): void {
    localStorage.setItem('likes', JSON.stringify(this.likes));
  }

  checkLikes(): void {
    for (let n of this.news) {
      if (this.likes && this.likes.includes(n['url'])) {
        n['liked'] = true;
      } else {
        n['liked'] = false;
      }
    }
  }

  like(url: string): void {
    this.getStoredLikes();
    if (this.likes.includes(url)) {
      this.likes = this.likes.filter(a => a != url);
    } else {
      this.likes.push(url);
    }
    this.storeLikes();
    this.checkLikes();
  }

}
