package senegai.codegen.renderer.model.db

import senegai.codegen.renderer.model.NameCase

/**
 * One column of a [DbTableModel], storing exactly one attribute of the item.
 *
 * The [columnName] is a SQL identifier and therefore a plain string, either declared by the
 * `dbItem` of the item or derived from the [attributeName].
 */
data class DbColumnModel(
    val attributeName: NameCase,
    val columnName: String,
    val sqlType: DbSqlType,
    val isNullable: Boolean,
    val isPrimaryKey: Boolean,
) {
    val isJsonb: Boolean = sqlType == DbSqlType.JSONB
}
