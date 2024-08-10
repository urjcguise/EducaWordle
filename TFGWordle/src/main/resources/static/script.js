const word = "Agile"
document.addEventListener('DOMContentLoaded', () => {
    const tries = document.querySelectorAll('.row');
    const inputLetters = document.querySelectorAll('.input-box');
    let currentLetter = inputLetters[0];


    // Enfocar el primer cuadro de entrada al cargar la página
    if (inputLetters.length > 0) {
        inputLetters[0].focus();
    }

    inputLetters.forEach((letter, index) => {
        const tryIndex = Math.floor(index / 5);
        const letterIndex = index % 5;

        letter.addEventListener('input', () => {
            if (letter.value.length === letter.maxLength) {
                if (letterIndex < 4) {
                    inputLetters[index + 1].focus();
                    currentLetter = inputLetters[index + 1];
                } /*else {
                    inputLetters[letterIndex].blur();
                }
                */

            }
        });

        letter.addEventListener('keydown', (event) => {
            if (event.key === 'Backspace' && letter.value === '') {
                if (letterIndex > 0) {
                    const prevBox = inputLetters[index - 1];
                    prevBox.focus();
                    prevBox.value = '';
                    currentLetter = prevBox;
                } else if (tryIndex > 0) {
                    const prevRow = tries[tryIndex - 1];
                    const prevRowLastBox = prevRow.querySelectorAll('.input-box')[4];
                    prevRowLastBox.focus();
                    prevRowLastBox.value = '';
                    prevRowLastBox.removeAttribute('disabled');
                    currentLetter = prevRowLastBox;
                }
            }

            if (event.key === 'Enter' && letterIndex === 4 && tryIndex < 4) {
                const nextRow = tries[tryIndex + 1];
                const nextRowInputBoxes = nextRow.querySelectorAll('.input-box');

                checkWord(tryIndex);

                nextRowInputBoxes.forEach(box => {
                    box.removeAttribute('disabled');
                });

                nextRowInputBoxes[0].focus();
                currentLetter = nextRowInputBoxes[0];
            }
        });
    });

    // Manejador para las teclas del teclado virtual
    document.querySelectorAll('.key').forEach(key => {
        key.addEventListener('click', () => {
            const keyContent = key.textContent;

            if (keyContent === 'ENVIAR') {
                const currentIndex = Array.from(inputLetters).indexOf(currentLetter);
                tryIndex = Math.floor(currentIndex / 5);
                letterIndex = currentIndex % 5;

                if (letterIndex === 4 && tryIndex < 4) {
                    const nextRow = tries[tryIndex + 1];
                    const nextRowInputBoxes = nextRow.querySelectorAll('.input-box');

                    checkWord(tryIndex);

                    nextRowInputBoxes.forEach(box => {
                        box.removeAttribute('disabled');
                    });

                    nextRowInputBoxes[0].focus();
                    currentLetter = nextRowInputBoxes[0];
                }
            } else if (keyContent === '⌫') {
                if (currentLetter.value === '') {
                    const prevBox = getPreviousBox(currentLetter);
                    if (prevBox) {
                        prevBox.value = '';
                        prevBox.focus();
                        currentLetter = prevBox;
                    }
                } else {
                    currentLetter.value = '';
                }
            } else {
                currentLetter.value = keyContent;
                const nextBox = getNextBox(currentLetter);
                if (nextBox) {
                    nextBox.focus();
                    currentLetter = nextBox;
                }
            }
        });
    });

    function getNextBox(box) {
        const index = Array.from(inputLetters).indexOf(box);
        if (index !== -1 && index < inputLetters.length - 1) {
            return inputLetters[index + 1];
        }
        return null;
    }

    function getPreviousBox(box) {
        const index = Array.from(inputLetters).indexOf(box);
        if (index > 0) {
            return inputLetters[index - 1];
        }
        return null;
    }
});

function checkWord(row) {
    // Convertimos la palabra en un array de letras
    const wordArray = word.toUpperCase().split('');
    // Creamos una copia del array para rastrear las coincidencias amarillas
    const wordArrayCopy = [...wordArray];
    // Variable para almacenar las letras ingresadas por el usuario
    let userInput = [];

    // Obtenemos los valores de los inputs y los almacenamos en userInput
    for (let i = 1; i <= 5; i++) {
        userInput.push(document.getElementById(`box${row + 1}-${i}`).value.toUpperCase());
    }

    // Primero, coloreamos las celdas verdes para las coincidencias exactas
    for (let i = 0; i < 5; i++) {
        if (userInput[i] === wordArray[i]) {
            document.getElementById(`box${row + 1}-${i + 1}`).style.backgroundColor = "green";
            // Marcamos la letra como usada en wordArrayCopy
            wordArrayCopy[i] = null;
            // Marcamos la letra como usada en userInput
            userInput[i] = null;
        }
    }

    // Luego, coloreamos las celdas amarillas y grises para las coincidencias parciales y faltantes
    for (let i = 0; i < 5; i++) {
        if (userInput[i] !== null) {
            // Si la letra existe en wordArrayCopy pero en una posición diferente
            if (wordArrayCopy.includes(userInput[i])) {
                document.getElementById(`box${row + 1}-${i + 1}`).style.backgroundColor = "yellow";
                // Eliminamos la primera ocurrencia de esa letra en wordArrayCopy
                wordArrayCopy[wordArrayCopy.indexOf(userInput[i])] = null;
            } else {
                // Si la letra no está en wordArray
                document.getElementById(`box${row + 1}-${i + 1}`).style.backgroundColor = "grey";
            }
        }
    }
}
