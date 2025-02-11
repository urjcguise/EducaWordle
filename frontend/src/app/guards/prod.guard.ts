import { inject } from "@angular/core";
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable } from "rxjs";
import { TokenService } from "../service/token.service";

export const prodGuard = (
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree => {
    const tokenService = inject(TokenService);
    const router = inject(Router);

    const expectedRol = route.data['expectedRol'];
    const roles = tokenService.getAuthorities();
    let realRol = '';

    roles.forEach(rol => {
        if (rol === 'ROLE_ADMIN') {
            realRol = 'admin';
        } else if (rol === 'ROLE_PROFESSOR') {
            realRol = 'professor';
        } else if (rol === 'ROLE_STUDENT') {
            realRol = 'student';
        }
    });

    if (!tokenService.getToken() || expectedRol.indexOf(realRol) === -1) {
        router.navigate(['/']);
        return false;
    }
    return true;
}