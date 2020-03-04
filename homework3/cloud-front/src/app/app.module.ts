import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { FormsModule, ReactiveFormsModule } from '@angular/forms'
import { MatButtonModule, MatTableModule, MatDividerModule, MatInputModule, MatFormFieldModule, MatSelectModule } from '@angular/material';
import { MatCardModule, MatCheckboxModule, MatDatepickerModule, MatNativeDateModule } from '@angular/material';
import { MatExpansionModule } from '@angular/material';
import { HttpClientModule } from '@angular/common/http'
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { SearchComponent } from './search/search.component';
import { CreateComponent } from './create/create.component';
import { LoginComponent } from './login/login.component';


@NgModule({
  declarations: [
    AppComponent,
    SearchComponent,
    CreateComponent,
    LoginComponent
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
    MatExpansionModule,
    MatTableModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatNativeDateModule,
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
