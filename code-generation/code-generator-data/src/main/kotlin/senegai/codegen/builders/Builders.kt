package senegai.codegen.builders

import senegai.codegen.schema.BuiltInType
import senegai.codegen.schema.EntityId
import senegai.codegen.schema.EnumId
import senegai.codegen.schema.ItemId

@DslMarker
annotation class BuilderDslMarker

@Target(AnnotationTarget.TYPE)
@DslMarker
annotation class SchemaTypeMarker

interface RootDsl {

    fun schema(builder: SchemaDsl.() -> Unit,)
}

@BuilderDslMarker
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

@BuilderDslMarker
interface EnumDsl {

    fun enumValue(
        name: String,
    )
}

@BuilderDslMarker
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

