/* @tt{{{
    @rlb

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
        [ searchValue="opus-magnum" replaceByExpression="model.item.itemName.kebabCase" ]
        [ searchValue="OpusMagnum" replaceByExpression="model.item.itemName.pascalCase" ]
        [ searchValue="opusMagnum" replaceByExpression="model.item.itemName.camelCase" ]
        [ searchValue="opus-magnum" replaceByExpression="model.entity.entityName.kebabCase" ]
        [ searchValue="OpusMagnum" replaceByExpression="model.entity.entityName.pascalCase" ]
        [ searchValue="opusMagnum" replaceByExpression="model.entity.entityName.camelCase" ]

    @modify-provided-filename-by-replacements

    @rla

}}}@ */

import {Injectable} from '@angular/core';
import {ValidatorFn, Validators} from "@angular/forms";
import {OpusMagnumFormPartFieldName} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part-field-name";
import {NamedValidator} from "@app/shared/form-controls/named-validator";
import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
/* @tt{{{ @rlb  @ignore-text @rla }}}@ */

/* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */


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
            /* @tt{{{ @rlb
                @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

                @replace-value-by-expression
                    [ searchValue="campusTextusObligatorius" replaceByExpression="attribute.attributeName.camelCase" ]

            }}}@  */
            case OpusMagnumFormPartFieldName.campusTextusObligatorius: return [
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
            /* @tt{{{ @rlb @end-foreach @rla }}}@ */
            /* @tt{{{ @rlb  @ignore-text @rla }}}@ */
            case OpusMagnumFormPartFieldName.campusTextusOptionalis: return [
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
            /* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */
            default: return []
        }
    };
} 
