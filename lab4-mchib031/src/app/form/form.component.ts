import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { FormBuilder } from '@angular/forms';
import { AbstractControl,Validators } from '@angular/forms';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})

export class FormComponent {
  myFormControl = this.fb.group({
    firstName:['',[Validators.required]],
    lastName:['',[Validators.required]],
    phoneNumber:['',[Validators.required, Validators.maxLength(10), Validators.minLength(10)]],
    email:['',[Validators.required, Validators.email]]
});

  get firstName():AbstractControl {
    return this.myFormControl.get('firstName');
  }
  get lastName():AbstractControl {
    return this.myFormControl.get('lastName');
  }
  get phoneNumber():AbstractControl {
    return this.myFormControl.get('phoneNumber');
  }
  get email():AbstractControl {
    return this.myFormControl.get('email');
  }


  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {}

  onSubmit():void {
    console.warn(this.myFormControl.value);
  }

}
