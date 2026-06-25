package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.BuiltInTypeUiAttributeModel
import senegai.codegen.renderer.model.ui.EnumUiAttributeModel
import senegai.codegen.renderer.model.ui.ItemUiIAttributeModel
import senegai.codegen.renderer.model.ui.UiEntityModel
import senegai.codegen.renderer.model.ui.UiItemAttributeModel
import senegai.codegen.schema.BuiltInType

/**
 * Generate the content for the template `AttributeExampleDataRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `opus-magnum-example-data.ts`
 * - path: `opus-magnum/opus-magnum-example-data.ts`
 */
object AttributeExampleDataRenderer {

    data class AttributeContext(
        val attributeModel: UiItemAttributeModel,
        val entityModel: UiEntityModel,
        val seed: Int,
    ) {
        fun withNextSeed(): AttributeContext {
            return copy(seed = seed + 1)
        }
    }

    fun renderTemplate(
        attributeModel: UiItemAttributeModel,
        entityModel: UiEntityModel,
    ): String {
        val seed = 0 // TODO dice seed with entityModel and attributeModel
        val attributeContext = AttributeContext(attributeModel, entityModel, seed)
        return """
                ${valueWithCardinality(attributeContext)}        
            """.trimMargin(marginPrefix = "|")
    }

    private fun valueWithCardinality(
        attributeContext: AttributeContext,
    ): String {
        if (attributeContext.attributeModel.isList) {
            val singleValues = (1..2).map {
                createSingleValue(attributeContext.withNextSeed())
            }
            return wrapToList(singleValues)
        } else {
            return createSingleValue(attributeContext)
        }
    }

    private fun wrapToList(singleValues: List<String>): String {
        val entries = singleValues.joinToString(",\n|")
        return """[
                |$entries     
            ]""".trimMargin(marginPrefix = "|")
    }

    private fun createSingleValue(attributeContext: AttributeContext): String {
        val value = when (val attributeModel = attributeContext.attributeModel) {
            is BuiltInTypeUiAttributeModel -> createBuiltInExampleData(attributeModel)
            is EnumUiAttributeModel -> createEnumExampleData(attributeModel, attributeContext.entityModel)
            is ItemUiIAttributeModel ->  createItemExampleData(attributeModel, attributeContext.entityModel)
        }
        return if(attributeContext.attributeModel.isNullable) diceNullability(value) else value
    }

    private fun diceNullability(value: String?): String {
        return value ?: ""
    }

    private fun createBuiltInExampleData(
        attribute: BuiltInTypeUiAttributeModel,
    ): String {
        return when(attribute.builtInType) {
            BuiltInType.STRING -> "'example'"
            BuiltInType.NUMBER -> "42"
            BuiltInType.BOOLEAN -> "true"
        }
    }


    private fun createEnumExampleData(
        attributeModel: EnumUiAttributeModel,
        entityModel: UiEntityModel,
    ): String {
        val enumType =
        entityModel.entityEnumTypes
            .firstOrNull { it.enumId == attributeModel.enumId }
            ?: throw NoSuchElementException(
                "EnumType ${attributeModel.enumId.enumName} not found. " +
                        "Available enum types: ${entityModel.entityEnumTypes.map { it.enumId.enumName }}",
            )
        val enumName = enumType.enumName.pascalCase
        val enumValue = enumType.enumValues.first().screamingSnakeCase
        return "${enumName}Enum.$enumValue"
    }


        private fun createItemExampleData(
            attributeModel: ItemUiIAttributeModel,
            entityModel: UiEntityModel,
    ): String {
        val uiItemModel =
            entityModel.entityItemModels
                .firstOrNull { it.itemId == attributeModel.referencedItem.itemId }
                ?: throw NoSuchElementException(
                    "UiItemModel ${attributeModel.referencedItem.itemId} not found. " +
                            "Available items: ${entityModel.entityItemModels}",
                )

        // call a generated renderer to create inner example data
        return ItemExampleDataRenderer.renderTemplate(uiItemModel, entityModel)
    }
}
