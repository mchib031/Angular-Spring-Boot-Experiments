import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { FormBuilder } from '@angular/forms';
import { AbstractControl,Validators } from '@angular/forms';
import { interf as fdb } from '../firebase/firebase.interface';
import { FirebaseService } from '../firebase/firebase.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})

export class FormComponent implements OnInit{
  myFormControl = this.fb.group({
    id:[''],
    firstName:['',[Validators.required]],
    lastName:['',[Validators.required]],
    phoneNumber:['',[Validators.required, Validators.maxLength(10), Validators.minLength(10)]],
    email:['',[Validators.required, Validators.email]]
});

  users : fdb[] = [];

  get firstName(){
    return this.myFormControl.value.firstName;
  }
  get lastName() {
    return this.myFormControl.value.lastName;
  }
  get phoneNumber() {
    return this.myFormControl.value.phoneNumber;
  }
  get email() {
    return this.myFormControl.value.email;
  }


  constructor(private fb: FormBuilder, private firebaseService: FirebaseService) {
  }

  ngOnInit(): void {
    this.firebaseService.addToDB().subscribe(data =>{
      this.users = data.map((element) => {
          return { id: element.payload.doc.id,...(element.payload.doc.data() as object)} as fdb
          })
  })

  }

  deleteUser(x):void{
      this.firebaseService.deleteUser(x);
  }

  addUser():void {
    console.warn(this.myFormControl.value);
    this.firebaseService.addUser({
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      phoneNumber: this.phoneNumber } as fdb);

  }

}
