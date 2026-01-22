package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.*
import senegai.codegen.schema.*

@Builder
@ExpectedAliasFromSuperiorBuilder(concept = SchemaData::class, conceptAlias = "schema")
interface SchemaBuilder: senegai.codegen.builders.SchemaBuilder {

    // **************
    // Entity
    // **************

    @BuilderMethod
    @NewConceptModel(concept = Entity::class, declareConceptAlias = "entity")
    @SetAliasConceptModelIdReferenceFacetValue(
        conceptToModifyAlias = "schema",
        facetToModify = "entities",
        referencedConceptAlias = "entity"
    )
    override fun createNewEntity(
        @SetConceptModelIdValue(conceptToModifyAlias = "entity")
        @SetFacetValue(conceptToModifyAlias = "entity", facetToModify = "entityId")
        entityId: EntityId,
        @SetFacetReference(conceptToModifyAlias = "entity", facetToModify = "item")
        itemId: ItemId,
    )

    // **************
    // Item
    // **************

    @BuilderMethod
    @NewConceptModel(concept = Item::class, declareConceptAlias = "item")
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
        // cast from senegai.codegen.builders.XyzBuilder to our XyzBuilder
        createNewItemInternal(itemId, builder)
    }

    // **************
    // Enum
    // **************

    @BuilderMethod
    @NewConceptModel(concept = EnumType::class, declareConceptAlias = "enum")
    @SetAliasConceptModelIdReferenceFacetValue(
        conceptToModifyAlias = "schema",
        facetToModify = "enums",
        referencedConceptAlias = "enum"
    )
    fun createNewEnumTypeInternal(
        @SetConceptModelIdValue(conceptToModifyAlias = "enum")
        @SetFacetValue(conceptToModifyAlias = "enum", facetToModify = "enumId")
        enumId: EnumId,
        @InjectBuilder builder: EnumBuilder.() -> Unit
    )

    override fun createNewEnumType(enumId: EnumId, builder: senegai.codegen.builders.EnumBuilder.() -> Unit) {
        // cast from senegai.codegen.builders.XyzBuilder to our XyzBuilder
        createNewEnumTypeInternal(enumId, builder)
    }
}
