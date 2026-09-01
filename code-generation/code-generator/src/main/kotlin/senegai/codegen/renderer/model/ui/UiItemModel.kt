package senegai.codegen.renderer.model.ui

import senegai.codegen.renderer.model.NameCase
import senegai.model.schema.BuiltInType
import senegai.model.schema.ItemId

data class UiItemModel(
    val itemDescription: UiItemDescriptionModel,
    val attributes: List<UiAttributeModel>,
) {
    val itemId: ItemId = itemDescription.itemId
    val itemName: NameCase = itemDescription.itemName

    /**
     * A single entity reference has no initial value: it starts out as `null` until the user
     * picks an entry, so that the required validator can complain. A list of references does
     * need one, because it is the value a newly pushed entry starts with.
     */
    val attributesWithAngularFormInitialValues: List<UiAttributeModel> = attributes
        .filter { it is BuiltInTypeUiAttributeModel || it.isList || it.isEnum }
        .filter { !it.isEntityReference || it.isList }

    val attributesWithCustomValidation: List<UiAttributeModel> = attributes
        .filter { it.hasCustomValidation }

    val usedEnums: List<UiEnumModel> = attributes
        .filterIsInstance<EnumUiAttributeModel>()
        .map { it.enum }
        .distinct()

    val attributesWithItemType: List<ItemUiIAttributeModel> = attributes
        .filterIsInstance<ItemUiIAttributeModel>()

    val attributesWithEnumType: List<EnumUiAttributeModel> = attributes
        .filterIsInstance<EnumUiAttributeModel>()

    /**
     * All attributes referencing another entity. They are a subset of the built-in UUID
     * attributes, as a reference is transported as the UUID of the referenced entity.
     */
    val attributesWithEntityReference: List<EntityReferenceUiAttributeModel> = attributes
        .filterIsInstance<EntityReferenceUiAttributeModel>()

    /** All entities referenced by an attribute of this item, e.g. to import one reference component per entity. */
    val referencedEntities: List<UiReferencedEntityModel> = attributesWithEntityReference
        .map { it.referencedEntity }
        .distinct()

    /** The entities referenced by a single-valued attribute, edited with the reference field component. */
    val singleReferencedEntities: List<UiReferencedEntityModel> = attributesWithEntityReference
        .filter { !it.isList }
        .map { it.referencedEntity }
        .distinct()

    /** The entities referenced by a list attribute, edited with the reference table component. */
    val listReferencedEntities: List<UiReferencedEntityModel> = attributesWithEntityReference
        .filter { it.isList }
        .map { it.referencedEntity }
        .distinct()

    val builtInTypeAndEnumAttributes: List<UiAttributeModel> = attributes
        .filter { it.isBuiltIn || it.isEnum }

    val directlyNestedItems: List<UiItemDescriptionModel> = attributes
        .filterIsInstance<ItemUiIAttributeModel>()
        .map { it.referencedItem }
        .distinct()

    /**
     * UUIDs are edited with the very same text input as strings, therefore they count
     * as text attributes for everything that is about rendering an input field.
     */
    val containsTextAttributes: Boolean = attributesOfTypes(TEXT_LIKE_BUILT_IN_TYPES, isList = false).any()
    val containsBooleanAttributes: Boolean = attributesOfType(BuiltInType.BOOLEAN, isList = false).any()
    val containsNumberAttributes: Boolean = attributesOfType(BuiltInType.NUMBER, isList = false).any()

    val containsTextListAttributes: Boolean = attributesOfTypes(TEXT_LIKE_BUILT_IN_TYPES, isList = true).any()
    val containsBooleanListAttributes: Boolean = attributesOfType(BuiltInType.BOOLEAN, isList = true).any()
    val containsNumberListAttributes: Boolean = attributesOfType(BuiltInType.NUMBER, isList = true).any()

    /**
     * Whether any attribute is transported as a [BuiltInType.UUID], e.g. to render the import of
     * the UUID type. Entity references count here, as they are UUIDs in the form as well.
     */
    val containsUuidAttributes: Boolean = attributesOfTypes(setOf(BuiltInType.UUID)).any()
            || attributesWithEntityReference.any()

    private fun attributesOfType(filterBuiltInType: BuiltInType, isList: Boolean): List<UiAttributeModel> =
        attributesOfTypes(setOf(filterBuiltInType), isList)

    /**
     * All built-in attributes of one of the [filterBuiltInTypes], of any cardinality if [isList]
     * is `null`. Entity references are left out: they happen to be UUIDs, but they are edited
     * with their own reference components, never with a built-in input.
     */
    private fun attributesOfTypes(filterBuiltInTypes: Set<BuiltInType>, isList: Boolean? = null): List<UiAttributeModel> {
        return attributes
            .filterIsInstance<BuiltInTypeUiAttributeModel>()
            .filter { !it.isEntityReference }
            .filter { it.builtInType in filterBuiltInTypes && (isList == null || it.isList == isList) }
    }

    private companion object {
        /** Built-in types that are rendered as a text input on the client. */
        private val TEXT_LIKE_BUILT_IN_TYPES = setOf(BuiltInType.STRING, BuiltInType.UUID)
    }
}
