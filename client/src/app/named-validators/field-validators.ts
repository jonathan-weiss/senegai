import {NamedValidator} from "@app/shared/form-controls/named-validator";
import {AbstractControl, ValidationErrors, Validators} from "@angular/forms";


/**
 * t(validator.minlength)
 */
export function minimumLengthNamedValidator(minimalLength: number): NamedValidator {
    return {
        validatorName: "minlength",
        validatorFunction: Validators.minLength(minimalLength),
        validatorTranslationKey: "validator.minlength",
        validatorTranslationParams: {minimalLength},
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
        validatorTranslationParams: {maximalLength},
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
        validatorTranslationParams: {min},
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
        validatorTranslationParams: {max},
    }
}

/**
 * t(validator.even)
 */
export function evenNumberNamedValidator(): NamedValidator {
    return {
        validatorName: "even",
        validatorFunction: (control: AbstractControl): ValidationErrors | null => {
            const value = control.value;
            // Leave the "is required" responsibility to the required validator.
            if (value === null || value === undefined || value === "") {
                return null;
            }
            return Number(value) % 2 === 0 ? null : {even: true};
        },
        validatorTranslationKey: "validator.even",
    }
}
