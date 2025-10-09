const canvas = document.getElementById('graph');
const ctx = canvas.getContext('2d');

const width = canvas.width;
const height = canvas.height;
const centerX = width / 2;
const centerY = height / 2;
const scale = 220;

function createGraph() {
    ctx.clearRect(0, 0, width, height);

    ctx.beginPath();
    ctx.moveTo(centerX, centerY);
    ctx.lineTo(centerX - scale / 2, centerY);
    ctx.lineTo(centerX, centerY + scale)
    ctx.closePath();

    ctx.fillStyle = 'rgba(51, 153, 255)';
    ctx.strokeStyle = '#3399ff';
    ctx.lineWidth = 2;
    ctx.fill();
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(centerX, centerY);
    ctx.lineTo(centerX, centerY + scale / 2);
    ctx.lineTo(centerX + scale, centerY + scale / 2);
    ctx.lineTo(centerX + scale, centerY);
    ctx.closePath();
    ctx.fill();
    ctx.stroke();

    ctx.beginPath();
    ctx.arc(centerX, centerY, scale / 2, Math.PI, Math.PI * 1.5, false);
    ctx.lineTo(centerX, centerY);
    ctx.closePath();
    ctx.fill();
    ctx.stroke();

    ctx.fillStyle = '#000';
    ctx.strokeStyle = '#000';
    ctx.lineWidth = 1;

    ctx.beginPath();
    ctx.moveTo(0, height / 2);
    ctx.lineTo(width, height / 2);
    ctx.moveTo(width / 2, 0);
    ctx.lineTo(width / 2, height);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(width - 15, centerY - 5);
    ctx.lineTo(width, centerY);
    ctx.lineTo(width - 15, centerY + 5);
    ctx.fill();

    ctx.beginPath();
    ctx.moveTo(centerX - 5, 15);
    ctx.lineTo(centerX, 0);
    ctx.lineTo(centerX + 5, 15);
    ctx.fill();

    ctx.font = "15px";

    ctx.strokeText("x", centerX + scale + 20, centerY + 15);
    ctx.strokeText("y", centerX - 15, centerY - scale - 20);

    ctx.strokeText("0", centerX + 10, centerY - 10);
    ctx.strokeText("R/2", centerX + scale / 2, centerY - 5);
    ctx.strokeText("R", centerX + scale, centerY - 5);

    ctx.strokeText("-R/2", centerX - scale / 2, centerY - 5);
    ctx.strokeText("-R", centerX - scale, centerY - 5);

    ctx.strokeText("R/2", centerX + 5, centerY - scale / 2);
    ctx.strokeText("R", centerX + 5, centerY - scale);

    ctx.strokeText("-R/2", centerX + 5, centerY + scale / 2);
    ctx.strokeText("-R", centerX + 5, centerY + scale);
}

function drawPoint(x, y) {
    ctx.beginPath();
    ctx.arc(centerX + x, centerY - y, 3, 0, 2 * Math.PI);
    ctx.fill();
}

export {createGraph, scale, drawPoint};