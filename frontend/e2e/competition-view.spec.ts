import { test, expect } from '@playwright/test';

test.describe('General tests', () => {
    test.beforeEach(async ({ page }) => {
        await page.goto('/login');
        await page.fill('#email', 'profesor1@gmail.com');
        await page.fill('#password', 'profesor1');
        await page.click('#button-login');
        await expect(page).toHaveURL('/competiciones');
        await page.goto('/competicion/Competicion1');
        await expect(page).toHaveURL('/competicion/Competicion1');
    });

    test('competition name appears correctly', async ({ page }) => {
        await expect(page.locator('.competition-title')).toHaveText('Competicion1');
    });

    test('navigate to "Ranking"', async ({ page }) => {
        await page.locator('.tab-item', { hasText: 'Ranking' }).click();
        await expect(page.locator('app-competition-ranking')).toBeVisible();
    });

    test('navigate to "Concursos"', async ({ page }) => {
        await page.locator('.tab-item', { hasText: 'Concursos' }).click();
        await expect(page.locator('.contests-content')).toBeVisible();
    });

    test('correct columns in "Ranking" table', async ({ page }) => {
        await page.locator('.tab-item', { hasText: 'Ranking' }).click();
        await expect(page.locator('app-competition-ranking')).toBeVisible();
        await expect(page.locator('th')).toHaveText([
            'Participante',
            'Nº de aciertos',
            'Total de intentos',
            'Tiempo total'
        ]);
    });
});

test.describe('Tests for professors', () => {
    test.beforeEach(async ({ page }) => {
        await page.goto('/login');
        await page.fill('#email', 'profesor1@gmail.com');
        await page.fill('#password', 'profesor1');
        await page.click('#button-login');
        await expect(page).toHaveURL('/competiciones');
        await page.goto('/competicion/Competicion1');
    });

    test('press change competition name', async ({ page }) => {
        await page.locator('.edit-action').click();
        await expect(page.locator('input.edit-input')).toBeVisible();
    });

    test('cancel change competition name', async ({ page }) => {
        await page.locator('.edit-action').click();
        await expect(page.locator('input.edit-input')).toBeVisible();
        await page.locator('.cancel-action').click();
        await expect(page.locator('input.edit-input')).toBeHidden();
    });

    test('navigate to "Alumnos"', async ({ page }) => {
        await page.locator('.tab-item', { hasText: 'Alumnos' }).click();
        await expect(page.locator('app-student-list')).toBeVisible();
    });

    test('open modal to create new student in "Alumnos"', async ({ page }) => {
        await page.locator('.tab-item', { hasText: 'Alumnos' }).click();
        await expect(page.locator('app-student-list')).toBeVisible();

        await page.click('button:has-text("Añadir Alumno")');
        await expect(page.locator('.modal')).toBeVisible();
        await expect(page.locator('app-new-student')).toBeVisible();
    });

    test('redirect to "Varios alumnos" when creating new student in "Alumnos"', async ({ page }) => {
        await page.locator('.tab-item', { hasText: 'Alumnos' }).click();
        await expect(page.locator('app-student-list')).toBeVisible();

        await page.click('button:has-text("Añadir Alumno")');
        await expect(page.locator('.modal')).toBeVisible();
        await expect(page.locator('app-new-student')).toBeVisible();

        await page.click('li.tab-item:has-text("Varios alumnos")');
        await expect(page.locator('.upload-form')).toBeVisible();
    });

    test('redirect to "Un alumno" when creating new student in "Alumnos"', async ({ page }) => {
        await page.locator('.tab-item', { hasText: 'Alumnos' }).click();
        await expect(page.locator('app-student-list')).toBeVisible();

        await page.click('button:has-text("Añadir Alumno")');
        await expect(page.locator('.modal')).toBeVisible();
        await expect(page.locator('app-new-student')).toBeVisible();

        await page.click('li.tab-item:has-text("Un alumno")');
        await expect(page.locator('form')).toContainText('Correo Electrónico');
    });

    test('create correct student in new student in "Alumnos"', async ({ page }) => {
        await page.locator('.tab-item', { hasText: 'Alumnos' }).click();
        await expect(page.locator('app-student-list')).toBeVisible();

        await page.click('button:has-text("Añadir Alumno")');
        await expect(page.locator('.modal')).toBeVisible();
        await expect(page.locator('app-new-student')).toBeVisible();

        await page.fill('#userName', 'New User');
        await page.fill('#email', 'newuser@example.com');
        await page.fill('#password', 'pass1234');

        const submitButton = page.locator('button[type="submit"]');
        await expect(submitButton).toBeEnabled();
    });

    test('empty name in new student in "Alumnos"', async ({ page }) => {
        await page.locator('.tab-item', { hasText: 'Alumnos' }).click();
        await expect(page.locator('app-student-list')).toBeVisible();

        await page.click('button:has-text("Añadir Alumno")');
        await expect(page.locator('.modal')).toBeVisible();
        await expect(page.locator('app-new-student')).toBeVisible();

        await page.click('#userName');
        await page.locator('#userName').blur();

        await expect(page.locator('.invalid-feedback')).toContainText('El nombre es obligatorio');
    });

    test('wrong email format in new student in "Alumnos"', async ({ page }) => {
        await page.locator('.tab-item', { hasText: 'Alumnos' }).click();
        await expect(page.locator('app-student-list')).toBeVisible();

        await page.click('button:has-text("Añadir Alumno")');
        await expect(page.locator('.modal')).toBeVisible();
        await expect(page.locator('app-new-student')).toBeVisible();

        await page.fill('#email', 'no-valid-email');
        await page.locator('#email').blur();

        await expect(page.locator('.invalid-feedback')).toContainText('Introduce un formato de correo válido');
    });

    test('short password in new student in "Alumnos"', async ({ page }) => {
        await page.locator('.tab-item', { hasText: 'Alumnos' }).click();
        await expect(page.locator('app-student-list')).toBeVisible();

        await page.click('button:has-text("Añadir Alumno")');
        await expect(page.locator('.modal')).toBeVisible();
        await expect(page.locator('app-new-student')).toBeVisible();

        await page.fill('#password', 'pass');
        await page.locator('#password').blur();

        await expect(page.locator('.invalid-feedback')).toContainText('La contraseña debe tener al menos 6 caracteres');
    });

    test('upload excel file in "Varios alumnos" when creating new student in "Alumnos"', async ({ page }) => {
        await page.locator('.tab-item', { hasText: 'Alumnos' }).click();
        await expect(page.locator('app-student-list')).toBeVisible();

        await page.click('button:has-text("Añadir Alumno")');
        await expect(page.locator('.modal')).toBeVisible();
        await expect(page.locator('app-new-student')).toBeVisible();

        await page.click('li.tab-item:has-text("Varios alumnos")');
        await expect(page.locator('.upload-form')).toBeVisible();

        const fileInput = page.locator('input[type="file"]');
        await fileInput.setInputFiles({
            name: 'fake_students.xlsx',
            mimeType: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
            buffer: Buffer.from('')
        });

        const uploadButton = page.locator('button[type="submit"]');
        await expect(uploadButton).toBeEnabled();
    });

    test('open modal to create new contest', async ({ page }) => {
        await page.locator('button.new-contest-button').click();
        await expect(page.locator('app-contest')).toBeVisible();
    });

    test('close modal to create new contest', async ({ page }) => {
        await page.locator('button.new-contest-button').click();
        await expect(page.locator('app-contest')).toBeVisible();
        await page.click('.competition-name', { force: true });
        await expect(page.locator('app-contest')).toBeHidden();
    });

    test('delete competition', async ({ page }) => {
        page.on('dialog', async dialog => {
            expect(dialog.message()).toContain('¿Está seguro de que desea eliminar esta competición?')
            await dialog.accept();
        })

        await page.locator('button.delete-competition').click();
        await page.goto('/competiciones');
        await expect(page).toHaveURL('/competiciones');
    });
});