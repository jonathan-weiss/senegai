/* @tt{{{
    #expand-comment [ expandDirection="backward" strip="linebreak"]

    #move-comment [ direction="backward" ]
    @template-renderer [
        templateRendererClassName="EntityItemFormPartValidationServiceRenderer"
        templateRendererPackageName="senegai.codegen.renderer.angular"
        templateRendererInterfaceName="UiEntityItemRenderer"
        templateRendererInterfacePackageName="senegai.codegen.renderer.angular"
    ] [
        modelClassName="UiEntityFormViewItemModel"
        modelPackageName="senegai.codegen.renderer.model.ui.entityform"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="Author" replaceByExpression="model.item.itemName.pascalCase" ]
        [ searchValue="author" replaceByExpression="model.item.itemName.camelCase" ]
        [ searchValue="opus-magnum" replaceByExpression="model.entity.entityName.kebabCase" ]
        [ searchValue="OpusMagnum" replaceByExpression="model.entity.entityName.pascalCase" ]
        [ searchValue="opusMagnum" replaceByExpression="model.entity.entityName.camelCase" ]

    @modify-provided-filename-by-replacements

    #expand-comment [ expandDirection="forward" strip="linebreak"]

}}}@ */

import {Injectable} from '@angular/core';
import {ValidatorFn, Validators} from "@angular/forms";
import {AuthorFormPartFieldName} from "@app/opus-magnum/opus-magnum-form/author-form-part/author-form-part-field-name";
import {NamedValidator} from "@app/shared/form-controls/named-validator";
import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
/* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @ignore-text #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */

/* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @end-ignore-text #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */


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
        switch(field) {
            /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]
                @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

                @replace-value-by-expression
                    [ searchValue="firstname" replaceByExpression="attribute.attributeName.camelCase" ]

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
            /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"] @end-foreach #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */
            /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @ignore-text #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */
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
            /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @end-ignore-text #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */
            default: return []
        }
    };
} 
