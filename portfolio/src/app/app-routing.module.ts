import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './modules/home/home.component';
import { NewsComponent } from './modules/news/news.component';
import { ContactComponent } from './modules/contact/contact.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'news',
    component: NewsComponent
  },
  {
    path: 'contact',
    component: ContactComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
