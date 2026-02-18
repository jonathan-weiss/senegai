import {Injectable} from '@angular/core';
import {ValidatorFn, Validators} from "@angular/forms";
import {
    OpusMagnumFormPartFieldName
} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part-field-name";
import {NamedValidator} from "@app/shared/form-controls/named-validator";
import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";

@Injectable({providedIn: 'root'})
export class OpusMagnumFormPartValidationService {

    validatorFunctions(field: OpusMagnumFormPartFieldName): Array<ValidatorFn> {
        return this.namedValidators(field).map(namedValidator => namedValidator.validatorFunction)
    }

    validatorNames(field: OpusMagnumFormPartFieldName): Array<ValidatorTranslation> {
        return this.namedValidators(field)
            .map(namedValidator => this.toValidatorTranslation(namedValidator))
    }

    private toValidatorTranslation(namedValidator: NamedValidator): ValidatorTranslation {
        return {
            validatorName: namedValidator.validatorName,
            validatorTranslationKey: namedValidator.validatorTranslationKey,
        }
    }

    namedValidators(field: OpusMagnumFormPartFieldName): ReadonlyArray<NamedValidator> {
        switch(field) {
            case OpusMagnumFormPartFieldName.id: return [
                {
                    validatorName: "required",
                    validatorFunction: Validators.required,
                    validatorTranslationKey: "validator.required",
                },
            ]
            case OpusMagnumFormPartFieldName.title: return [
                {
                    validatorName: "required",
                    validatorFunction: Validators.required,
                    validatorTranslationKey: "validator.required",
                },
            ]
            default: return []
        }
    };
} 
