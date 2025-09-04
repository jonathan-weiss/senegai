package senegai.codegen.definitions

import senegai.codegen.schema.CodegenSchema
import senegai.codegen.schema.CodegenSchema.ItemAttributeTypeConcept

private const val PRINT_SCHEMA_INFORMATION = true

object SchemaInformationPrinter {

    fun printSchemaInformation(codegenSchema: CodegenSchema) {
        if(PRINT_SCHEMA_INFORMATION) {
            codegenSchema.getItems().forEach(::printItemInformation)
        }
    }

    private fun printItemInformation(item: CodegenSchema.ItemConcept) {
        println("Item: ${item.getItemName()}")
        item.getItemAttributes().forEach { attr ->
            println("  Attribute: ${attr.getAttributeName()}: (${attributeTypeAsText(attr.getAttributeType())})")
        }
    }

    private fun attributeTypeAsText(attributeType: ItemAttributeTypeConcept): String {
        return when(attributeType) {
            is CodegenSchema.ItemAttributeBuiltinTypeConcept -> "BuiltIn-Type:${attributeType.getBuiltinType().name}"
            is CodegenSchema.ItemAttributeNestedItemTypeConcept -> "Nested-Item: ${attributeType.getNestedItem().getItemName()}"
        }
    }
}
