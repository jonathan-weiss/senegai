package senegai.codegen.renderer.model.db

import senegai.model.schema.ItemId

/**
 * The relational schema of the whole model: one table per item that declares a primary key.
 *
 * An item without one is never stored on its own — it is nested into the row of the item
 * holding it — and has therefore no table here.
 */
data class DbModel(
    val tables: List<DbTableModel>,
) {
    fun tableOf(itemId: ItemId): DbTableModel = tables.singleOrNull { it.itemId == itemId }
        ?: throw NoSuchElementException(
            "The item '${itemId.itemName}' is stored in no table, because it declares no primary key. " +
                    "Stored are ${tables.map { it.itemName.pascalCase }}."
        )
}
