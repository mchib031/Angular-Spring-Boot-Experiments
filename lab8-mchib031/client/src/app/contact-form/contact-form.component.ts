import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ContactListingService } from '../contact-list/contact-listing.service';
@Component({
  selector: 'app-contact-form',
  templateUrl: './contact-form.component.html',
  styleUrls: ['./contact-form.component.css'],
})
export class ContactFormComponent implements OnInit {
  contactForm: FormGroup;

  constructor(
    private contactListingService: ContactListingService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.contactForm = new FormGroup({
      firstName: new FormControl(null, Validators.required),
      lastName: new FormControl(null, Validators.required),
      phoneNumber: new FormControl(
        null,
        Validators.pattern(/[1-9]\d{2}[1-9]\d{6}/)
      ),
      email: new FormControl(null, Validators.email),
    });
  }

  onSubmit() {
    console.log(this.contactForm.getRawValue());
    const { firstName, lastName, phoneNumber, email } =
      this.contactForm.getRawValue();
    this.contactListingService
      .addUser(firstName, lastName, phoneNumber, email)
      .subscribe((_) => {
        this.contactForm.reset();
        this.router.navigate(['./contacts']);
      });
  }
}
