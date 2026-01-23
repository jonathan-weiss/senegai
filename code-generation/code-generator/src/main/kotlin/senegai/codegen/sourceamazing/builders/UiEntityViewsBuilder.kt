package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.Builder
import org.codeblessing.sourceamazing.builder.api.annotations.BuilderMethod
import org.codeblessing.sourceamazing.builder.api.annotations.ExpectedAliasFromSuperiorBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.InjectBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.NewConceptModel
import org.codeblessing.sourceamazing.builder.api.annotations.SetAliasConceptModelIdReferenceFacetValue
import org.codeblessing.sourceamazing.builder.api.annotations.SetFacetValue
import senegai.codegen.builders.UiColumnDsl
import senegai.codegen.builders.UiEditorDsl
import senegai.codegen.builders.UiTabDsl
import senegai.codegen.builders.UiViewsDsl
import senegai.codegen.schema.UiEntity
import senegai.codegen.schema.UiEntityEditorSection
import senegai.codegen.schema.UiEntityEditorTab
import senegai.codegen.schema.UiEntityEditorView

@Builder
@ExpectedAliasFromSuperiorBuilder(concept = UiEntity::class, conceptAlias = "uiEntity")
interface UiEntityViewsBuilder: UiViewsDsl {

    @BuilderMethod
    @NewConceptModel(concept = UiEntityEditorView::class, declareConceptAlias = "uiEditor")
    @SetAliasConceptModelIdReferenceFacetValue(
        conceptToModifyAlias = "uiEntity",
        facetToModify = "editorView",
        referencedConceptAlias = "uiEditor"
    )
    fun editorInternal(
        @InjectBuilder builder: UiEditorViewBuilder.() -> Unit
    )

    override fun editor(builder: UiEditorDsl.() -> Unit) {
        editorInternal(builder)
    }
}
