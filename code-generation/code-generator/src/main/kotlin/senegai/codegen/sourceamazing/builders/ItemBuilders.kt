package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.*
import senegai.codegen.schema.BuiltInType
import senegai.codegen.schema.ItemAttributeBuiltInType
import senegai.codegen.schema.ItemAttributeData
import senegai.codegen.schema.ItemData
import senegai.codegen.schema.SchemaData
import senegai.codegen.schema.ItemAttributeNestedItemType
import senegai.codegen.schema.ItemId

@Builder
@ExpectedAliasFromSuperiorBuilder(concept = SchemaData::class, conceptAlias = "root")
interface RootBuilder {

    @BuilderMethod
    @RedeclareAliasForNestedBuilder(conceptAlias = "root", newConceptAlias = "schema")
    fun createRootInstance(): SchemaBuilder
}

@Builder
@ExpectedAliasFromSuperiorBuilder(concept = SchemaData::class, conceptAlias = "schema")
interface SchemaBuilder: senegai.codegen.builders.SchemaBuilder {

    @BuilderMethod
    @NewConceptModel(concept = ItemData::class, declareConceptAlias = "item")
    @SetAliasConceptModelIdReferenceFacetValue(
        conceptToModifyAlias = "schema",
        facetToModify = "items",
        referencedConceptAlias = "item"
    )
    fun createNewItemInternal(
        @SetConceptModelIdValue(conceptToModifyAlias = "item")
        @SetFacetValue(conceptToModifyAlias = "item", facetToModify = "itemId") itemId: ItemId,
        @InjectBuilder builder: ItemBuilder.() -> Unit,
    )

    override fun createNewItem(itemId: ItemId, builder: senegai.codegen.builders.ItemBuilder.() -> Unit) {
        // cast from senegai.codegen.builders.ItemBuilder to our ItemBuilder
        createNewItemInternal(itemId, builder as ItemBuilder.() -> Unit)
    }
}

@Builder
@ExpectedAliasFromSuperiorBuilder(concept = ItemData::class, conceptAlias = "item")
interface ItemBuilder: senegai.codegen.builders.ItemBuilder {

    @BuilderMethod
    @NewConceptModel(concept = ItemAttributeData::class, declareConceptAlias = "itemAttribute")
    @NewConceptModel(concept = ItemAttributeBuiltInType::class, declareConceptAlias = "itemAttributeBuiltInType")
    @SetAliasConceptModelIdReferenceFacetValue(
        conceptToModifyAlias = "item",
        facetToModify = "attributes",
        referencedConceptAlias = "itemAttribute"
    )
    @SetAliasConceptModelIdReferenceFacetValue(
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


    @BuilderMethod
    @NewConceptModel(concept = ItemAttributeData::class, declareConceptAlias = "itemAttribute")
    @NewConceptModel(concept = ItemAttributeNestedItemType::class, declareConceptAlias = "itemAttributeNestedItemType")
    @NewConceptModel(concept = ItemData::class, declareConceptAlias = "nestedItem")
    @SetAliasConceptModelIdReferenceFacetValue(
        conceptToModifyAlias = "item",
        facetToModify = "attributes",
        referencedConceptAlias = "itemAttribute"
    )
    @SetFixedEnumFacetValue(
        conceptToModifyAlias = "itemAttribute",
        facetToModify = "cardinality",
        value = "EXACTLY_ONE"
    )
    @SetAliasConceptModelIdReferenceFacetValue(
        conceptToModifyAlias = "itemAttribute",
        facetToModify = "type",
        referencedConceptAlias = "itemAttributeNestedItemType"
    )
    @SetAliasConceptModelIdReferenceFacetValue(
        conceptToModifyAlias = "itemAttributeNestedItemType",
        facetToModify = "nestedItem",
        referencedConceptAlias = "nestedItem"
    )
    @RedeclareAliasForNestedBuilder(conceptAlias = "nestedItem", newConceptAlias = "item")
    fun attributeInternal(
        @SetFacetValue("itemAttribute", "attributeName") name: String,
        @SetFacetValue(conceptToModifyAlias = "nestedItem", facetToModify = "itemId") itemId: ItemId,
        @InjectBuilder builder: ItemBuilder.() -> Unit,
    )

    override fun attribute(
        name: String,
        itemId: ItemId,
        builder: senegai.codegen.builders.ItemBuilder.() -> Unit,
    ) {
        this.attributeInternal(name, itemId, builder)
    }
}
