document.addEventListener('DOMContentLoaded', () => {
    const rows = document.querySelectorAll('.row');
    const inputBoxes = document.querySelectorAll('.input-box');
    let currentBox = inputBoxes[0];

    // Enfocar el primer cuadro de entrada al cargar la página
    if (inputBoxes.length > 0) {
        inputBoxes[0].focus();
    }

    inputBoxes.forEach((box, index) => {
        const rowIndex = Math.floor(index / 5);
        const boxIndex = index % 5;

        box.addEventListener('input', () => {
            if (box.value.length === box.maxLength) {
                if (boxIndex < 4) {
                    inputBoxes[index + 1].focus();
                    currentBox = inputBoxes[index + 1];
                } else if (rowIndex < 4) {
                    const nextRow = rows[rowIndex + 1];
                    const nextRowFirstBox = nextRow.querySelector('.input-box');
                    nextRowFirstBox.removeAttribute('disabled');
                    nextRowFirstBox.focus();
                    currentBox = nextRowFirstBox;
                }
            }
        });

        box.addEventListener('keydown', (event) => {
            if (event.key === 'Backspace' && box.value === '') {
                if (boxIndex > 0) {
                    const prevBox = inputBoxes[index - 1];
                    prevBox.focus();
                    prevBox.value = '';
                    currentBox = prevBox;
                } else if (rowIndex > 0) {
                    const prevRow = rows[rowIndex - 1];
                    const prevRowLastBox = prevRow.querySelectorAll('.input-box')[4];
                    prevRowLastBox.focus();
                    prevRowLastBox.value = '';
                    prevRowLastBox.removeAttribute('disabled');
                    currentBox = prevRowLastBox;
                }
            }

            if (event.key === 'Enter' && boxIndex === 4 && rowIndex < 4) {
                const nextRow = rows[rowIndex + 1];
                const nextRowFirstBox = nextRow.querySelector('.input-box');
                nextRowFirstBox.removeAttribute('disabled');
                nextRowFirstBox.focus();
                currentBox = nextRowFirstBox;
            }
        });
    });

    // Manejador para las teclas del teclado virtual
    document.querySelectorAll('.key').forEach(key => {
        key.addEventListener('click', () => {
            const keyContent = key.textContent;

            if (keyContent === 'ENVIAR') {
                // Implementar funcionalidad de enviar si es necesario
            } else if (keyContent === '⌫') {
                if (currentBox.value === '') {
                    const prevBox = getPreviousBox(currentBox);
                    if (prevBox) {
                        prevBox.value = '';
                        prevBox.focus();
                        currentBox = prevBox;
                    }
                } else {
                    currentBox.value = '';
                }
            } else {
                currentBox.value = keyContent;
                const nextBox = getNextBox(currentBox);
                if (nextBox) {
                    nextBox.focus();
                    currentBox = nextBox;
                }
            }
        });
    });

    function getNextBox(box) {
        const index = Array.from(inputBoxes).indexOf(box);
        if (index !== -1 && index < inputBoxes.length - 1) {
            return inputBoxes[index + 1];
        }
        return null;
    }

    function getPreviousBox(box) {
        const index = Array.from(inputBoxes).indexOf(box);
        if (index > 0) {
            return inputBoxes[index - 1];
        }
        return null;
    }
});
