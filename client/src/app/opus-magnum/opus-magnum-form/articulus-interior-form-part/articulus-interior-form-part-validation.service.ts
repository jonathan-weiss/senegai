import {Injectable} from '@angular/core';
import {ValidatorFn, Validators} from "@angular/forms";
import {NamedValidator} from "@app/shared/form-controls/named-validator";
import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
import {
    ArticulusInteriorFormPartFieldName
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-form-part/articulus-interior-form-part-field-name";

@Injectable({providedIn: 'root'})
export class ArticulusInteriorFormPartValidationService {

    validatorFunctions(field: ArticulusInteriorFormPartFieldName): Array<ValidatorFn> {
        return this.namedValidators(field).map(namedValidator => namedValidator.validatorFunction)
    }

    validatorNames(field: ArticulusInteriorFormPartFieldName): Array<ValidatorTranslation> {
        return this.namedValidators(field)
            .map(namedValidator => this.toValidatorTranslation(namedValidator))
    }

    private toValidatorTranslation(namedValidator: NamedValidator): ValidatorTranslation {
        return {
            validatorName: namedValidator.validatorName,
            validatorTranslationKey: namedValidator.validatorTranslationKey,
        }
    }

    namedValidators(field: ArticulusInteriorFormPartFieldName): ReadonlyArray<NamedValidator> {
        switch (field) {
            default:
                return []
        }
    };
} 
