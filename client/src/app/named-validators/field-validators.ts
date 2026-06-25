import {NamedValidator} from "@app/shared/form-controls/named-validator";
import {Validators} from "@angular/forms";


/**
 * t(validator.minlength)
 */
export function minimalLengthNamedValidator(minimalLength: number): NamedValidator {
    return {
        validatorName: "minlength",
        validatorFunction: Validators.minLength(minimalLength),
        validatorTranslationKey: "validator.minlength",
    }
}
