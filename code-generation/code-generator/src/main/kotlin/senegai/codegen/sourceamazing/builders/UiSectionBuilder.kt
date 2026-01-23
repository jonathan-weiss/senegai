package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.Builder
import org.codeblessing.sourceamazing.builder.api.annotations.BuilderMethod
import org.codeblessing.sourceamazing.builder.api.annotations.ExpectedAliasFromSuperiorBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.NewConceptModel
import org.codeblessing.sourceamazing.builder.api.annotations.SetAliasConceptModelIdReferenceFacetValue
import org.codeblessing.sourceamazing.builder.api.annotations.SetFacetValue
import senegai.codegen.builders.EnumDsl
import senegai.codegen.builders.UiSectionDsl
import senegai.codegen.schema.EnumType
import senegai.codegen.schema.UiEntityAttributeBlock
import senegai.codegen.schema.UiEntityEditorSection

@Builder
@ExpectedAliasFromSuperiorBuilder(concept = UiEntityEditorSection::class, conceptAlias = "uiSection")
interface UiSectionBuilder: UiSectionDsl {

    @BuilderMethod
    @NewConceptModel(concept = UiEntityAttributeBlock::class, declareConceptAlias = "uiEntityAttributeBlock")
    @SetAliasConceptModelIdReferenceFacetValue(
        conceptToModifyAlias = "uiSection",
        facetToModify = "blocks",
        referencedConceptAlias = "uiEntityAttributeBlock"
    )

    override fun entityAttribute(
        @SetFacetValue("uiEntityAttributeBlock", "entityAttributeName")
        attributeName: String,
    )
}
