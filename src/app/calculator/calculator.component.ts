import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-calculator',
  templateUrl: './calculator.component.html',
  styleUrls: ['./calculator.component.css']
})
export class CalculatorComponent {
  firstnum = 0;
  secondnum = 0;
  resultat = '0';
  constructor() { }

  add(value1: string, value2: string): void {
    this.firstnum = Number(value1);
    this.secondnum = Number(value2);
    this.resultat = (this.firstnum + this.secondnum).toFixed(2);
  }

  subtract(value1: string, value2: string): void {
    this.firstnum = Number(value1);
    this.secondnum = Number(value2);
    this.resultat = (this.firstnum - this.secondnum).toFixed(2);
  }

  multiply(value1: string, value2: string): void {
    this.firstnum = Number(value1);
    this.secondnum = Number(value2);
    this.resultat = (this.firstnum * this.secondnum).toFixed(2);
  }

  divide(value1: string, value2: string): void {
    this.firstnum = Number(value1);
    this.secondnum = Number(value2);
    if (this.secondnum == 0){
      this.resultat = 'undefined';
    } else {
      this.resultat = (this.firstnum / this.secondnum).toFixed(2);
    }
  }

}
