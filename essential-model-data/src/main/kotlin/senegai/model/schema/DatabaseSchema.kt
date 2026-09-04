package senegai.model.schema

/**
 * The relational mapping of an [Item] that declares a primary key, as far as it is declared
 * by a `dbItem` block. Everything in it is optional: an item without a `dbItem`, or a
 * `dbItem` that leaves a part out, falls back to the name of the item resp. of the attribute
 * in `SCREAMING_SNAKE_CASE`.
 *
 * A name declared here is a SQL identifier and is therefore used verbatim, never re-cased.
 */
data class DbItem(
    val itemId: ItemId,
    /** `null` as long as no `tableName` is declared. */
    val tableName: String?,
    /** Only the columns whose name is declared; empty as long as none is. */
    val columns: List<DbColumn>,
)

/**
 * The column the attribute [attributeName] of an [Item] is stored in.
 */
data class DbColumn(
    val attributeName: String,
    val columnName: String,
)
