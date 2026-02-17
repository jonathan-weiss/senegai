/* @tt{{{
    @slbc

    @template-renderer [ templateRendererClassName="EntityItemFormPartValidationServiceRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="UiEntityItemRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

    @template-model [
        modelClassName="UiEntityModel"
        modelPackageName="senegai.codegen.renderer.model.ui"
        modelName="entity"
    ]

    @template-model [
        modelClassName="UiItemModel"
        modelPackageName="senegai.codegen.renderer.model.ui"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="Author" replaceByExpression="model.itemName" ]
        [ searchValue="author" replaceByExpression="model.itemNameLowercase" ]
        [ searchValue="opus-magnum" replaceByExpression="entity.entityNameDashCase" ]
        [ searchValue="OpusMagnum" replaceByExpression="entity.entityName" ]
        [ searchValue="opusMagnum" replaceByExpression="entity.entityNameLowercase" ]

    @modify-provided-filename-by-replacements

    @slac

}}}@ */

import {Injectable} from '@angular/core';
import {ValidatorFn, Validators} from "@angular/forms";
import {AuthorFormPartFieldName} from "@app/opus-magnum/opus-magnum-form/author-form-part/author-form-part-field-name";
import {NamedValidator} from "@app/shared/form-controls/named-validator";
import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
/* @tt{{{ @slbc  @ignore-text @slac }}}@ */

/* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */


@Injectable({providedIn: 'root'})
export class AuthorFormPartValidationService {

    validatorFunctions(field: AuthorFormPartFieldName): Array<ValidatorFn> {
        return this.namedValidators(field).map(namedValidator => namedValidator.validatorFunction)
    }

    validatorNames(field: AuthorFormPartFieldName): Array<ValidatorTranslation> {
        return this.namedValidators(field)
            .map(namedValidator => this.toValidatorTranslation(namedValidator))
    }

    private toValidatorTranslation(namedValidator: NamedValidator): ValidatorTranslation {
        return {
            validatorName: namedValidator.validatorName,
            validatorTranslationKey: namedValidator.validatorTranslationKey,
        }
    }

    namedValidators(field: AuthorFormPartFieldName): ReadonlyArray<NamedValidator> {
        // TODO use mapped types https://www.typescriptlang.org/docs/handbook/2/mapped-types.html
        switch(field) {
            /* @tt{{{ @slbc
                @foreach [ iteratorExpression="model.attributes" loopVariable="attribute" ]

                @replace-value-by-expression
                    [ searchValue="firstname" replaceByExpression="attribute.attributeName" ]

            }}}@  */
            case AuthorFormPartFieldName.firstname: return [
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
            case AuthorFormPartFieldName.nickname: return [
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
            case AuthorFormPartFieldName.lastname: return [
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
