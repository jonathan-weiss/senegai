package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.Builder
import org.codeblessing.sourceamazing.builder.api.annotations.BuilderMethod
import org.codeblessing.sourceamazing.builder.api.annotations.ExpectedAliasFromSuperiorBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.InjectBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.NewConceptModel
import org.codeblessing.sourceamazing.builder.api.annotations.SetAliasConceptModelIdReferenceFacetValue
import org.codeblessing.sourceamazing.builder.api.annotations.SetFacetValue
import senegai.codegen.builders.UiEntityDsl
import senegai.codegen.builders.UiViewsDsl
import senegai.codegen.schema.EntityId
import senegai.codegen.schema.UiEntity
import senegai.codegen.schema.UiEntityEditorView

@Builder
@ExpectedAliasFromSuperiorBuilder(concept = UiEntity::class, conceptAlias = "uiEntity")
interface UiEntityBuilder: UiEntityDsl {

    @BuilderMethod
    fun viewsInternal(@InjectBuilder builder: UiEntityViewsBuilder.() -> Unit)

    override fun views(builder: UiViewsDsl.() -> Unit) {
        viewsInternal(builder)
    }
}
