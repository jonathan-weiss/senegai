package senegai.codegen.renderer.model.ui

import senegai.codegen.renderer.model.NameCase
import senegai.codegen.schema.EnumId
import senegai.codegen.schema.EnumType

data class UiEnumModel(
    private val enumType: EnumType,
) {
    val enumId: EnumId = enumType.enumId
    val enumName: NameCase = NameCase(enumType.enumId.enumName)
    val enumValues: List<NameCase> = enumType.enumValues.map { NameCase(it) }
}
