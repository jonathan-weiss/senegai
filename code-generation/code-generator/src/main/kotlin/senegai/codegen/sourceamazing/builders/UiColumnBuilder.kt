package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.Builder
import org.codeblessing.sourceamazing.builder.api.annotations.BuilderMethod
import org.codeblessing.sourceamazing.builder.api.annotations.ExpectedAliasFromSuperiorBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.InjectBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.NewConceptModel
import org.codeblessing.sourceamazing.builder.api.annotations.SetAliasConceptModelIdReferenceFacetValue
import org.codeblessing.sourceamazing.builder.api.annotations.SetFacetValue
import senegai.codegen.builders.UiColumnDsl
import senegai.codegen.builders.UiSectionDsl
import senegai.codegen.schema.UiEntityEditorColumn
import senegai.codegen.schema.UiEntityEditorSection

@Builder
@ExpectedAliasFromSuperiorBuilder(concept = UiEntityEditorColumn::class, conceptAlias = "uiColumn")
interface UiColumnBuilder: UiColumnDsl {

    @BuilderMethod
    @NewConceptModel(concept = UiEntityEditorSection::class, declareConceptAlias = "uiSection")
    @SetAliasConceptModelIdReferenceFacetValue(
        conceptToModifyAlias = "uiColumn",
        facetToModify = "sections",
        referencedConceptAlias = "uiSection"
    )
    fun sectionInternal(
        @SetFacetValue("uiSection", "sectionName")
        sectionName: String,
        @InjectBuilder builder: UiSectionBuilder.() -> Unit
    )

    override fun section(sectionName: String, builder: UiSectionDsl.() -> Unit) {
        sectionInternal(sectionName, builder)
    }
}
