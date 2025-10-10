const xOptions = new Set([-5, -4, -3, -2, -1, 0, 1, 2, 3]);
const yMin = -3;
const yMax = 3;
const rOptions = new Set([1, 2, 3, 4, 5]);

function validateX(x) {
    if(isNaN(x) || !xOptions.has(x)){
        throw new Error(`X must be in [${[...xOptions].join(", ")}]`);
    }
}

function validateY(y) {
    if(isNaN(y) || y >= yMax || y <= yMin) {
        throw new Error(`Y must be between ${yMin} and ${yMax}`);
    }
}

function validateR(r) {
    if(isNaN(r) || !rOptions.has(r)) {
        throw new Error(`R must be in [${[...rOptions].join(", ")}]`);
    }
}

export {validateX, validateY, validateR};