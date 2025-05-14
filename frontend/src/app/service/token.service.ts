import { Injectable } from '@angular/core';

const TOKEN_KEY = 'AuthToken';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  roles: Array<string> = [];

  constructor() { }

  public setToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string | null {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public getUserName(): string {
    const token = this.getToken();
    if (!token) return '';

    try {
      const payload = token.split('.')[1];
      const parsedPayload = JSON.parse(decodeJwtPayload(payload));
      return parsedPayload.username || '';
    } catch (e) {
      return '';
    }
  }

  public getEmail(): string {
    const token = this.getToken();
    if (!token) return '';

    try {
      const payload = token.split('.')[1];
      const parsedPayload = JSON.parse(decodeJwtPayload(payload));
      return parsedPayload.sub || '';
    } catch (e) {
      return '';
    }
  }

  public getAuthorities(): string[] {
    const token = this.getToken();
    if (!token) return [];

    try {
      const payload = token.split('.')[1];
      const parsedPayload = JSON.parse(decodeJwtPayload(payload));
      return parsedPayload.roles || [];
    } catch (e) {
      return [];
    }
  }

  public logOut(): void {
    window.sessionStorage.clear();
  }
}

function decodeJwtPayload(payload: string): string {
  const base64 = payload.replace(/-/g, '+').replace(/_/g, '/');
  const jsonPayload = decodeURIComponent(
    atob(base64)
      .split('')
      .map(c => '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2))
      .join('')
  );
  return jsonPayload;
}