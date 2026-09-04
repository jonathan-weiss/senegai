package senegai.codegen.renderer.model.db

/**
 * The SQL type a column is declared with, named as PostgreSQL spells it.
 *
 * Everything that has no flat relational representation — a list of any kind and a nested
 * item — is stored as [JSONB]; an enum value is stored as [TEXT].
 */
enum class DbSqlType(val sqlTypeName: String) {
    UUID("uuid"),
    TEXT("text"),
    INTEGER("integer"),
    BOOLEAN("boolean"),
    JSONB("jsonb"),
}
