/* @tt{{{
    @slbc

    @template-renderer [ templateRendererClassName="ItemFormValidationServiceRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="ItemRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

    @template-model [
    modelClassName="ItemModel"
    modelPackageName="senegai.codegen.renderer.model"
    modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="Author" replaceByExpression="model.itemName" ]
        [ searchValue="author" replaceByExpression="model.itemNameLowercase" ]

    @modify-provided-filename-by-replacements

    @slac

}}}@ */

import {Injectable} from '@angular/core';
import {ValidatorFn, Validators} from "@angular/forms";
import {AuthorFormFieldName} from "@app/author/author-form/author-form-field-name";
import {NamedValidator} from "@app/shared/form-controls/named-validator";
import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
/* @tt{{{ @slbc  @ignore-text @slac }}}@ */

/* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */


@Injectable({providedIn: 'root'})
export class AuthorFormValidationService {

    validatorFunctions(field: AuthorFormFieldName): Array<ValidatorFn> {
        return this.namedValidators(field).map(namedValidator => namedValidator.validatorFunction)
    }

    validatorNames(field: AuthorFormFieldName): Array<ValidatorTranslation> {
        return this.namedValidators(field)
            .map(namedValidator => this.toValidatorTranslation(namedValidator))
    }

    private toValidatorTranslation(namedValidator: ValidatorTranslation): ValidatorTranslation {
        return {
            validatorName: namedValidator.validatorName,
            validatorTranslationKey: namedValidator.validatorTranslationKey,
        }
    }

    namedValidators(field: AuthorFormFieldName): ReadonlyArray<NamedValidator> {
        // TODO use mapped types https://www.typescriptlang.org/docs/handbook/2/mapped-types.html
        switch(field) {
            /* @tt{{{ @slbc
                @foreach [ iteratorExpression="model.attributes" loopVariable="attribute" ]

                @replace-value-by-expression
                    [ searchValue="firstname" replaceByExpression="attribute.attributeName" ]

            }}}@  */
            case AuthorFormFieldName.firstname: return [
                {
                    validatorName: "required",
                    validatorFunction: Validators.required,
                    validatorTranslationKey: "validator.required",
                },
                {
                    validatorName: "minlength",
                    validatorFunction: Validators.minLength(2),
                    validatorTranslationKey: "validator.minlength",
                },
            ]
            /* @tt{{{ @slbc @end-foreach @slac }}}@ */
            /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
            case AuthorFormFieldName.nickname: return [
                {
                    validatorName: "required",
                    validatorFunction: Validators.required,
                    validatorTranslationKey: "validator.required",
                },
                {
                    validatorName: "minlength",
                    validatorFunction: Validators.minLength(3),
                    validatorTranslationKey: "validator.minlength",
                },
            ]
            case AuthorFormFieldName.lastname: return [
                {
                    validatorName: "required",
                    validatorFunction: Validators.required,
                    validatorTranslationKey: "validator.required",
                },
                {
                    validatorName: "maxlength",
                    validatorFunction: Validators.maxLength(45),
                    validatorTranslationKey: "validator.maxlength",

                },
            ]
            /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
            default: return []
        }
    };
} 
