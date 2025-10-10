function round(param) {
    const isNumberRegex = /^-?\d+([\.,]\d+)?$/
    const checkRegex = /^-?\d+([\.,]\d{0,3})?$/;
    if (!isNumberRegex.test(param)) {
         throw new Error("Argument must be a number");
    }
    if (!checkRegex.test(param)) {
         const roundRegexp = /^(-?\d+([\.,]\d{0,3}))\d+$/;
         param = param.replace(roundRegexp, '$1').replace(",", ".");
    }
    return param;
}

export {round}