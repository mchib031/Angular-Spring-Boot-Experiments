import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-shopping-view',
  templateUrl: './shopping-view.component.html',
  styleUrls: ['./shopping-view.component.css']
})
export class ShoppingViewComponent implements OnInit {

  
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