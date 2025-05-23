import { test, expect } from '@playwright/test';

test.describe('Tests for admin', () => {
    test.beforeEach(async ({ page }) => {
        await page.goto('/login');
        await page.fill('#email', 'admin@admin.com');
        await page.fill('#password', 'admin1234');
        await page.click('#button-login');
        await expect(page).toHaveURL('/usuarios');
    });

    test('verify title', async ({ page }) => {
        await expect(page.locator('h1')).toHaveText('Usuarios disponibles');
        await expect(page.locator('.section-header h3', { hasText: 'Profesores' })).toBeVisible();
        await expect(page.locator('.section-header h3', { hasText: 'Alumnos' })).toBeVisible();
    });

    test('redirect to new professor', async ({ page }) => {
        await page.click('button:has-text("Añadir Profesor")');
        await expect(page).toHaveURL('/nuevoProfesor');
    });

    test('correct form in new professor', async ({ page }) => {
        await page.click('button:has-text("Añadir Profesor")');
        await expect(page).toHaveURL('/nuevoProfesor');

        await expect(page.locator('h4')).toHaveText('Crear Nueva Cuenta');
        await expect(page.locator('label[for="userName"]')).toHaveText('Nombre de Usuario');
        await expect(page.locator('label[for="email"]')).toHaveText('Correo Electrónico');
        await expect(page.locator('label[for="password"]')).toHaveText('Contraseña');

        await expect(page.locator('#userName')).toBeVisible();
        await expect(page.locator('#email')).toBeVisible();
        await expect(page.locator('#password')).toBeVisible();
        await expect(page.locator('button[type="submit"]')).toBeDisabled();
    });

    test('validate name creating new professor', async ({ page }) => {
        await page.click('button:has-text("Añadir Profesor")');
        await expect(page).toHaveURL('/nuevoProfesor');

        await page.locator('#userName').focus();
        await page.locator('#userName').blur();
        await expect(page.locator('.invalid-feedback')).toContainText('El nombre de usuario es obligatorio');
    });

    test('validate email creating new professor', async ({ page }) => {
        await page.click('button:has-text("Añadir Profesor")');
        await expect(page).toHaveURL('/nuevoProfesor');

        await page.locator('#email').focus();
        await page.locator('#email').blur();
        await expect(page.locator('.invalid-feedback')).toContainText('El correo electrónico es obligatorio');

        await page.locator('#email').fill('email');
        await page.locator('#email').blur();
        await expect(page.locator('.invalid-feedback')).toContainText('Introduce un formato de correo válido');
    });

    test('validate password creating new professor', async ({ page }) => {
        await page.click('button:has-text("Añadir Profesor")');
        await expect(page).toHaveURL('/nuevoProfesor');

        await page.locator('#password').focus();
        await page.locator('#password').blur();
        await expect(page.locator('.invalid-feedback')).toContainText('La contraseña es obligatoria');

        await page.locator('#password').fill('pass');
        await page.locator('#password').blur();
        await expect(page.locator('.invalid-feedback')).toContainText('La contraseña debe tener al menos 6 caracteres');
    });

    test('correct professor creating new professor', async ({ page }) => {
        await page.click('button:has-text("Añadir Profesor")');
        await expect(page).toHaveURL('/nuevoProfesor');

        await page.locator('#userName').fill('NewProfessor');
        await page.locator('#email').fill('email@email.com');
        await page.locator('#password').fill('123456');

        await expect(page.locator('button[type="submit"]')).toBeEnabled();
    });


    test('delete professor', async ({ page }) => {
        let confirmAppeared = false;

        page.on('dialog', async (dialog) => {
            if (dialog.type() === 'confirm') {
                confirmAppeared = true;
                await dialog.dismiss();
            }
        });

        await page.click('.user-section:nth-of-type(1) .btn-danger:has-text("Eliminar")');
        expect(confirmAppeared).toBe(true);
    });

    test('redirect to edit professor', async ({ page }) => {
        await page.click('.user-section:nth-of-type(1) .btn-warning:has-text("Editar")');
        await expect(page).toHaveURL('/Profesor1/editarUsuario');
    });

    test('redirect to watch competitions', async ({ page }) => {
        await page.click('.user-section:nth-of-type(1) .btn-info:has-text("Ver competiciones")');
        await expect(page).toHaveURL('/competiciones');
    });

    test('redirect to watch wordles', async ({ page }) => {
        await page.click('.user-section:nth-of-type(1) .btn-success:has-text("Ver wordles")');
        await expect(page).toHaveURL('/wordles');
    });

    test('redirect to edit student', async ({ page }) => {
        await page.click('.user-section:nth-of-type(2) .btn-warning:has-text("Editar")');
        await expect(page).toHaveURL('/Alumno1/editarUsuario');
    });

    test('delete student', async ({ page }) => {
        let confirmAppeared = false;

        page.on('dialog', async (dialog) => {
            if (dialog.type() === 'confirm') {
                confirmAppeared = true;
                await dialog.dismiss();
            }
        });

        await page.click('.user-section:nth-of-type(2) .btn-danger:has-text("Eliminar")');
        expect(confirmAppeared).toBe(true);
    });
});