import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent implements OnInit{
 
  item! : Items;
  ListeDesArticles: Items[] = []


  ngOnInit(): void {
   this.resetForm();
  }

  resetForm() {
   this.item = { article: ''};
  }

  ajouter() {
   this.ListeDesArticles.push(this.item);
   this.resetForm();
  }

  deleteItem(index: number) {
    this.ListeDesArticles.splice(index, 1);
  }
}
interface Items {
  article: string;
 }
