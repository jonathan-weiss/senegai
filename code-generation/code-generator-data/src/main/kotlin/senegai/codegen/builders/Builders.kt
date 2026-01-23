package senegai.codegen.builders

import senegai.codegen.schema.BuiltInType
import senegai.codegen.schema.EntityId
import senegai.codegen.schema.EnumId
import senegai.codegen.schema.ItemId

@DslMarker
annotation class MainDslMarker

/**
 * DslMarker only says:
 * “If multiple implicit receivers share this marker, only the nearest one is visible.” Nothing more. Nothing less.
 *
 * Use different @DslMarkers when you have multiple independent DSL “worlds” that can be nested or used side-by-side,
 * and you don’t want them to hide each other’s receivers.
 *
 * Because Kotlin’s rule is:
 * Only receivers annotated with the same marker participate in hiding outer receivers.
 */
@MainDslMarker
interface RootDsl {

    fun schema(builder: SchemaDsl.() -> Unit,)
}

@MainDslMarker
interface SchemaDsl {

    fun entity(
        entityId: EntityId,
        itemId: ItemId,
    )

    fun enumType(
        enumId: EnumId,
        builder: EnumDsl.() -> Unit,
    )

    fun item(
        itemId: ItemId,
        builder: ItemDsl.() -> Unit,
    )
}

@MainDslMarker
interface EnumDsl {

    fun enumValue(
        name: String,
    )
}

@MainDslMarker
interface ItemDsl {

    fun attribute(
        name: String,
        type: BuiltInType
    )

    fun attribute(
        name: String,
        itemId: ItemId,
    )

    fun attribute(
        name: String,
        enumId: EnumId,
    )
}

