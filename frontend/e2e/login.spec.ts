import { test, expect } from '@playwright/test';

test.describe('Login', () => {
    test.beforeEach(async ({ page }) => {
        await page.goto('/login');
    });

    test('login with correct student credentials redirects to home page', async ({ page }) => {
        await page.fill('#email', 'alumno1@gmail.com');
        await page.fill('#password', 'alumno1');
        await page.click('#button-login');
        await expect(page).toHaveURL('/competiciones');
    });

    test('login with correct professor credentials redirects to home page', async ({ page }) => {
        await page.fill('#email', 'profesor1@gmail.com');
        await page.fill('#password', 'profesor1');
        await page.click('#button-login');
        await expect(page).toHaveURL('/competiciones');
    });

    test('login with correct admin credentials redirects to home page', async ({ page }) => {
        await page.fill('#email', 'admin@admin.com');
        await page.fill('#password', 'admin1234');
        await page.click('#button-login');
        await expect(page).toHaveURL('/usuarios');
    });

    test('login with incorrect credentials shows error message', async ({ page }) => {
        await page.fill('#email', 'email@gmail.com');
        await page.fill('#password', 'pass');
        await page.click('#button-login');
        await expect(page.getByText('Error en el login:')).toBeVisible();
    });
});