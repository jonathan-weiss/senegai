package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.*
import senegai.model.builders.UiEditorDsl
import senegai.model.builders.UiSearchResultDsl
import senegai.model.builders.UiViewsDsl
import senegai.model.schema.UiEntity
import senegai.model.schema.UiEntityEditorView
import senegai.model.schema.UiEntitySearchResultView

@Builder
@ExpectedClazzModelFromSuperiorBuilder(clazz = UiEntity::class, alias = "uiEntity")
interface UiEntityViewsBuilder: UiViewsDsl {

    @BuilderMethod
    @NewClazzModel(clazz = UiEntityEditorView::class, alias = "uiEditor")
    @SetClazzModelOfAlias(alias = "uiEntity", clazzProperty = "editorView", referencedAlias = "uiEditor")
    fun editorInternal(
        @InjectBuilder builder: UiEditorViewBuilder.() -> Unit
    )

    override fun editor(builder: UiEditorDsl.() -> Unit) {
        editorInternal(builder)
    }

    @BuilderMethod
    @NewClazzModel(clazz = UiEntitySearchResultView::class, alias = "uiSearchResult")
    @SetClazzModelOfAlias(alias = "uiEntity", clazzProperty = "searchResultView", referencedAlias = "uiSearchResult")
    fun searchResultInternal(
        @InjectBuilder builder: UiSearchResultViewBuilder.() -> Unit
    )

    override fun searchResult(builder: UiSearchResultDsl.() -> Unit) {
        searchResultInternal(builder)
    }
}
