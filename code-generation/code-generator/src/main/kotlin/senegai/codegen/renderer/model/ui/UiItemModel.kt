package senegai.codegen.renderer.model.ui

import senegai.codegen.renderer.NotSupportedInTemplateException
import senegai.codegen.renderer.model.NameCase
import senegai.model.schema.BuiltInType
import senegai.model.schema.ItemId

data class UiItemModel(
    val itemDescription: UiItemDescriptionModel,
    val attributes: List<UiAttributeModel>,
    /**
     * The attribute that identifies this item (its primary key), or `null` if this item
     * has none. It is always a plain built-in attribute, never a reference to another item.
     */
    val idAttribute: BuiltInTypeUiAttributeModel?,
    /**
     * The attributes that identify one instance of this item for a human reader, shown
     * next to the [idAttribute] wherever a reference to this item is rendered. A reference
     * is stored as a bare primary key, which tells the user nothing, so every place that
     * shows one resolves it to the whole item and renders these attributes instead.
     *
     * They are declared with `uiItem { displayAttributes { ... } }` in the essential model
     * data and belong to the item itself, therefore they are the same in every UiEntity.
     */
    val displayAttributes: List<UiAttributeModel>,
) {
    val itemId: ItemId = itemDescription.itemId
    val itemName: NameCase = itemDescription.itemName

    /** Whether this item is identified by a primary key and can therefore be addressed, searched and referenced. */
    val hasPrimaryKey: Boolean = idAttribute != null

    /**
     * The primary key, for the templates that are only rendered for an item that has one
     * (service, search/by-ids WTOs, reference components).
     */
    val primaryKeyAttribute: BuiltInTypeUiAttributeModel
        get() = requireNotNull(idAttribute) {
            "The item '${itemName.pascalCase}' declares no primary key."
        }

    /** Whether the primary key is a [BuiltInType.UUID], e.g. to render the import of the UUID type. */
    val hasUuidPrimaryKey: Boolean = idAttribute?.builtInType == BuiltInType.UUID

    /**
     * The TypeScript expression that turns the primary key in the edit route into the type
     * of the primary key. A route parameter is always a string, therefore only a NUMBER key
     * has to be converted at all. The templates that read it name the raw parameter
     * `idParam`.
     */
    val primaryKeyFromRouteParamExpression: String
        get() = when (primaryKeyAttribute.builtInType) {
            BuiltInType.UUID -> "idParam as UUID"
            BuiltInType.STRING -> "idParam as string"
            BuiltInType.NUMBER -> "Number(idParam)"
            BuiltInType.BOOLEAN -> throw NotSupportedInTemplateException(
                "The item '${itemName.pascalCase}' is identified by the attribute " +
                        "'${primaryKeyAttribute.attributeName.camelCase}' of the built-in type " +
                        "${primaryKeyAttribute.builtInType}, which no item can be identified by."
            )
        }

    /**
     * A single item reference has no initial value: it starts out as `null` until the user
     * picks an entry, so that the required validator can complain. A list of references does
     * need one, because it is the value a newly pushed entry starts with.
     */
    val attributesWithAngularFormInitialValues: List<UiAttributeModel> = attributes
        .filter { it is BuiltInTypeUiAttributeModel || it.isList || it.isEnum }
        .filter { !it.isItemReference || it.isList }

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
     * All attributes referencing another item. They are a subset of the built-in
     * attributes, as a reference is transported as the primary key of the referenced item.
     */
    val attributesWithItemReference: List<ItemReferenceUiAttributeModel> = attributes
        .filterIsInstance<ItemReferenceUiAttributeModel>()

    /** All items referenced by an attribute of this item, e.g. to import one reference component per item. */
    val referencedItems: List<UiReferencedItemModel> = attributesWithItemReference
        .map { it.referencedItem }
        .distinct()

    /** The items referenced by a single-valued attribute, edited with the reference field component. */
    val singleReferencedItems: List<UiReferencedItemModel> = attributesWithItemReference
        .filter { !it.isList }
        .map { it.referencedItem }
        .distinct()

    /** The items referenced by a list attribute, edited with the reference table component. */
    val listReferencedItems: List<UiReferencedItemModel> = attributesWithItemReference
        .filter { it.isList }
        .map { it.referencedItem }
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
     * the UUID type. A reference counts here if the item it references is identified by a UUID,
     * as such a reference is a UUID in the form as well.
     */
    val containsUuidAttributes: Boolean = attributesOfTypes(setOf(BuiltInType.UUID)).any()
            || attributesWithItemReference.any { it.builtInType == BuiltInType.UUID }

    private fun attributesOfType(filterBuiltInType: BuiltInType, isList: Boolean): List<UiAttributeModel> =
        attributesOfTypes(setOf(filterBuiltInType), isList)

    /**
     * All built-in attributes of one of the [filterBuiltInTypes], of any cardinality if [isList]
     * is `null`. Item references are left out: they happen to be of a built-in type, but they
     * are edited with their own reference components, never with a built-in input.
     */
    private fun attributesOfTypes(filterBuiltInTypes: Set<BuiltInType>, isList: Boolean? = null): List<UiAttributeModel> {
        return attributes
            .filterIsInstance<BuiltInTypeUiAttributeModel>()
            .filter { !it.isItemReference }
            .filter { it.builtInType in filterBuiltInTypes && (isList == null || it.isList == isList) }
    }

    private companion object {
        /** Built-in types that are rendered as a text input on the client. */
        private val TEXT_LIKE_BUILT_IN_TYPES = setOf(BuiltInType.STRING, BuiltInType.UUID)
    }
}
