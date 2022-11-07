import { Injectable } from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router} from '@angular/router';
import { Observable } from 'rxjs';
import {AuthenticationService} from './authentication.service';

@Injectable({
providedIn: 'root'
})
export class LoggedInGuard implements CanActivate {
constructor(private authService: AuthenticationService, private router: Router) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    // return  this.authService.isLoggedIn();
    if (this.authService.isLoggedIn()) {return true; }
    this.authService.redirectUrl = state.url;
    this.router.navigate(['./login']);
    return false;
  }
}
