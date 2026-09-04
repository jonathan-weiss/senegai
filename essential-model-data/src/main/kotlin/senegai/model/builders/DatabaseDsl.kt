package senegai.model.builders

/**
 * Declares how one item is mapped to a SQL table, for example
 * `dbItem(itemId = Items.COMPANY) { tableName(name = "T_COMPANY") }`.
 *
 * Both declarations are optional and only override a default: without them the table is
 * named after the item and every column after its attribute, in `SCREAMING_SNAKE_CASE`.
 */
@MainDslMarker
interface DbItemDsl {
    fun tableName(name: String)

    /**
     * Declares the column the attribute [attributeName] of this item is stored in.
     * The attributes not declared here keep their default column name.
     */
    fun column(attributeName: String, columnName: String)
}
