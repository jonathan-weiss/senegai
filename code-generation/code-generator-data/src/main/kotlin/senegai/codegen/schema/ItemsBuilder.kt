package senegai.codegen.schema

import org.codeblessing.sourceamazing.builder.api.annotations.*

@Builder
interface ItemsBuilder {

    @BuilderMethod
    @WithNewBuilder(ItemBuilder::class)
    @SetRandomConceptIdentifierValue("item")
    @NewConcept(CodegenSchema.ItemConcept::class, "item")
    fun createNewItem(
        @SetFacetValue("item", CodegenSchema.ItemConcept.ItemNameFacet ::class) itemName: String,
        @InjectBuilder builder: ItemBuilder.() -> Unit,
    )

    @BuilderMethod
    @WithNewBuilder(ItemBuilder::class)
    @NewConcept(CodegenSchema.ItemConcept::class, "item")
    fun createNewItem(
        @ProvideBuilderData itemIdentifier: ItemId,
        @InjectBuilder builder: ItemBuilder.() -> Unit,
    )

    @Builder
    @ExpectedAliasFromSuperiorBuilder("item")
    interface ItemBuilder {

        @BuilderMethod
        @NewConcept(CodegenSchema.ItemAttributeConcept::class, "itemAttribute")
        @NewConcept(CodegenSchema.ItemAttributeBuiltinTypeConcept::class, "itemAttributeBuiltinType")
        @SetRandomConceptIdentifierValue("itemAttribute")
        @SetRandomConceptIdentifierValue("itemAttributeBuiltinType")
        @SetAliasConceptIdentifierReferenceFacetValue("item", CodegenSchema.ItemConcept.ItemAttributesFacet::class, referencedConceptAlias = "itemAttribute")
        @SetAliasConceptIdentifierReferenceFacetValue("itemAttribute", CodegenSchema.ItemAttributeConcept.AttributeTypeFacet::class, referencedConceptAlias = "itemAttributeBuiltinType")
        fun attribute(
            @SetFacetValue("itemAttribute", CodegenSchema.ItemAttributeConcept.AttributeNameFacet::class) attributeName: String,
            @SetFacetValue("itemAttributeBuiltinType", CodegenSchema.ItemAttributeBuiltinTypeConcept.BuiltInTypeFacet::class) type: CodegenSchema.BuiltinType,
        )

    }
}
