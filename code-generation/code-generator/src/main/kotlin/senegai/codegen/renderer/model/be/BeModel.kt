package senegai.codegen.renderer.model.be

data class BeModel(
    val entities: List<BeEntityModel>,
) {
    val items: List<BeItemModel> = entities.flatMap { it.entityItemModels }.distinct()
    val enums: List<BeEnumModel> = entities.flatMap { it.entityEnumTypes }.distinct()
}
