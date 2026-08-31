package senegai.model.builders

import senegai.model.schema.EntityId
import senegai.model.schema.EnumId
import senegai.model.schema.ItemId

@MainDslMarker
interface RootDsl {
    fun schema(builder: SchemaDsl.() -> Unit)
}

@MainDslMarker
interface SchemaDsl {
    fun entity(
        entityId: EntityId,
        entityRootItemId: ItemId,
        entityIdAttributeName: String,
    )

    fun enumType(
        enumId: EnumId,
        builder: EnumDsl.() -> Unit,
    )

    fun item(
        itemId: ItemId,
        builder: ItemDsl.() -> Unit,
    )

    fun uiEntity(
        entityId: EntityId,
        builder: UiEntityDsl.() -> Unit,
    )
}
