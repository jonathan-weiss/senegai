package senegai.codegen.renderer.model

data class ItemAttribute(
    val attributeName: String,
) {
    val typescriptAttributeType: String = if(attributeName == "nickname") "string | null" else "string"
}
