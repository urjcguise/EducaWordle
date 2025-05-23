import { test, expect } from '@playwright/test';

test.describe('General tests', () => {
    test.beforeEach(async ({ page }) => {
        await page.goto('/login');
        await page.fill('#email', 'alumno1@gmail.com');
        await page.fill('#password', 'alumno1');
        await page.click('#button-login');
        await expect(page).toHaveURL('/competiciones');
        await page.goto('/1/concurso');
        await expect(page).toHaveURL('/1/concurso');
    });

    test('contest name appears correctly', async ({ page }) => {
        await expect(page.locator('.contest-name')).toHaveText('Concurso1');
    });

    test('navigate to contest ranking', async ({ page }) => {
        await page.locator('.tab-item', { hasText: 'Ranking' }).click();
        await expect(page.locator('app-contest-ranking')).toBeVisible();
    });

    test('correct columns in "Ranking" table', async ({ page }) => {
        await page.locator('.tab-item', { hasText: 'Ranking' }).click();
        await expect(page.locator('app-contest-ranking')).toBeVisible();
        await expect(page.locator('th')).toHaveText([
            'Participante',
            'Nº de aciertos',
            'Intentos restantes',
            'Total de intentos',
            'Tiempo total'
        ]);
    });

    test('navigate to contest info', async ({ page }) => {
        await page.locator('.tab-item', { hasText: 'Información' }).click();
        await expect(page.locator('.info-content')).toBeVisible();
    });
});

test.describe('Professor tests', () => {
    test.beforeEach(async ({ page }) => {
        await page.goto('/login');
        await page.fill('#email', 'profesor1@gmail.com');
        await page.fill('#password', 'profesor1');
        await page.click('#button-login');
        await expect(page).toHaveURL('/competiciones');
        await page.goto('/1/concurso');
        await expect(page).toHaveURL('/1/concurso');
    });

    test('edit contest button appears', async ({ page }) => {
        await expect(page.locator('button', { hasText: 'Editar' })).toBeVisible();
        await page.locator('button', { hasText: 'Editar' }).click();
        await expect(page).toHaveURL('/1/editarConcurso');
    });

    test('edit contest tittle appears', async ({ page }) => {
        await expect(page.locator('button', { hasText: 'Editar' })).toBeVisible();
        await page.locator('button', { hasText: 'Editar' }).click();

        await expect(page.locator('h2.title')).toBeVisible();
        await expect(page.locator('h2.title')).toContainText('Editar');
    });

    test('contest name in contest name input during edit contest', async ({ page }) => {
        await expect(page.locator('button', { hasText: 'Editar' })).toBeVisible();
        await page.locator('button', { hasText: 'Editar' }).click();

        await expect(page.locator('input#contestName')).toHaveValue(/.+/);
    });

    test('correct number of tries in the selector', async ({ page }) => {
        await expect(page.locator('button', { hasText: 'Editar' })).toBeVisible();
        await page.locator('button', { hasText: 'Editar' }).click();

        expect(await page.locator('select#numTries > option').allTextContents()).toEqual(['2', '3', '4', '5', '6']);
    });

    test('start and end dates should be correct', async ({ page }) => {
        await expect(page.locator('button', { hasText: 'Editar' })).toBeVisible();
        await page.locator('button', { hasText: 'Editar' }).click();

        expect(await page.locator('input#startDateTime').inputValue()).toMatch(/^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}$/);
        expect(await page.locator('input#endDateTime').inputValue()).toMatch(/^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}$/);
    });

    test('change contest name during editing contest should be visible', async ({ page }) => {
        await expect(page.locator('button', { hasText: 'Editar' })).toBeVisible();
        await page.locator('button', { hasText: 'Editar' }).click();

        await page.locator('input#contestName').fill('New name');
        await expect(page.locator('input#contestName')).toHaveValue('New name');
    });

    test('change number of tries during editing contest should be visible', async ({ page }) => {
        await expect(page.locator('button', { hasText: 'Editar' })).toBeVisible();
        await page.locator('button', { hasText: 'Editar' }).click();

        await page.locator('select#numTries').selectOption('4');
        await expect(page.locator('select#numTries')).toHaveValue('4');
    });

    test('change start or end date during editing contest should be visible', async ({ page }) => {
        await expect(page.locator('button', { hasText: 'Editar' })).toBeVisible();
        await page.locator('button', { hasText: 'Editar' }).click();

        await page.locator('input#startDateTime').fill('2025-06-01T10:00');
        await page.locator('input#endDateTime').fill('2025-06-01T12:00');

        await expect(page.locator('input#startDateTime')).toHaveValue('2025-06-01T10:00');
        await expect(page.locator('input#endDateTime')).toHaveValue('2025-06-01T12:00');
    });

    test('pressing "Usar diccionario" option should show the next section', async ({ page }) => {
        await expect(page.locator('button', { hasText: 'Editar' })).toBeVisible();
        await page.locator('button', { hasText: 'Editar' }).click();

        await page.locator('input[type="checkbox"]').nth(0).check();
        await expect(page.locator('text=¿Desea importar un fichero de diccionario?')).toBeVisible();
    });

    test('pressing again "Usar diccionario" option should hide the next section', async ({ page }) => {
        await expect(page.locator('button', { hasText: 'Editar' })).toBeVisible();
        await page.locator('button', { hasText: 'Editar' }).click();

        await page.locator('input[type="checkbox"]').nth(1).check();
        await expect(page.locator('text=¿Desea importar un fichero de diccionario?')).toHaveCount(0);
    });

    test('pressing "Usar archivo" option should open file input', async ({ page }) => {
        await expect(page.locator('button', { hasText: 'Editar' })).toBeVisible();
        await page.locator('button', { hasText: 'Editar' }).click();

        await page.locator('input[type="checkbox"]').nth(0).check();
        await page.locator('input[type="checkbox"]').nth(2).check();

        await expect(page.locator('input[type="file"]')).toBeVisible();
    });

    test('copy contest button appears', async ({ page }) => {
        await expect(page.locator('button', { hasText: 'Copiar' })).toBeVisible();
    });

    test('navigate to wordle page', async ({ page }) => {
        await page.locator('.tab-item', { hasText: 'Wordles' }).click();
        await expect(page.locator('app-show-wordles')).toBeVisible();
    });

    test('show "No hay wordles todavía" text', async ({ page }) => {
        await page.goto('/3/concurso');
        await expect(page).toHaveURL('/3/concurso');

        await page.locator('.tab-item', { hasText: 'Wordles' }).click();
        await expect(page.locator('app-show-wordles')).toBeVisible();

        await expect(page.locator('.empty-message')).toHaveText('No hay wordles todavía.');
    });

    test('open modal after clicking in "Añadir" watching wordles', async ({ page }) => {
        await page.locator('.tab-item', { hasText: 'Wordles' }).click();
        await expect(page.locator('app-show-wordles')).toBeVisible();

        await page.locator('button:has-text("Añadir")').click();
        await expect(page.locator('.modal-content')).toBeVisible();
    });

    test('creating new wordle in modal', async ({ page }) => {
        await page.locator('.tab-item', { hasText: 'Wordles' }).click();
        await expect(page.locator('app-show-wordles')).toBeVisible();

        await page.locator('button:has-text("Añadir")').click();
        await page.locator('.modal-create-button-area button').click();
        await expect(page.locator('.create-new-input-area')).toBeVisible();
        await expect(page.locator('.create-button')).toBeDisabled();
    });

    test('enable save wordle button creating new one in modal', async ({ page }) => {
        await page.locator('.tab-item', { hasText: 'Wordles' }).click();
        await expect(page.locator('app-show-wordles')).toBeVisible();

        await page.locator('button:has-text("Añadir")').click();
        await page.locator('.modal-create-button-area button').click();
        await page.locator('.create-new-input-area input').fill('nuevo');
        await expect(page.locator('.create-button')).toBeEnabled();
    });

    test('activate random mode and show alert', async ({ page }) => {
        await page.locator('.tab-item', { hasText: 'Wordles' }).click();
        await expect(page.locator('app-show-wordles')).toBeVisible();

        page.on('dialog', dialog => dialog.accept());
        await page.locator('input[type="checkbox"]').first().check();
        await expect(page.locator('input[type="checkbox"]').first()).toBeChecked();
    });

    test('activate accent mode and show alert', async ({ page }) => {
        await page.locator('.tab-item', { hasText: 'Wordles' }).click();
        await expect(page.locator('app-show-wordles')).toBeVisible();

        page.on('dialog', dialog => dialog.accept());
        await page.locator('input[type="checkbox"]').nth(1).check();
        await expect(page.locator('input[type="checkbox"]').nth(1)).toBeChecked();
    });

    test('button "Modificar orden" is visible', async ({ page }) => {
        await page.locator('.tab-item', { hasText: 'Wordles' }).click();
        await expect(page.locator('app-show-wordles')).toBeVisible();

        await page.locator('button:has-text("Modificar orden")').isVisible();
    });

    test('"Editar" and "Borrar" buttons are visible', async ({ page }) => {
        await page.locator('.tab-item', { hasText: 'Wordles' }).click();
        await expect(page.locator('app-show-wordles')).toBeVisible();

        await page.locator('li').first().click();
        await page.locator('button:has-text("Editar")').isVisible();
        await page.locator('button:has-text("Borrar")').isVisible();
    });

    test('navigate to statistics page', async ({ page }) => {
        await page.locator('.tab-item', { hasText: 'Estadísticas' }).click();
        await expect(page.locator('app-contest-statistics')).toBeVisible();
    });

    test('correct log table columns names', async ({ page }) => {
        await page.locator('.tab-item', { hasText: 'Estadísticas' }).click();
        await expect(page.locator('app-contest-statistics')).toBeVisible();

        const logsTable = page.locator('.details-table.info-table:has(th.table-title:has-text("Logs"))').first();
        await expect(logsTable).toBeVisible();

        const logsTheadText = await logsTable.locator('thead tr:nth-child(2)').textContent();
        expect(logsTheadText).toContain('Alumno');
        expect(logsTheadText).toContain('Correo');
        expect(logsTheadText).toContain('Marca de tiempo');
        expect(logsTheadText).toContain('Palabra a adivinar');
        expect(logsTheadText).toContain('Posición palabra');
        expect(logsTheadText).toContain('Intento palabra');
        expect(logsTheadText).toContain('Intento');
        expect(logsTheadText).toContain('Estado');
    });

    test('text "No hay logs disponibles" shows', async ({ page }) => {
        await page.goto('/3/concurso');
        await expect(page).toHaveURL('/3/concurso');

        await page.locator('.tab-item', { hasText: 'Estadísticas' }).click();
        await expect(page.locator('app-contest-statistics')).toBeVisible();

        await expect(page.getByText('No hay logs disponibles')).toBeVisible();
    });

    test('text "No hay datos de resumen" shows', async ({ page }) => {
        await page.goto('/3/concurso');
        await expect(page).toHaveURL('/3/concurso');

        await page.locator('.tab-item', { hasText: 'Estadísticas' }).click();
        await expect(page.locator('app-contest-statistics')).toBeVisible();

        await expect(page.getByText('No hay datos de resumen')).toBeVisible();
    });

    test('delete contest button active', async ({ page }) => {
        await expect(page.locator('button.delete-button')).toBeVisible();
        let confirmAppeared = false;

        page.on('dialog', async (dialog) => {
            if (dialog.type() === 'confirm') {
                confirmAppeared = true;
                await dialog.dismiss();
            }
        });

        await page.locator('button.delete-button').click();
        expect(confirmAppeared).toBe(true);
    });

    test('navigate to play contest as professor', async ({ page }) => {
        await expect(page.locator('button', { hasText: /borrador/ })).toBeVisible();
        await page.locator('button', { hasText: /borrador/ }).click();
        await expect(page).toHaveURL('/wordle');
    });
});

test.describe('Students statistic tests', () => {
    test.beforeEach(async ({ page }) => {
        await page.goto('/login');
        await page.fill('#email', 'alumno1@gmail.com');
        await page.fill('#password', 'alumno1');
        await page.click('#button-login');
        await expect(page).toHaveURL('/competiciones');
        await page.goto('/2/concurso');
        await expect(page).toHaveURL('/2/concurso');
    });

    test('show not played info', async ({ page }) => {
        await page.locator('.tab-item', { hasText: 'Estadísticas' }).click();
        await expect(page.locator('app-contest-statistics')).toBeVisible();

        await expect(page.getByText('No hay información de intentos para esta palabra.').first()).toBeVisible();
    });

    test('correct wordle columns names', async ({ page }) => {
        await page.locator('.tab-item', { hasText: 'Estadísticas' }).click();
        await expect(page.locator('app-contest-statistics')).toBeVisible();

        await expect(page.locator('.details-table.info-table').first()).toBeVisible();
        const wordleDetailsTable = page.locator('.details-table.info-table:has(th.table-title:has-text("Palabra:"))').first();
        await expect(wordleDetailsTable).toBeVisible();

        const theadText = await wordleDetailsTable.locator('thead tr:nth-child(2)').textContent();
        expect(theadText).toContain('Intento');
        expect(theadText).toContain('Tiempo');
        expect(theadText).toContain('Palabra');
        expect(theadText).toContain('Correctas');
        expect(theadText).toContain('Mal colocadas');
        expect(theadText).toContain('Erróneas');
    });

    test('correct "Resumen" table columns names', async ({ page }) => {
        await page.locator('.tab-item', { hasText: 'Estadísticas' }).click();
        await expect(page.locator('app-contest-statistics')).toBeVisible();

        const summaryTable = page.locator('.summary-table.info-table').first();
        await expect(summaryTable).toBeVisible();

        const summaryTheadText = await summaryTable.locator('thead tr:nth-child(2)').textContent();
        expect(summaryTheadText).toContain('Palabra');
        expect(summaryTheadText).toContain('Aciertos');
        expect(summaryTheadText).toContain('Intentando');
        expect(summaryTheadText).toContain('Fallada');
        expect(summaryTheadText).toContain('Promedio de intentos');
        expect(summaryTheadText).toContain('Promedio de tiempo');
    });
});