package senegai.codegen.schema

import org.codeblessing.sourceamazing.builder.api.annotations.BuilderData
import org.codeblessing.sourceamazing.builder.api.annotations.BuilderDataProvider
import org.codeblessing.sourceamazing.builder.api.annotations.SetFacetValue
import org.codeblessing.sourceamazing.builder.api.annotations.SetProvidedConceptIdentifierValue
import org.codeblessing.sourceamazing.builder.api.annotations.SetProvidedFacetValue
import org.codeblessing.sourceamazing.schema.api.ConceptIdentifier

@BuilderDataProvider
data class ItemId(
    val name: String
) {
    @BuilderData
    @SetProvidedConceptIdentifierValue(conceptToModifyAlias = "item")
    fun getItemIdentifier(): ConceptIdentifier {
        return ConceptIdentifier.of(name)
    }

    @BuilderData
    @SetProvidedFacetValue(
        conceptToModifyAlias = "item",
        facetToModify = CodegenSchema.ItemConcept.ItemNameFacet ::class,
    )
    fun getItemName(): String {
        return name
    }
}
