import {NamedValidator} from "@app/shared/form-controls/named-validator";
import {Validators} from "@angular/forms";


/**
 * t(validator.minlength)
 */
export function minimumLengthNamedValidator(minimalLength: number): NamedValidator {
    return {
        validatorName: "minlength",
        validatorFunction: Validators.minLength(minimalLength),
        validatorTranslationKey: "validator.minlength",
    }
}

/**
 * t(validator.maxlength)
 */
export function maximumLengthNamedValidator(maximalLength: number): NamedValidator {
    return {
        validatorName: "maxlength",
        validatorFunction: Validators.maxLength(maximalLength),
        validatorTranslationKey: "validator.maxlength",
    }
}

/**
 * t(validator.minNumber)
 */
export function minimumNumberNamedValidator(min: number): NamedValidator {
    return {
        validatorName: "min",
        validatorFunction: Validators.min(min),
        validatorTranslationKey: "validator.minNumber",
    }
}

/**
 * t(validator.maxNumber)
 */
export function maximumNumberNamedValidator(max: number): NamedValidator {
    return {
        validatorName: "max",
        validatorFunction: Validators.max(max),
        validatorTranslationKey: "validator.maxNumber",
    }
}
