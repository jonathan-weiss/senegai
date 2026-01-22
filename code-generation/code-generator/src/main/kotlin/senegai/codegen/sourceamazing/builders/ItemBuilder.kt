package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.*
import senegai.codegen.schema.*

@Builder
@ExpectedAliasFromSuperiorBuilder(concept = Item::class, conceptAlias = "item")
interface ItemBuilder: senegai.codegen.builders.ItemBuilder {

    @BuilderMethod
    @NewConceptModel(concept = ItemAttribute::class, declareConceptAlias = "itemAttribute")
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
    override fun attribute(
        @SetFacetValue("itemAttribute", "attributeName") name: String,
        @SetFacetValue("itemAttribute", "type") type: BuiltInType,
    )

    @BuilderMethod
    @NewConceptModel(concept = ItemAttribute::class, declareConceptAlias = "itemAttribute")
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
    override fun attribute(
        @SetFacetValue("itemAttribute", "attributeName") name: String,
        @SetFacetValue("itemAttribute", "type") itemId: ItemId,
    )

    @BuilderMethod
    @NewConceptModel(concept = ItemAttribute::class, declareConceptAlias = "itemAttribute")
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
    override fun attribute(
        @SetFacetValue("itemAttribute", "attributeName") name: String,
        @SetFacetValue("itemAttribute", "type") enumId: EnumId,
    )
}

