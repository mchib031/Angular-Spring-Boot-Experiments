import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router, Routes} from '@angular/router';
import {BookComponent} from './book/book.component';

export const booksRoutes: Routes = [
  {path: ':id', component: BookComponent}
];

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {
  constructor(private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
  }

  submit(value: string): void {
    this.router.navigate(['./', value], {relativeTo: this.route});
  }
}
