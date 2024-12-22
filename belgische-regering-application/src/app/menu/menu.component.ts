import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthGoogleService } from '../services/auth-google.service';

//AuthModules
const MODULES: any[] = [FormsModule, ReactiveFormsModule]

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [RouterModule, CommonModule, MODULES],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent implements OnInit {
  private authService = inject(AuthGoogleService)

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  hamburgerOpen = false;
  adminDropdownOpen = false;

  toggleHamburger(): void {
    this.hamburgerOpen = !this.hamburgerOpen;
  }

  onHamburgerItemClick() {
    if (this.hamburgerOpen) {
      this.hamburgerOpen = false;
    }
  }
  onAdminDropDownClick() {
    this.adminDropdownOpen = !this.adminDropdownOpen;
  }

  closeAdminDropDown() {
    this.adminDropdownOpen = false;
  }

  navigateTo(path: string) {
    this.closeAdminDropDown();
    this.hamburgerOpen = false;
    this.router.navigate([path]);
  }

  //Authentication
  singInWithGoogle() {
    this.authService.login();
  }

  Logout(){
    this.authService.logout();
    this.router.navigate(["/"]);
  }

  isAuthenticated(): boolean{
    return this.authService.isAuthenticated()
  }

}
