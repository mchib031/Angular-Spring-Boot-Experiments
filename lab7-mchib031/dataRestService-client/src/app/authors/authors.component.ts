import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router, Routes} from '@angular/router';
import {AuthorComponent} from './author/author.component';

export const authorsRoutes: Routes = [
  {path: ':id', component: AuthorComponent}
];

@Component({
  selector: 'app-authors',
  templateUrl: './authors.component.html',
  styleUrls: ['./authors.component.css']
})
export class AuthorsComponent implements OnInit {
  constructor(private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
  }

  submit(value: string): void {
    this.router.navigate(['./', value], {relativeTo: this.route});
  }
}


