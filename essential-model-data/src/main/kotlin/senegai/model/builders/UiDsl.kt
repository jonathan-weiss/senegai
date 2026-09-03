package senegai.model.builders

import senegai.model.schema.ItemId

@MainDslMarker
interface UiEntityDsl {
    fun views(builder: UiViewsDsl.() -> Unit)
}

@MainDslMarker
interface UiViewsDsl {
    fun editor(builder: UiEditorDsl.() -> Unit)
}

@MainDslMarker
interface UiEditorDsl {
    fun configureEditorForMainItem(builder: UiEditorForMainItemDsl.() -> Unit)

    fun configureEditorForNestedItem(
        itemId: ItemId,
        builder: UiEditorForNestedItemDsl.() -> Unit,
    )
}

@MainDslMarker
interface UiEditorForMainItemDsl {
    fun tab(
        tabTranslationKey: String,
        builder: UiTabDsl.() -> Unit,
    )

    fun column(builder: UiBlockEditorDsl.() -> Unit)
}

@MainDslMarker
interface UiEditorForNestedItemDsl {
    fun column(builder: UiBlockEditorDsl.() -> Unit)
}

@MainDslMarker
interface UiTabDsl {
    fun column(builder: UiBlockEditorDsl.() -> Unit)
}

@MainDslMarker
interface UiBlockEditorDsl {
    fun text(textTranslationKey: String)

    fun section(sectionTranslationKey: String)

    fun attribute(attributeName: String)
}
