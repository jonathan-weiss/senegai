package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.NameCase
import senegai.codegen.renderer.model.ui.BuiltInTypeUiItemAttributeTypeModel
import senegai.codegen.renderer.model.ui.EnumUiItemAttributeTypeModel
import senegai.codegen.renderer.model.ui.ItemUiItemAttributeTypeModel
import senegai.codegen.renderer.model.ui.UiItemAttributeModel
import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewItemModel
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
    ): String {
        val inputTag = when (attributeModel.type) {
            is BuiltInTypeUiItemAttributeTypeModel -> createBuiltInInput(attributeModel.attributeName, attributeModel.type.builtInType)
            is EnumUiItemAttributeTypeModel -> createEnumInput(NameCase(attributeModel.type.enumId.enumName), attributeModel.attributeName)
            is ItemUiItemAttributeTypeModel -> createItemInput(attributeModel.type.item.itemName, attributeModel.attributeName)
        }
        
        return """
                $inputTag        
            """.trimMargin(marginPrefix = "|")
    }

    /**
     *  ` <app-text-input [textFormControl]="campusTextusObligatoriusControl" label="campusTextusObligatorius" placeholder="Enter Campus Textus Obligatorius" [validatorTranslations]="campusTextusObligatoriusValidatorNames" />`
     *  ` <app-text-input [textFormControl]="campusTextusOptionalisControl" label="campusTextusOptionalis" placeholder="Enter campusTextusOptionalis" [validatorTranslations]="campusTextusOptionalisValidatorNames" />`
     */
    private fun createBuiltInInput(attributeName: NameCase, builtInType: BuiltInType): String {
        val infix = determineFormComponentTypeInfix(builtInType)
        val attributeNameCamelCase = attributeName.camelCase
        return """<app-${infix}-input [${infix}FormControl]="${attributeNameCamelCase}Control" label="$attributeNameCamelCase" placeholder="Enter $attributeNameCamelCase" [validatorTranslations]="${attributeNameCamelCase}ValidatorNames" />"""
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
     *  ` <app-articulus-interior-form-part [articulusInteriorForm]="articulusInteriorListFormGroupUnderEdit!"  />`
     *  ` <app-articulus-interior-form-part [articulusInteriorForm]="articulusInteriorSingularisControl"  />`
     */
    private fun createItemInput(itemName: NameCase, attributeName: NameCase): String {
        val attributeNameCamelCase = attributeName.camelCase
        val itemNameKebabCase = itemName.kebabCase
        val itemNameCamelCase = itemName.camelCase
        return """<app-${itemNameKebabCase}-form-part [${itemNameCamelCase}Form]="${attributeNameCamelCase}Control"  />"""
    }

    /**
     *  ` <app-appellatio-selector [enumFormControl]="appellatioControl" [validatorTranslations]="appellatioValidatorNames" />`
     */
    private fun createEnumInput(enumName: NameCase, attributeName: NameCase): String {
        val attributeNameCamelCase = attributeName.camelCase
        val enumNameKebabCase = enumName.kebabCase
        return """<app-${enumNameKebabCase}-selector [${attributeNameCamelCase}Control]="${attributeNameCamelCase}Control"  />"""
    }

}
