package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.BuiltInTypeUiItemAttributeTypeModel
import senegai.codegen.renderer.model.ui.EnumUiItemAttributeTypeModel
import senegai.codegen.renderer.model.ui.ItemUiItemAttributeTypeModel
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
            val singleValues = (1..10).map {
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
        val value = when (val attributeType = attributeContext.attributeModel.type) {
            is BuiltInTypeUiItemAttributeTypeModel -> createBuiltInExampleData(attributeType)
            is EnumUiItemAttributeTypeModel -> createEnumExampleData(attributeType, attributeContext.entityModel)
            is ItemUiItemAttributeTypeModel ->  createItemExampleData(attributeType, attributeContext.entityModel)
        }
        return if(attributeContext.attributeModel.isNullable) diceNullability(value) else value
    }

    private fun diceNullability(value: String?): String {
        return value ?: ""
    }

    private fun createBuiltInExampleData(
        attributeType: BuiltInTypeUiItemAttributeTypeModel,
    ): String {
        return when(attributeType.builtInType) {
            BuiltInType.STRING -> "'example'"
            BuiltInType.NUMBER -> "42"
            BuiltInType.BOOLEAN -> "true"
        }
    }


    private fun createEnumExampleData(
        attributeType: EnumUiItemAttributeTypeModel,
        entityModel: UiEntityModel,
    ): String {
        val enumType =
        entityModel.entityEnumTypes
            .firstOrNull { it.enumId == attributeType.enumId }
            ?: throw NoSuchElementException(
                "EnumType ${attributeType.enumId.enumName} not found. " +
                        "Available enum types: ${entityModel.entityEnumTypes.map { it.enumId.enumName }}",
            )
        val enumValue = enumType.enumValues.first()
        return "'$enumValue'"
    }


        private fun createItemExampleData(
        attributeType: ItemUiItemAttributeTypeModel,
        entityModel: UiEntityModel,
    ): String {
        val uiItemModel =
            entityModel.entityItemModels
                .firstOrNull { it.itemId == attributeType.item.itemId }
                ?: throw NoSuchElementException(
                    "UiItemModel ${attributeType.item.itemId} not found. " +
                            "Available items: ${entityModel.entityItemModels}",
                )

        // call a generated renderer to create inner example data
        return ItemExampleDataRenderer.renderTemplate(uiItemModel, entityModel)
    }
}
