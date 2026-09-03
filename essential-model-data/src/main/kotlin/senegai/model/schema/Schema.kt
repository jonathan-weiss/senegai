package senegai.model.schema

data class SchemaData(
    val items: List<Item>,
    val enums: List<EnumType>,
    val uiEntities: List<UiEntity>,
)
