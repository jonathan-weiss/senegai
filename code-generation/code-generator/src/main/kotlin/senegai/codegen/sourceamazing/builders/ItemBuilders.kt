package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.*
import org.codeblessing.sourceamazing.schema.api.ConceptIdentifier
import senegai.codegen.schema.BuiltInType
import senegai.codegen.schema.ItemAttributeBuiltInType
import senegai.codegen.schema.ItemAttributeData
import senegai.codegen.schema.ItemData
import senegai.codegen.schema.ItemsData
import senegai.codegen.builders.ItemId
import senegai.codegen.schema.ItemAttributeNestedItemType

@Builder
interface RootBuilder {

    @BuilderMethod
    @NewConcept(concept = ItemsData::class, declareConceptAlias = "items")
    fun createRootInstance(
        @SetConceptIdentifierValue(conceptToModifyAlias = "items") rootInstanceId: ConceptIdentifier,
    ): ItemsBuilder
}

@Builder
@ExpectedAliasFromSuperiorBuilder(concept = ItemsData::class, conceptAlias = "items")
interface ItemsBuilder: senegai.codegen.builders.ItemsBuilder {

    @BuilderMethod
    @SetRandomConceptIdentifierValue(conceptToModifyAlias = "item")
    @NewConcept(concept = ItemData::class, declareConceptAlias = "item")
    @SetAliasConceptIdentifierReferenceFacetValue(
        conceptToModifyAlias = "items",
        facetToModify = "items",
        referencedConceptAlias = "item"
    )

    fun createNewItem(
        @SetFacetValue(conceptToModifyAlias = "item", facetToModify = "itemName") itemName: String,
        @InjectBuilder builder: ItemBuilder.() -> Unit,
    )

    override fun createNewItem(
        itemId: ItemId,
        builder: senegai.codegen.builders.ItemBuilder.() -> Unit,
    ) {
        createNewItem(itemId.id, builder)
    }
}

@Builder
@ExpectedAliasFromSuperiorBuilder(concept = ItemData::class, conceptAlias = "item")
interface ItemBuilder: senegai.codegen.builders.ItemBuilder {

    @BuilderMethod
    @NewConcept(concept = ItemAttributeData::class, declareConceptAlias = "itemAttribute")
    @NewConcept(concept = ItemAttributeBuiltInType::class, declareConceptAlias = "itemAttributeBuiltInType")
    @SetRandomConceptIdentifierValue(conceptToModifyAlias = "itemAttribute")
    @SetRandomConceptIdentifierValue(conceptToModifyAlias = "itemAttributeBuiltInType")
    @SetAliasConceptIdentifierReferenceFacetValue(
        conceptToModifyAlias = "item",
        facetToModify = "attributes",
        referencedConceptAlias = "itemAttribute"
    )
    @SetAliasConceptIdentifierReferenceFacetValue(
        conceptToModifyAlias = "itemAttribute",
        facetToModify = "type",
        referencedConceptAlias = "itemAttributeBuiltInType"
    )
    @SetFixedEnumFacetValue(
        conceptToModifyAlias = "itemAttribute",
        facetToModify = "cardinality",
        value = "EXACTLY_ONE"
    )
    override fun attribute(
        @SetFacetValue("itemAttribute", "attributeName") name: String,
        @SetFacetValue("itemAttributeBuiltInType", "builtInType") type: BuiltInType,
    )

    override fun attribute(
        name: String,
        itemId: ItemId,
        builder: senegai.codegen.builders.ItemBuilder.() -> Unit
    ) {
        return attribute(name, itemId.id, builder)
    }

    @BuilderMethod
    @NewConcept(concept = ItemAttributeData::class, declareConceptAlias = "itemAttribute")
    @NewConcept(concept = ItemAttributeNestedItemType::class, declareConceptAlias = "itemAttributeNestedItemType")
    @NewConcept(concept = ItemData::class, declareConceptAlias = "nestedItem")
    @SetRandomConceptIdentifierValue(conceptToModifyAlias = "itemAttribute")
    @SetRandomConceptIdentifierValue(conceptToModifyAlias = "itemAttributeNestedItemType")
    @SetRandomConceptIdentifierValue(conceptToModifyAlias = "nestedItem")
    @SetAliasConceptIdentifierReferenceFacetValue(
        conceptToModifyAlias = "item",
        facetToModify = "attributes",
        referencedConceptAlias = "itemAttribute"
    )
    @SetFixedEnumFacetValue(
        conceptToModifyAlias = "itemAttribute",
        facetToModify = "cardinality",
        value = "EXACTLY_ONE"
    )
    @SetAliasConceptIdentifierReferenceFacetValue(
        conceptToModifyAlias = "itemAttribute",
        facetToModify = "type",
        referencedConceptAlias = "itemAttributeNestedItemType"
    )
    @SetAliasConceptIdentifierReferenceFacetValue(
        conceptToModifyAlias = "itemAttributeNestedItemType",
        facetToModify = "nestedItem",
        referencedConceptAlias = "nestedItem"
    )
    @RedeclareAliasForNestedBuilder(conceptAlias = "nestedItem", newConceptAlias = "item")
    fun attribute(
        @SetFacetValue("itemAttribute", "attributeName") name: String,
        @SetFacetValue(conceptToModifyAlias = "nestedItem", facetToModify = "itemName") itemName: String,
        @InjectBuilder builder: ItemBuilder.() -> Unit
    )
}
