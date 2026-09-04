package senegai.codegen.renderer.model.db

import senegai.codegen.renderer.model.NameCase
import senegai.model.schema.DbItem
import senegai.model.schema.Item
import senegai.model.schema.ItemAttribute

/**
 * Resolves the SQL identifiers of an item: what its `dbItem` declares, or else the name of
 * the item resp. of the attribute in `SCREAMING_SNAKE_CASE`.
 *
 * Both the validation of the declared schema and its conversion into the [DbModel] need the
 * resolved names, therefore the fallback lives here instead of in one of the two.
 */
object DbNameDefaults {

    fun tableName(item: Item, dbItem: DbItem?): String =
        dbItem?.tableName ?: NameCase(item.itemName).screamingSnakeCase

    fun columnName(attribute: ItemAttribute, dbItem: DbItem?): String =
        dbItem?.columns?.firstOrNull { it.attributeName == attribute.attributeName }?.columnName
            ?: NameCase(attribute.attributeName).screamingSnakeCase
}
