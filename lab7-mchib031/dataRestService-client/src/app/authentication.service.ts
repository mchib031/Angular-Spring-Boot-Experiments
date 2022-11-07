import { Injectable } from '@angular/core';
import {Router} from '@angular/router';
import {noop} from 'rxjs';

@Injectable({
providedIn: 'root'
})
export class AuthenticationService {
redirectUrl: string | null | undefined;

constructor(private router: Router) {}

  login(user: string, password: string): boolean {
    // hard coded for now
    if (user === 'admin' && password === 'password') {
      sessionStorage.setItem('username', user);
      if (this.redirectUrl) { this.router.navigate([this.redirectUrl]).then(noop); }
      this.redirectUrl = null;
      return true;
    }
    return false;
  }

  logout(): any {
    sessionStorage.removeItem('username');
  }

  getUser(): any {
    return sessionStorage.getItem('username');
  }

  isLoggedIn(): boolean {
    return this.getUser() !== null;
  }
}
