import { test, expect } from '@playwright/test';

test.describe('General tests', () => {
    test.beforeEach(async ({ page }) => {
        await page.goto('/login');
        await page.fill('#email', 'alumno1@gmail.com');
        await page.fill('#password', 'alumno1');
        await page.click('#button-login');
        await expect(page).toHaveURL('/competiciones');
    });

    test('"Competiciones" text appears', async ({ page }) => {
        await expect(page.getByRole('heading', { name: 'Competiciones ' })).toBeVisible();
    });

    test('show and hide contest list', async ({ page }) => {
        const arrow = page.locator('.arrow').first();
        await arrow.click();
        await expect(page.locator('.contest-list').first()).toBeHidden();
        await arrow.click();
        await expect(page.locator('.contest-list').first()).toBeVisible();
    });

    test('navigate to competition view', async ({ page }) => {
        const firstCompetition = page.locator('.competition-header h3').first();
        const competitionName = await firstCompetition.textContent();
        await firstCompetition.click();
        await expect(page).toHaveURL(`/competicion/${competitionName}`);
    });

    test('navigate to contest', async ({ page }) => {
        const firstContest = page.locator('.contest-list ul li .contest-link').first();
        await firstContest.click();
        await expect(page.url()).toMatch(/^http:\/\/localhost:4200\/\d+\/concurso$/);
    });
});

test.describe('Tests for professors', () => {
    test.beforeEach(async ({ page }) => {
        await page.goto('/login');
        await page.fill('#email', 'profesor1@gmail.com');
        await page.fill('#password', 'profesor1');
        await page.click('#button-login');
        await expect(page).toHaveURL('/competiciones');
    });

    test('button "Añadir nueva competición" is visible', async ({ page }) => {
        await expect(page.getByRole('button', { name: 'Añadir nueva competición' })).toBeVisible();
    });

    test('button "Añadir nueva competición" opens modal when is clicked', async ({ page }) => {
        await page.getByRole('button', { name: 'Añadir nueva competición' }).click();
        await expect(page.locator('.modal')).toBeVisible();
    });

    test('close new competition modal', async ({ page }) => {
        await page.getByRole('button', { name: 'Añadir nueva competición' }).click();
        await expect(page.locator('.modal')).toBeVisible();
        await page.click('.title-container', { force: true });
        await expect(page.locator('.modal')).toBeHidden();
    });
});