const xOptions = new Set([-5, -4, -3, -2, -1, 0, 1, 2, 3]);
const yMin = -3;
const yMax = 3;
const rOptions = new Set([1, 2, 3, 4, 5]);

function validateX(x) {
    if(isNaN(x) || !xOptions.has(x)){
        throw new Error(`x must be in [${[...xOptions].join(", ")}]`);
    }
}

function validateY(y) {
    y = roundY(y);
    if(isNaN(y) || y >= yMax || y <= yMin) {
        throw new Error(`y must be between ${yMin} and ${yMax}`);
    }
}

function validateR(r) {
    if(isNaN(r) || !rOptions.has(r)) {
        throw new Error(`r must be in [${[...rOptions].join(", ")}]`);
    }
}

function roundY(yStr) {
    const isNumberRegex = /^-?\d+([\.,]\d+)?$/
    const checkRegex = /^-?\d+([\.,]\d{0,3})?$/;
    if (!isNumberRegex.test(yStr)) {
         throw new Error("Y must be a number");
    }
    if (!checkRegex.test(yStr)) {
         const roundRegexp = /^(-?\d+([\.,]\d{0,3}))\d+$/;
         yStr = yStr.replace(roundRegexp, '$1').replace(",", ".");
    }
    return yStr;
}

export {validateX, validateY, validateR};