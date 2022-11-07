import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Author} from '../model/author';
import {AuthorsService} from '../service/authors.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-author',
  templateUrl: './author.component.html',
  styleUrls: ['./author.component.css']
})
export class AuthorComponent implements OnInit, OnDestroy {
  selectedAuthor!: Author | null;
  private subscription!: Subscription;

  constructor(private route: ActivatedRoute, private authorsService: AuthorsService) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const id = params.id;
      this.subscription = this.authorsService.getAuthor(id).subscribe(
        (data: Author) => {
          this.selectedAuthor = data;
        },
        (_: any) => {
          this.selectedAuthor = null;
        });
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
