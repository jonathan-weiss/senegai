package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.BuiltInTypeUiAttributeModel
import senegai.codegen.renderer.model.ui.EnumUiAttributeModel
import senegai.codegen.renderer.model.ui.ItemUiIAttributeModel
import senegai.codegen.renderer.model.ui.UiItemAttributeModel
import senegai.codegen.schema.BuiltInType

/**
 * Generate the content for the template `SingleFormInputHtmlTagRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `opus-magnum-form-part.component.html`
 * - path: `opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part.component.html`
 */
object SingleFormInputHtmlTagRenderer {

    /**
     *  ` <app-text-input [textFormControl]="campusTextusObligatoriusControl" label="campusTextusObligatorius" placeholder="Enter Campus Textus Obligatorius" [validatorTranslations]="campusTextusObligatoriusValidatorNames" />`
     *  ` <app-text-input [textFormControl]="campusTextusOptionalisControl" label="campusTextusOptionalis" placeholder="Enter campusTextusOptionalis" [validatorTranslations]="campusTextusOptionalisValidatorNames" />`
     *  ` <app-articulus-interior-form-part [articulusInteriorForm]="articulusInteriorListFormGroupUnderEdit!"  />`
     *  ` <app-articulus-interior-form-part [articulusInteriorForm]="articulusInteriorSingularisControl"  />`
     *  ` <app-appellatio-selector [enumFormControl]="appellatioControl" [validatorTranslations]="appellatioValidatorNames" />`
     */
    fun renderTemplate(
        attributeModel: UiItemAttributeModel,
        isList: Boolean = false,
    ): String {
        val inputTag = when (attributeModel) {
            is BuiltInTypeUiAttributeModel -> createBuiltInInput(attributeModel)
            is EnumUiAttributeModel -> createEnumInput(attributeModel)
            is ItemUiIAttributeModel -> createItemInput(attributeModel)
        }

        return """
                $inputTag        
            """.trimMargin(marginPrefix = "|")
    }

    /**
     *  ` <app-text-input [textFormControl]="campusTextusObligatoriusControl" label="campusTextusObligatorius" placeholder="Enter Campus Textus Obligatorius" [validatorTranslations]="campusTextusObligatoriusValidatorNames" />`
     *  ` <app-text-input [textFormControl]="campusTextusOptionalisControl" label="campusTextusOptionalis" placeholder="Enter campusTextusOptionalis" [validatorTranslations]="campusTextusOptionalisValidatorNames" />`
     *  ` <app-single-text-form-field-table [formArray]="iteratioSimpliciumTextuumControl" columnHeader="Iteratio Simplicium Textuum" placeholder="Iteratio Simplicium Textuum" />`
     */
    private fun createBuiltInInput(attributeModel: BuiltInTypeUiAttributeModel): String {
        val infix = determineFormComponentTypeInfix(attributeModel.builtInType)
        val attributeNameCamelCase = attributeModel.attributeName.camelCase
        return if(attributeModel.isList) {
            """<app-single-${infix}-form-field-table [formArray]="${attributeNameCamelCase}Control" columnHeader="$attributeNameCamelCase" placeholder="Enter $attributeNameCamelCase" [validatorTranslations]="${attributeNameCamelCase}ValidatorNames" />"""
        } else {
            """<app-${infix}-input [${infix}FormControl]="${attributeNameCamelCase}Control" label="$attributeNameCamelCase" placeholder="Enter $attributeNameCamelCase" [validatorTranslations]="${attributeNameCamelCase}ValidatorNames" />"""
        }
    }

    /**
     * Replaces the component to <app-text-input />
     */
    private fun determineFormComponentTypeInfix(builtInType: BuiltInType): String =
        when (builtInType) {
            BuiltInType.STRING -> "text"
            BuiltInType.NUMBER -> "number"
            BuiltInType.BOOLEAN -> "boolean"
        }


    /**
     *  ` <app-articulus-interior-form-part [articulusInteriorForm]="articulusInteriorListEditState.formGroupUnderEdit!"  />`
     *  ` <app-articulus-interior-form-part [articulusInteriorForm]="articulusInteriorSingularisControl"  />`
     */
    private fun createItemInput(attributeModel: ItemUiIAttributeModel): String {
        val attributeNameCamelCase = attributeModel.attributeName.camelCase
        val entityNameKebabCase = attributeModel.entity.entityName.kebabCase
        val itemNameKebabCase = attributeModel.referencedItem.itemName.kebabCase
        val itemNameCamelCase = attributeModel.referencedItem.itemName.camelCase
        val controlName = if(attributeModel.isList) "${attributeNameCamelCase}EditState.formGroupUnderEdit!" else "${attributeNameCamelCase}Control"
        return """<app-${entityNameKebabCase}-${itemNameKebabCase}-form-part [${itemNameCamelCase}Form]="$controlName"  />"""
    }

    /**
     *  ` <app-appellatio-comis-selector [enumFormControl]="appellatioControl" [validatorTranslations]="appellatioValidatorNames" />`
     *  ` <app-single-appellatio-comis-form-field-table [formArray]="appellatioOptionalisIteratusControl" columnHeader="appellatioOptionalisIteratus" [validatorTranslations]="appellatioOptionalisIteratusValidatorNames" />`
     */
    private fun createEnumInput(attributeModel: EnumUiAttributeModel): String {
        val attributeNameCamelCase = attributeModel.attributeName.camelCase
        val enumNameKebabCase = attributeModel.enum.enumName.kebabCase
        return if(attributeModel.isList) {
            """<app-single-${enumNameKebabCase}-form-field-table [formArray]="${attributeNameCamelCase}Control" columnHeader="$attributeNameCamelCase" [validatorTranslations]="${attributeNameCamelCase}ValidatorNames" />"""
        } else {
            """<app-${enumNameKebabCase}-selector [enumFormControl]="${attributeNameCamelCase}Control" [validatorTranslations]="${attributeNameCamelCase}ValidatorNames" />"""
        }
    }

}
