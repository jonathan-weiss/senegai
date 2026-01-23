package senegai.codegen.builders

import senegai.codegen.schema.EntityId
import senegai.codegen.schema.EnumId
import senegai.codegen.schema.ItemId

@MainDslMarker
interface RootDsl {

    fun schema(builder: SchemaDsl.() -> Unit,)
}

@MainDslMarker
interface SchemaDsl {

    fun entity(
        entityId: EntityId,
        entityRootItemId: ItemId,
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


