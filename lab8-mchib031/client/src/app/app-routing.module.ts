import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContactFormComponent } from './contact-form/contact-form.component';
import { ContactListComponent } from './contact-list/contact-list.component';

const routes: Routes = [
  { path: 'contact-form', component: ContactFormComponent },
  { path: 'contacts', component: ContactListComponent },
  { path: '', redirectTo: 'contact-form', pathMatch: 'full' },
  { path: '**', component: ContactFormComponent },
];

@NgModule({
  imports: [CommonModule, RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
