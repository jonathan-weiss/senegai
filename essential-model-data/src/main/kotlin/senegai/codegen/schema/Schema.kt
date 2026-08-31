package senegai.codegen.schema

data class SchemaData(
    val entities: List<Entity>,
    val items: List<Item>,
    val enums: List<EnumType>,
    val uiEntities: List<UiEntity>,
)
