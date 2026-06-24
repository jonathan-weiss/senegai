package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.NameCase
import senegai.codegen.renderer.model.ui.BuiltInTypeUiItemAttributeTypeModel
import senegai.codegen.renderer.model.ui.EnumUiItemAttributeTypeModel
import senegai.codegen.renderer.model.ui.ItemUiItemAttributeTypeModel
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
        val inputTag = when (attributeModel.type) {
            is BuiltInTypeUiItemAttributeTypeModel -> createBuiltInInput(attributeModel.attributeName, attributeModel.type.builtInType, attributeModel.isList)
            is EnumUiItemAttributeTypeModel -> createEnumInput(NameCase(attributeModel.type.enumId.enumName), attributeModel.attributeName, attributeModel.isList)
            is ItemUiItemAttributeTypeModel -> createItemInput(attributeModel.entity.entityName, attributeModel.type.item.itemName, attributeModel.attributeName, isList)
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
    private fun createBuiltInInput(attributeName: NameCase, builtInType: BuiltInType, isList: Boolean): String {
        val infix = determineFormComponentTypeInfix(builtInType)
        val attributeNameCamelCase = attributeName.camelCase
        if(isList) {
            return """<app-single-${infix}-form-field-table [formArray]="${attributeNameCamelCase}Control" columnHeader="$attributeNameCamelCase" placeholder="Enter $attributeNameCamelCase" [validatorTranslations]="${attributeNameCamelCase}ValidatorNames" />"""
        } else {
            return """<app-${infix}-input [${infix}FormControl]="${attributeNameCamelCase}Control" label="$attributeNameCamelCase" placeholder="Enter $attributeNameCamelCase" [validatorTranslations]="${attributeNameCamelCase}ValidatorNames" />"""
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
    private fun createItemInput(
        entityName: NameCase,
        itemName: NameCase,
        attributeName: NameCase,
        isList: Boolean
    ): String {
        val attributeNameCamelCase = attributeName.camelCase
        val entityNameKebabCase = entityName.kebabCase
        val itemNameKebabCase = itemName.kebabCase
        val itemNameCamelCase = itemName.camelCase
        val controlName = if(isList) "${attributeNameCamelCase}EditState.formGroupUnderEdit!" else "${attributeNameCamelCase}Control"
        return """<app-${entityNameKebabCase}-${itemNameKebabCase}-form-part [${itemNameCamelCase}Form]="$controlName"  />"""
    }

    /**
     *  ` <app-appellatio-comis-selector [enumFormControl]="appellatioControl" [validatorTranslations]="appellatioValidatorNames" />`
     *  ` <app-single-appellatio-comis-form-field-table [formArray]="appellatioOptionalisIteratusControl" columnHeader="appellatioOptionalisIteratus" [validatorTranslations]="appellatioOptionalisIteratusValidatorNames" />`
     */
    private fun createEnumInput(enumName: NameCase, attributeName: NameCase, isList: Boolean): String {
        val attributeNameCamelCase = attributeName.camelCase
        val enumNameKebabCase = enumName.kebabCase
        if(isList) {
            return """<app-single-${enumNameKebabCase}-form-field-table [formArray]="${attributeNameCamelCase}Control" columnHeader="$attributeNameCamelCase" [validatorTranslations]="${attributeNameCamelCase}ValidatorNames" />"""
        } else {
            return """<app-${enumNameKebabCase}-selector [enumFormControl]="${attributeNameCamelCase}Control" [validatorTranslations]="${attributeNameCamelCase}ValidatorNames" />"""
        }
    }

}
