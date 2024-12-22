import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthGoogleService } from './auth-google.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthGoogleService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean | Observable<boolean> | Promise<boolean> {
    const isAuthenticated = this.authService.isAuthenticated();
    console.log('AuthGuard: isAuthenticated =', isAuthenticated); // Define this method in your AuthService
    if (!isAuthenticated) {
      this.router.navigate(['/']); // Redirect to login if not authenticated
      return false;
    }
    return true;
  }
}
