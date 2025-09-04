package senegai.codegen.schema

import org.codeblessing.sourceamazing.schema.api.annotations.*
import senegai.codegen.schema.CodegenSchema.ItemAttributeNestedItemTypeConcept.NestedItemReferenceFacet

@Schema([
    CodegenSchema.ItemConcept::class,
    CodegenSchema.ItemAttributeConcept::class,
    CodegenSchema.ItemAttributeBuiltinTypeConcept::class,
    CodegenSchema.ItemAttributeNestedItemTypeConcept::class,
])
interface CodegenSchema {

    @QueryConcepts(conceptClasses = [ItemConcept::class])
    fun getItems(): List<ItemConcept>

    @Concept(facets = [
        ItemConcept.ItemNameFacet::class,
        ItemConcept.ItemAttributesFacet::class,
    ])
    interface ItemConcept {
        @StringFacet
        interface ItemNameFacet

        @ReferenceFacet(
            minimumOccurrences = 1,
            maximumOccurrences = Int.MAX_VALUE,
            referencedConcepts = [ItemAttributeConcept::class])
        interface ItemAttributesFacet

        @QueryFacetValue(ItemNameFacet::class)
        fun getItemName(): String

        @QueryFacetValue(ItemAttributesFacet::class)
        fun getItemAttributes(): List<ItemAttributeConcept>
    }

    @Concept(facets = [
        ItemAttributeConcept.AttributeNameFacet::class,
        ItemAttributeConcept.AttributeTypeFacet::class,
    ])
    interface ItemAttributeConcept {
        @StringFacet
        interface AttributeNameFacet

        @ReferenceFacet([
            ItemAttributeBuiltinTypeConcept::class,
            ItemAttributeNestedItemTypeConcept::class,
        ])
        interface AttributeTypeFacet

        @QueryFacetValue(AttributeNameFacet::class)
        fun getAttributeName(): String

        @QueryFacetValue(AttributeTypeFacet::class)
        fun getAttributeType(): ItemAttributeTypeConcept

    }


    sealed interface ItemAttributeTypeConcept

    @Concept(facets = [
        ItemAttributeBuiltinTypeConcept.BuiltInTypeFacet::class,
    ])
    interface ItemAttributeBuiltinTypeConcept: ItemAttributeTypeConcept {
        @EnumFacet(BuiltinType::class)
        interface BuiltInTypeFacet

        @QueryFacetValue(BuiltInTypeFacet::class)
        fun getBuiltinType(): BuiltinType
    }

    @Concept(facets = [
        NestedItemReferenceFacet::class,
    ])
    interface ItemAttributeNestedItemTypeConcept: ItemAttributeTypeConcept {
        @ReferenceFacet([ItemConcept::class])
        interface NestedItemReferenceFacet

        @QueryFacetValue(NestedItemReferenceFacet::class)
        fun getNestedItem(): ItemConcept
    }

    enum class BuiltinType {
        STRING,
        INTEGER,
    }
}
