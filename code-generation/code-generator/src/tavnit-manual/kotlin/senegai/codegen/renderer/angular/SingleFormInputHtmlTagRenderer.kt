package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.BuiltInTypeUiAttributeModel
import senegai.codegen.renderer.model.ui.EntityReferenceUiAttributeModel
import senegai.codegen.renderer.model.ui.EnumUiAttributeModel
import senegai.codegen.renderer.model.ui.ItemUiIAttributeModel
import senegai.codegen.renderer.model.ui.UiAttributeModel
import senegai.model.schema.BuiltInType

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
        attributeModel: UiAttributeModel,
        isList: Boolean = false,
    ): String {
        val inputTag = when (attributeModel) {
            // must be checked before the built-in input: a reference is a UUID, but it is never
            // edited as one
            is EntityReferenceUiAttributeModel -> createEntityReferenceInput(attributeModel)
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
     *  ` <app-entitas-relata-membrum-relatum-reference-field [membrumRelatumReferenceFormControl]="relatioAdEntitatemOptionalisControl" [validatorTranslations]="relatioAdEntitatemOptionalisValidatorNames" />`
     *  ` <app-entitas-relata-membrum-relatum-reference-table [membrumRelatumReferenceFormArray]="relatioAdEntitatemOptionalisIteratusControl" />`
     *
     * A reference is stored as the UUID of the referenced entity, which tells the user nothing.
     * It is therefore edited with the reference components of the referenced entity, which search
     * it with a typeahead and show it by its display attributes.
     */
    private fun createEntityReferenceInput(attributeModel: EntityReferenceUiAttributeModel): String {
        val attributeNameCamelCase = attributeModel.attributeName.camelCase
        val referencedEntity = attributeModel.referencedEntity
        val componentSelectorPrefix = "app-${referencedEntity.entityName.kebabCase}-${referencedEntity.rootItem.itemName.kebabCase}-reference"
        val referencedItemCamelCase = referencedEntity.rootItem.itemName.camelCase
        return if (attributeModel.isList) {
            """<$componentSelectorPrefix-table [${referencedItemCamelCase}ReferenceFormArray]="${attributeNameCamelCase}Control" />"""
        } else {
            """<$componentSelectorPrefix-field [${referencedItemCamelCase}ReferenceFormControl]="${attributeNameCamelCase}Control" [validatorTranslations]="${attributeNameCamelCase}ValidatorNames" />"""
        }
    }

    /**
     * Replaces the component to <app-text-input />
     */
    private fun determineFormComponentTypeInfix(builtInType: BuiltInType): String =
        when (builtInType) {
            // a UUID is edited as plain text, therefore it reuses the text input component
            BuiltInType.STRING, BuiltInType.UUID -> "text"
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
