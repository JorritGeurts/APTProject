import { inject, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AuthConfig, OAuthService } from 'angular-oauth2-oidc';

@Injectable({
  providedIn: 'root'
})
export class AuthGoogleService {
  private oAuthService = inject(OAuthService)
  private router = inject(Router)

  constructor() { 
    this.initConfiguration();
  }

  initConfiguration() {
    const authConfig: AuthConfig = {
      issuer: 'https://accounts.google.com',
      strictDiscoveryDocumentValidation: false,
      clientId: '85447181634-1fle16mtgv0bgb4u2t5n1m1lp1sugv1q.apps.googleusercontent.com',
      redirectUri: window.location.origin + "/",
      scope: 'openid profile email',
      showDebugInformation: true, // Logs OAuth details to console
    }

    this.oAuthService.configure(authConfig);
    this.oAuthService.setupAutomaticSilentRefresh();

    this.oAuthService.setStorage(localStorage)

    this.oAuthService.loadDiscoveryDocumentAndTryLogin();
  }

  login(){
    this.oAuthService.initImplicitFlow();
  }

  logout() {
    this.oAuthService.revokeTokenAndLogout();
    this.oAuthService.logOut();
  }

  getProfile() {
    return this.oAuthService.getIdentityClaims();
  }

  getToken() {
    return this.oAuthService.getAccessToken();
  }

  isAuthenticated(): boolean {
    return this.oAuthService.hasValidAccessToken();
  }

}
