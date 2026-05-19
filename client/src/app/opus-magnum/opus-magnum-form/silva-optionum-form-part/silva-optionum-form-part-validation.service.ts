/* @tt{{{
    @remove-blanks-and-linebreak-before-comment

    @move-comment-backward
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
        [ searchValue="silva-optionum" replaceByExpression="model.item.itemName.kebabCase" ]
        [ searchValue="SilvaOptionum" replaceByExpression="model.item.itemName.pascalCase" ]
        [ searchValue="silvaOptionum" replaceByExpression="model.item.itemName.camelCase" ]
        [ searchValue="opus-magnum" replaceByExpression="model.entity.entityName.kebabCase" ]
        [ searchValue="OpusMagnum" replaceByExpression="model.entity.entityName.pascalCase" ]
        [ searchValue="opusMagnum" replaceByExpression="model.entity.entityName.camelCase" ]

    @modify-provided-filename-by-replacements

    @remove-blanks-and-linebreak-after-comment

}}}@ */

import {Injectable} from '@angular/core';
import {ValidatorFn, Validators} from "@angular/forms";
import {SilvaOptionumFormPartFieldName} from "@app/opus-magnum/opus-magnum-form/silva-optionum-form-part/silva-optionum-form-part-field-name";
import {NamedValidator} from "@app/shared/form-controls/named-validator";
import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
/* @tt{{{ @remove-blanks-and-linebreak-before-comment  @ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */

/* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */


@Injectable({providedIn: 'root'})
export class SilvaOptionumFormPartValidationService {

    validatorFunctions(field: SilvaOptionumFormPartFieldName): Array<ValidatorFn> {
        return this.namedValidators(field).map(namedValidator => namedValidator.validatorFunction)
    }

    validatorNames(field: SilvaOptionumFormPartFieldName): Array<ValidatorTranslation> {
        return this.namedValidators(field)
            .map(namedValidator => this.toValidatorTranslation(namedValidator))
    }

    private toValidatorTranslation(namedValidator: NamedValidator): ValidatorTranslation {
        return {
            validatorName: namedValidator.validatorName,
            validatorTranslationKey: namedValidator.validatorTranslationKey,
        }
    }

    namedValidators(field: SilvaOptionumFormPartFieldName): ReadonlyArray<NamedValidator> {
        switch(field) {
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment
                @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

                @replace-value-by-expression
                    [ searchValue="campusTextusObligatorius" replaceByExpression="attribute.attributeName.camelCase" ]

            }}}@  */
            case SilvaOptionumFormPartFieldName.campusTextusObligatorius: return [
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
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment @end-foreach @remove-blanks-and-linebreak-after-comment }}}@ */
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
            case SilvaOptionumFormPartFieldName.campusTextusOptionalis: return [
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
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
            default: return []
        }
    };
} 
