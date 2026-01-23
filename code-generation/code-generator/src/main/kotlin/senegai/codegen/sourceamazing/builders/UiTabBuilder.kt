package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.Builder
import org.codeblessing.sourceamazing.builder.api.annotations.BuilderMethod
import org.codeblessing.sourceamazing.builder.api.annotations.ExpectedAliasFromSuperiorBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.InjectBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.NewConceptModel
import org.codeblessing.sourceamazing.builder.api.annotations.SetAliasConceptModelIdReferenceFacetValue
import org.codeblessing.sourceamazing.builder.api.annotations.SetFacetValue
import senegai.codegen.builders.UiColumnDsl
import senegai.codegen.builders.UiTabDsl
import senegai.codegen.schema.UiEntityEditorColumn
import senegai.codegen.schema.UiEntityEditorSection
import senegai.codegen.schema.UiEntityEditorTab

@Builder
@ExpectedAliasFromSuperiorBuilder(concept = UiEntityEditorTab::class, conceptAlias = "uiTab")
interface UiTabBuilder: UiTabDsl {

    @BuilderMethod
    @NewConceptModel(concept = UiEntityEditorColumn::class, declareConceptAlias = "uiColumn")
    @SetAliasConceptModelIdReferenceFacetValue(
        conceptToModifyAlias = "uiTab",
        facetToModify = "columns",
        referencedConceptAlias = "uiColumn"
    )
    fun columnInternal(
        @InjectBuilder builder: UiColumnBuilder.() -> Unit
    )

    override fun column(builder: UiColumnDsl.() -> Unit) {
        columnInternal(builder)
    }
}
