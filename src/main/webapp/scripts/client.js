import {createGraph, scale, drawPoint} from './canvas.js';
import {validateX, validateY, validateR} from './validator.js';

const errorMessage = document.getElementById("error-message");
const canvas = document.getElementById('graph-section');
const rButtons = document.querySelectorAll('input[name=r]')
const form = document.getElementById("input-form");

window.addEventListener('DOMContentLoaded', () => {
    createGraph();

    if (window.savedPoints && window.savedPoints.length > 0) {
        window.savedPoints.forEach(point => {
            const x = (point.x / point.r) * scale;
            const y = (point.y / point.r) * scale;
            drawPoint(x, y);
        });
    }
});

window.onload = function() {
    if(document.querySelector('input[name="r"]:checked') != null) {
        document.getElementById("graph-section").style.cursor = "pointer";
    }
};

rButtons.forEach(rButton => rButton.addEventListener("change", function (e) {
    document.getElementById("graph-section").style.cursor = "pointer";
}));

canvas.addEventListener("click", function (e) {
    if(document.querySelector('input[name="r"]:checked') != null) {
        const r = parseInt(document.querySelector('input[name="r"]:checked').value);
        validateR(r);
        const rect = canvas.getBoundingClientRect();
        const x = (event.clientX - (rect.right + rect.left) / 2) / scale * r;
        const y = ((rect.top + rect.bottom) / 2 - event.clientY) / scale * r;

        const xCheckboxes = form.querySelectorAll('input[name="x"]');
        xCheckboxes.forEach(checkbox => {
            checkbox.checked = false;
        });

        const targetXCheckbox = form.querySelector(`input[name="x"][value="${x}"]`);
        if (targetXCheckbox) {
            targetXCheckbox.checked = true;
        } else {
            const hiddenXInput = document.createElement('input');
            hiddenXInput.type = 'hidden';
            hiddenXInput.name = 'x';
            hiddenXInput.value = x.toString();
            form.appendChild(hiddenXInput);
        }
        const yInput = document.getElementById("enter-y");
        yInput.value = y.toString();

        const rInputs = form.querySelectorAll('input[name="r"]');
        rInputs.forEach(input => {
            input.checked = (input.value == r);
        });

        form.submit();
    } else {
        errorMessage.hidden = false;
        errorMessage.textContent = "Choose r";
    }
});

form.addEventListener("submit", async function (e) {
    errorMessage.hidden = true;
    errorMessage.textContent = "";

    const xCheckboxes = document.querySelectorAll('input[name="x"]:checked');
    if (xCheckboxes.length === 0) {
        errorMessage.textContent = "Select at least one X value";
        errorMessage.hidden = false;
        e.preventDefault();
        return;
    }

    xCheckboxes.forEach(checkbox => {
        const value = parseInt(checkbox.value);
        try {
            validateX(value);
        } catch (error) {
            errorMessage.textContent = error.message;
            errorMessage.hidden = false;
            e.preventDefault();
            return;
        }
    });

    const y = parseFloat(document.getElementById("enter-y").value);
    try {
        validateY(y);
    } catch (error) {
        errorMessage.textContent = error.message;
        errorMessage.hidden = false;
        e.preventDefault();
        return;
    }

    const r = parseInt(document.querySelector('input[name="r"]:checked').value);
    try {
        validateR(r);
    } catch (error) {
        errorMessage.textContent = error.message;
        errorMessage.hidden = false;
        e.preventDefault();
        return;
    }
});
