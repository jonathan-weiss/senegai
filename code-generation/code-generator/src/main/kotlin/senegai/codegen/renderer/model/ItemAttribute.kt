package senegai.codegen.renderer.model

data class ItemAttribute(
    val attributeName: String,
) {
    val typescriptAttributeTypeExample: String = "'John'"
    val typescriptAttributeInitialValue: String = "''"
    val typescriptAttributeType: String = "string"
    //val typescriptAttributeType: String = if(attributeName == "nickname") "string | null" else "string"
}
