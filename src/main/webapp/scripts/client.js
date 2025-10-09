import {createGraph, scale, drawPoint} from './canvas.js';
import {validateX, validateY, validateR} from './validator.js';

let config = {};
await fetch('./config.json')
  .then(response => response.json())
  .then(data => {
    config = data;
});

const errorMessage = document.getElementById("error-message");
const canvas = document.getElementById('graph-section');
const rButtons = document.querySelectorAll('input[name=r]')
const form = document.getElementById("input-form");

createGraph();

async function processRequest(xValues, y, r) {
    const params = new URLSearchParams();

    xValues.forEach(x => {
        params.append("x", x);
    });
    params.append("y", y);
    params.append("r", r);

    const response = await fetch(config.serverApi, {
        method: "POST",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
        },
        body: params
    });
    if(response.ok) {
        const html = await response.text();
        const parser = new DOMParser();
        const doc = parser.parseFromString(html, "text/html");

        const newTable = doc.getElementById("result-table");
        const oldTable = document.getElementById("result-table");

        if (newTable && oldTable) {
            oldTable.innerHTML = newTable.innerHTML;
        }

        const newErrorMessage = doc.getElementById("error-message");
        const oldErrorMessage = document.getElementById("error-message");
        if (newErrorMessage && oldErrorMessage) {
            oldErrorMessage.innerHTML = newErrorMessage.innerHTML;
            const hasError = newErrorMessage.innerHTML.trim() !== '';
            oldErrorMessage.hidden = !hasError;
        }
    }
}

window.onload = function() {
    if(document.querySelector('input[name="r"]:checked') != null) {
        document.getElementById("graph-section").style.cursor = "pointer";
    }
};

rButtons.forEach(rButton => rButton.addEventListener("change", function (e) {
    document.getElementById("graph-section").style.cursor = "pointer";
}));

canvas.addEventListener("click", async function (e) {
    if(document.querySelector('input[name="r"]:checked') != null) {
        const r = parseInt(document.querySelector('input[name="r"]:checked').value);
        validateR(r);
        const rect = canvas.getBoundingClientRect();
        const x = (event.clientX - (rect.right + rect.left) / 2) / scale * r;
        const y = ((rect.top + rect.bottom) / 2 - event.clientY) / scale * r;

        processRequest([x], y, r);
        drawPoint(x * scale / r, y * scale / r);
    } else {
        errorMessage.hidden = false;
        errorMessage.textContent = "Choose r";
    }
});

form.addEventListener("submit", async function (e) {
    e.preventDefault();
    errorMessage.hidden = true;
    errorMessage.textContent = "";

    const xCheckboxes = document.querySelectorAll('input[name="x"]:checked');
    if (xCheckboxes.length === 0) {
        errorMessage.textContent = "Select at least one X value";
        errorMessage.hidden = false;
        return;
    }

    const xValues = []
    xCheckboxes.forEach(checkbox => {
        const value = parseInt(checkbox.value);
        try {
            validateX(value);
            xValues.push(value);
        } catch (error) {
            errorMessage.textContent = error.message;
            errorMessage.hidden = false;
            return;
        }
    });

    const y = parseFloat(document.getElementById("enter-y").value);
    try {
        validateY(y);
    } catch (error) {
        errorMessage.textContent = error.message;
        errorMessage.hidden = false;
        return;
    }

    const r = parseInt(document.querySelector('input[name="r"]:checked').value);
    try {
        validateR(r);
    } catch (error) {
        errorMessage.textContent = error.message;
        errorMessage.hidden = false;
        return;
    }
    processRequest(xValues, y, r);
});
