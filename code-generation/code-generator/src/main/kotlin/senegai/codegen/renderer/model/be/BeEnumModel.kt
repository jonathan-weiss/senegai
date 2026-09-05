package senegai.codegen.renderer.model.be

import senegai.codegen.renderer.model.NameCase
import senegai.codegen.renderer.model.db.DbEnumModel
import senegai.model.schema.EnumId
import senegai.model.schema.EnumType

data class BeEnumModel(
    private val enumType: EnumType,
    /** How the values of this enum type are spelled in the database. */
    val dbEnum: DbEnumModel,
) {
    val enumId: EnumId = enumType.enumId
    val enumName: NameCase = NameCase(enumType.enumId.enumName)
    val enumValues: List<NameCase> = enumType.enumValues.map { NameCase(it) }
}
