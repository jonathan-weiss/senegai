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
import senegai.codegen.schema.UiEntity
import senegai.codegen.schema.UiEntityEditorSection
import senegai.codegen.schema.UiEntityEditorTab
import senegai.codegen.schema.UiEntityEditorView

@Builder
@ExpectedAliasFromSuperiorBuilder(concept = UiEntityEditorView::class, conceptAlias = "uiEditor")
interface UiEditorViewBuilder: UiEditorDsl {

    @BuilderMethod
    @NewConceptModel(concept = UiEntityEditorTab::class, declareConceptAlias = "uiTab")
    @SetAliasConceptModelIdReferenceFacetValue(
        conceptToModifyAlias = "uiEditor",
        facetToModify = "tabs",
        referencedConceptAlias = "uiTab"
    )
    fun tabInternal(
        @SetFacetValue(conceptToModifyAlias = "uiTab", facetToModify = "tabName")
        tabName: String,
        @InjectBuilder builder: UiTabBuilder.() -> Unit
    )

    override fun tab(tabName: String, builder: UiTabDsl.() -> Unit) {
        tabInternal(tabName, builder)
    }
}
