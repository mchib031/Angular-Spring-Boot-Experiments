import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { ContactComponent } from './contact/contact.component';
import { BooksComponent } from './books/books.component';
import { BookComponent } from './books/book/book.component';
import { AuthornamesPipe } from './pipes/authornames.pipe';
import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { AuthorsComponent } from './authors/authors.component';
import { AuthorComponent } from './authors/author/author.component';

@NgModule({
declarations: [
AppComponent,
HomeComponent,
AboutComponent,
ContactComponent,
BooksComponent,
BookComponent,
AuthornamesPipe,
LoginComponent,
AdminComponent,
AuthorsComponent,
AuthorComponent
],
imports: [
BrowserModule,
AppRoutingModule,
NgbModule,
FormsModule,
ReactiveFormsModule,
HttpClientModule
],
providers: [],
bootstrap: [AppComponent]
})
export class AppModule { }
