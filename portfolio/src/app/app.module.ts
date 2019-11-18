import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './modules/home/home.component';
import { NewsComponent } from './modules/news/news.component';
import { ContactComponent } from './modules/contact/contact.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'

import { MatButtonModule, MatDividerModule, MatInputModule, MatFormFieldModule, MatSelectModule } from '@angular/material';
import { MatCardModule } from '@angular/material';
import { MatExpansionModule } from '@angular/material';

import { HttpClientModule } from '@angular/common/http';

import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { FooterComponent } from './footer/footer.component';
import { MenuComponent } from './menu/menu.component'

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NewsComponent,
    ContactComponent,
    FooterComponent,
    MenuComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,

    MatButtonModule,
    MatDividerModule,
    MatInputModule,
    MatFormFieldModule,
    MatSelectModule,
    MatCardModule,
    FontAwesomeModule,
    MatExpansionModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
