import {Injectable} from '@angular/core';
import {ValidatorFn, Validators} from "@angular/forms";
import {NamedValidator} from "@app/shared/form-controls/named-validator";
import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
import {
    LibraryAwardFormPartFieldName
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part-field-name";

@Injectable({providedIn: 'root'})
export class LibraryAwardFormPartValidationService {

    validatorFunctions(field: LibraryAwardFormPartFieldName): Array<ValidatorFn> {
        return this.namedValidators(field).map(namedValidator => namedValidator.validatorFunction)
    }

    validatorNames(field: LibraryAwardFormPartFieldName): Array<ValidatorTranslation> {
        return this.namedValidators(field)
            .map(namedValidator => this.toValidatorTranslation(namedValidator))
    }

    private toValidatorTranslation(namedValidator: NamedValidator): ValidatorTranslation {
        return {
            validatorName: namedValidator.validatorName,
            validatorTranslationKey: namedValidator.validatorTranslationKey,
        }
    }

    namedValidators(field: LibraryAwardFormPartFieldName): ReadonlyArray<NamedValidator> {
        switch (field) {
            default:
                return []
        }
    };
} 
