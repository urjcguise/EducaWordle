import { test, expect } from '@playwright/test';

test('main page load successfully', async ({page}) => {
    await page.goto('http://localhost:4200/');
    await expect(page).toHaveTitle('Educa Wordle');
});

test('clicking on the button "Acceso usuarios URJC" should redirect to login page', async ({ page }) => {
    await page.goto('http://localhost:4200/');
    await page.click('a.login-button');
    await expect(page).toHaveURL('/login');
});