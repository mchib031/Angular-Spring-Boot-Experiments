import { Injectable } from '@angular/core';
import {Author, Book} from '../model/book';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from 'rxjs/operators';

const Url = 'http://localhost:8080/books-api/';

@Injectable({
  providedIn: 'root'
})
export class BooksService {
  constructor(private http: HttpClient) {}

  public getBook(id: string): Observable<Book> {
    return this.http.get<Book>(Url + 'books/' + id);
  }

  public addBook(book: Book): Observable<Book> {
    return this.http.post<Book>(Url + 'books', book);
  }

  public addBookAuthor(id: number, author: Author): Observable<Author> {
    return this.http.post<Author>(Url + 'books/' + id + '/authors', author);
  }

  public getAuthorsNamed(firstName: string, lastName: string): Observable<any> {
    const options = {params: new HttpParams().set('firstName', firstName).set('lastName', lastName)};
    return this.http.get<any>(Url + 'authors', options).pipe(
      map(response => response._embedded ? response._embedded.authors : undefined )
    );
  }

  public updateBookAuthors(bookId: number, authorId: number): Observable<any> {
    return this.http.patch(Url + 'books/' + bookId + '/authors/' + authorId, {});
  }
}
