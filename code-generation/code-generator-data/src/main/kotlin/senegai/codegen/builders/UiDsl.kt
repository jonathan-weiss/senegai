package senegai.codegen.builders

import senegai.codegen.schema.ItemId

@MainDslMarker
interface UiEntityDsl {

    fun views(
        builder: UiViewsDsl.() -> Unit,
    )
}

@MainDslMarker
interface UiViewsDsl {

    fun editor(
        builder: UiEditorDsl.() -> Unit,
    )
}

@MainDslMarker
interface UiEditorDsl {

    fun configureEditorForEntity(
        builder: UiEditorForMainItemDsl.() -> Unit
    )

    fun configureNestedEntityItem(
        itemId: ItemId,
        builder: UiEditorForNestedItemDsl.() -> Unit
    )
}


@MainDslMarker
interface UiEditorForMainItemDsl {

    fun tab(
        tabName: String,
        builder: UiTabDsl.() -> Unit,
    )

    fun column(
        builder: UiBlockEditorDsl.() -> Unit,
    )

}

@MainDslMarker
interface UiEditorForNestedItemDsl {
    fun column(
        builder: UiBlockEditorDsl.() -> Unit,
    )

}

@MainDslMarker
interface UiTabDsl {
    fun column(
        builder: UiBlockEditorDsl.() -> Unit,
    )
}

@MainDslMarker
interface UiBlockEditorDsl {

    fun text(
        text: String,
    )

    fun section(
        sectionName: String,
    )

    fun attribute(
        attributeName: String,
    )
}


