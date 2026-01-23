package senegai.codegen.builders

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

    fun tab(
        tabName: String,
        builder: UiTabDsl.() -> Unit,
    )
}

@MainDslMarker
interface UiTabDsl {
    fun column(
        builder: UiColumnDsl.() -> Unit,
    )
}

@MainDslMarker
interface UiColumnDsl {
    fun section(
        sectionName: String,
        builder: UiSectionDsl.() -> Unit,
    )
}

@MainDslMarker
interface UiSectionDsl {
    fun entityAttribute(
        attributeName: String,
    )
}


