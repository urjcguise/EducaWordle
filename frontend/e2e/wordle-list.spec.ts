import { test, expect } from '@playwright/test';

test.describe('Professor tests without folders', () => {
    test.beforeEach(async ({ page }) => {
        await page.goto('/login');
        await page.fill('#email', 'profesor1@gmail.com');
        await page.fill('#password', 'profesor1');
        await page.click('#button-login');
        await expect(page).toHaveURL('/competiciones');
        await page.goto('/wordles');
        await expect(page).toHaveURL('/wordles');
    });

    test('page name appears correctly', async ({ page }) => {
        await expect(page.locator('h2')).toHaveText('Lista de Wordles');
    });

    test('show initial buttons', async ({ page }) => {
        await expect(page.getByRole('button', { name: 'Crear wordle' })).toBeVisible();
        await expect(page.getByRole('button', { name: 'Crear carpeta' })).toBeVisible();

        await expect(page.getByRole('button', { name: 'Editar wordle' })).toBeHidden();
        await expect(page.getByRole('button', { name: 'Mover a carpeta' })).toBeHidden();
        await expect(page.getByRole('button', { name: 'Borrar' })).toBeHidden();
    });

    test('show input field after clicking in "Crear carpeta" button', async ({ page }) => {
        await page.getByRole('button', { name: 'Crear carpeta' }).click();
        await expect(page.getByPlaceholder('Nombre de la carpeta')).toBeVisible();
    });

    test('select one wordle activate buttons', async ({ page }) => {
        await page.locator('.wordle-root-item').nth(0).click();

        await expect(page.getByRole('button', { name: 'Editar wordle' })).toBeVisible();
        await expect(page.getByRole('button', { name: 'Borrar' })).toBeVisible();
    });

    test('open and close modal after "Crear wordle" button pushed', async ({ page }) => {
        await page.getByRole('button', { name: 'Crear wordle' }).click();
        await expect(page.locator('.modal')).toBeVisible();

        await page.click('.wordle-list-title', { force: true });
        await expect(page.locator('.modal')).toBeHidden();
    });

    test('hide wordle selection after clicking outside', async ({ page }) => {
        await page.locator('.wordle-root-item').nth(0).click();
        await expect(page.getByRole('button', { name: 'Editar wordle' })).toBeVisible();

        await page.mouse.click(0, 0);
        await expect(page.getByRole('button', { name: 'Editar wordle' })).toBeHidden();
    });
});

test.describe('Professor tests with folders', () => {
    test.beforeEach(async ({ page }) => {
        await page.goto('/login');
        await page.fill('#email', 'profesor2@gmail.com');
        await page.fill('#password', 'profesor2');
        await page.click('#button-login');
        await expect(page).toHaveURL('/competiciones');
        await page.goto('/wordles');
        await expect(page).toHaveURL('/wordles');
    });

    test('select wordle with folder activate "Mover a carpeta" button', async ({ page }) => {
        await page.locator('.wordle-root-item').nth(0).click();

        await expect(page.getByRole('button', { name: 'Mover a carpeta' })).toBeVisible();
    });

    test('double click to a folder navigates to folder view', async ({ page }) => {
        await page.locator('app-folder-display').first().dblclick();

        await expect(page).toHaveURL('/1702/wordles');
    });

    test('edit wordle redirect to edit view', async ({ page }) => {
        await page.locator('.wordle-root-item').nth(0).click();
        await page.getByRole('button', { name: 'Editar wordle' }).click();
        await expect(page).toHaveURL('/QWERTY/editarWordle');
    });

    test('only appears "Borrar" button if wordle and folder are clicked at the same time', async ({ page }) => {
        await page.locator('.wordle-root-item').first().click();
        await page.locator('app-folder-display').first().click();

        await expect(page.getByRole('button', { name: 'Crear wordle' })).toBeHidden();
        await expect(page.getByRole('button', { name: 'Crear carpeta' })).toBeHidden();
        await expect(page.getByRole('button', { name: 'Editar wordle' })).toBeHidden();
        await expect(page.getByRole('button', { name: 'Mover a carpeta' })).toBeHidden();
        await expect(page.getByRole('button', { name: 'Borrar' })).toBeVisible();
    });
});

test.describe('Edit wordle page', () => {
    test.beforeEach(async ({ page }) => {
        await page.goto('/login');
        await page.fill('#email', 'profesor2@gmail.com');
        await page.fill('#password', 'profesor2');
        await page.click('#button-login');
        await expect(page).toHaveURL('/competiciones');
        await page.goto('/QWERTY/editarWordle');
        await expect(page).toHaveURL('/QWERTY/editarWordle');
    });

    test('form appears correctly', async ({ page }) => {
        await expect(page.getByRole('heading', { name: 'Editar Wordle' })).toBeVisible();
        await expect(page.locator('.edit-section-title').first()).toHaveText('Palabra');
        await expect(page.getByPlaceholder('Escribe la palabra del Wordle')).toBeVisible();
        await expect(page.locator('.edit-section-title').last()).toHaveText('Asignar a competiciones');
        await expect(page.getByRole('button', { name: /Guardar Wordle/i })).toBeVisible();
    });

    test('wordle name is not empty', async ({ page }) => {
        await expect(page.locator('input#wordle')).toHaveValue(/.+/);
    });

    test('no competitions text', async ({ page }) => {
        await expect(page.getByText('No hay competiciones disponibles.')).toBeVisible();
    });
});

test.describe('Folders functionalities', () => {
    test.beforeEach(async ({ page }) => {
        await page.goto('/login');
        await page.fill('#email', 'profesor2@gmail.com');
        await page.fill('#password', 'profesor2');
        await page.click('#button-login');
        await expect(page).toHaveURL('/competiciones');
        await page.goto('/1702/wordles');
        await expect(page).toHaveURL('/1702/wordles');
    });

    test('parents folders are visible', async ({ page }) => {
        await expect(page.locator('.list-header .history .parent-folder')).toHaveCount(2);
        await expect(page.locator('.list-header .history .parent-folder').last()).toHaveText('Carpeta');
    });

    test('click in "..." redirect to wordle list', async ({ page }) => {
        await page.locator('.list-header .history .parent-folder', { hasText: '...' }).click();
        await expect(page).toHaveURL('/wordles');
    });
});