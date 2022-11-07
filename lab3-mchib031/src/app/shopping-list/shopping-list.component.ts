import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-shopping-list',
  templateUrl: './shopping-list.component.html',
  styleUrls: ['./shopping-list.component.css']
})
export class ShoppingListComponent implements OnInit {

  
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