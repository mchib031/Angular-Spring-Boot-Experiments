import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ContactListingService } from './contact-listing.service';
import { User } from './user';

@Component({
  selector: 'app-contact-list',
  templateUrl: './contact-list.component.html',
  styleUrls: ['./contact-list.component.css'],
})
export class ContactListComponent implements OnInit, OnDestroy {
  contacts: User[] | null = null;
  subscription: Subscription;
  constructor(private contactListingService: ContactListingService) {}

  ngOnInit(): void {
    this.subscription = this.contactListingService.getUsers().subscribe(
      ({ data }) => {
        this.contacts = data.users;
      },
      (error) => {
        this.contacts = null;
      }
    );
  }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
