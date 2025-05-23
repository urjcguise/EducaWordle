import { test, expect } from '@playwright/test';

test.describe('Student Role Buttons', () => {
    
    test.beforeEach(async ({ page }) => {
        await page.goto('/login');
        await page.fill('#email', 'alumno1@gmail.com');
        await page.fill('#password', 'alumno1');
        await page.click('#button-login');
        await expect(page).toHaveURL('/competiciones');
    });

    test('button "Página Principal" is visible', async ({ page }) => {
        await expect(page.getByRole('button', { name: 'Página Principal' })).toBeVisible();
    });
    
    test('button "Página Principal" works correctly', async ({ page }) => {
        await page.goto('/wordlesResueltos');
        await page.click('button:has-text("Página Principal")');

        await expect(page).toHaveURL('/competiciones');
    });

    test('show "Cerrar Sesión" button in big screens', async ({ page }) => {
        await page.setViewportSize({ width: 1024, height: 768 });
        await page.goto('/competiciones');

        const button = page.getByRole('button', { name: 'Cerrar Sesión' });
        await expect(button).toBeVisible();
    });

    test('hide "Cerrar Sesión" button in small screens', async ({ page }) => {
        await page.setViewportSize({ width: 300, height: 300 });
        await page.goto('/wordlesResueltos');
       
        const button = page.getByRole('button', { name: 'Cerrar Sesión' });
        await expect(button).toBeHidden();
    });

    test('show "Ver Wordles" button', async ({ page }) => {
        await page.goto('/competiciones');
        await expect(page.getByRole('button', { name: 'Ver Wordles' })).toBeVisible();
    });

    test('button "Ver Wordles" works correctly if is student', async ({ page }) => {
        await page.click('button:has-text("Ver Wordles")');

        await expect(page).toHaveURL('/wordlesResueltos');
    });

    test('show "Atrás" button if showBackButton is true', async ({ page }) => {
        await page.goto('/wordlesResueltos');
        await expect(page.getByRole('button', { name: /atrás/i })).toBeVisible();
    });

    test('hide "Atrás" button if showBackButton is false', async ({ page }) => {
        await page.goto('/competiciones');
        await expect(page.getByRole('button', { name: /atrás/i })).toBeHidden();
    });

    test('reload page after pressing "Cerrar Sesión" button', async ({ page }) => {
        await page.goto('/competiciones');
        await page.click('button:has-text("Cerrar Sesión")');

        await page.waitForLoadState('load');
    });    
});

test.describe('Professor Role Buttons', () => {
    test.beforeEach(async ({ page }) => {
        await page.goto('/login');
        await page.fill('#email', 'profesor1@gmail.com');
        await page.fill('#password', 'profesor1');
        await page.click('#button-login');
        await expect(page).toHaveURL('/competiciones');
    });

    test('button "Ver Wordles" works correctly if is professor', async ({ page }) => {
        await page.click('button:has-text("Ver Wordles")');
        await expect(page).toHaveURL('/wordles');
    });
});

test.describe('Admin Role Button', () => {
    test.beforeEach(async ({ page }) => {
        await page.goto('/login');
        await page.fill('#email', 'admin@admin.com');
        await page.fill('#password', 'admin1234');
        await page.click('#button-login');
        await expect(page).toHaveURL('/usuarios');
    });

    test('button "Ver Usuarios" is visible', async ({ page }) => {
        await expect(page.getByRole('button', { name: 'Ver Usuarios' })).toBeVisible();
    });

    test('button "Ver Usuarios" works correctly', async ({ page }) => {
        await page.goto('/competiciones');
        await page.click('button:has-text("Ver Usuarios")');
        await expect(page).toHaveURL('/usuarios');
    });
});