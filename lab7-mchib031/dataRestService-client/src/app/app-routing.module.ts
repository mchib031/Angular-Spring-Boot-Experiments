import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './home/home.component';
import {AboutComponent} from './about/about.component';
import {ContactComponent} from './contact/contact.component';
import {BooksComponent} from './books/books.component';
import {BookComponent} from './books/book/book.component';
import {LoginComponent} from './login/login.component';
import {AdminComponent} from './admin/admin.component';
import {LoggedInGuard} from './logged-in.guard';
import {AuthorComponent} from './authors/author/author.component';
import {AuthorsComponent} from './authors/authors.component'

const booksRoutes: Routes = [
{path: ':id', component: BookComponent}
];

const authorsRoutes: Routes = [
{path: ':id', component: AuthorComponent}
];

const routes: Routes = [
{path: 'home', component: HomeComponent},
{path: 'about', component: AboutComponent},
{path: 'contact', component: ContactComponent},
{ path: 'login', component: LoginComponent },
{
path: 'admin',
component: AdminComponent,
canActivate: [ LoggedInGuard ]
},
{
path: 'authors', component: AuthorsComponent,
children: authorsRoutes
},
{path: 'books', component: BooksComponent,
children: booksRoutes
},
{path: '', redirectTo: 'home', pathMatch: 'full'},
{path: '**', component: HomeComponent}
];


@NgModule({
imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
