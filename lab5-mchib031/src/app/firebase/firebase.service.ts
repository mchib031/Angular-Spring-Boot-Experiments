import { Injectable } from '@angular/core';
import { AngularFirestore, DocumentChangeAction, DocumentReference} from '@angular/fire/compat/firestore';
import { Observable } from 'rxjs';
import { interf } from './firebase.interface';

@Injectable({
  providedIn: 'root'
})

export class FirebaseService {

  constructor(private firestore: AngularFirestore) {  }

  addToDB(): Observable<DocumentChangeAction<unknown>[]> {
    return this.firestore.collection('user').snapshotChanges();
  }

  addUser(user: interf): Promise<DocumentReference> {
    delete user.id;
    return this.firestore.collection('user').add({...user});
  }

  deleteUser(x: string): Promise<void>{
    return this.firestore.collection('user').doc(x).delete();
  }
}
