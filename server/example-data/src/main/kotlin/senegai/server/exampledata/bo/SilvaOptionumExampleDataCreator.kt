/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="ItemExampleDataCreatorRenderer"
        templateRendererPackageName="senegai.codegen.renderer.be"
        templateRendererInterfaceName="BeItemRenderer"
        templateRendererInterfacePackageName="senegai.codegen.renderer.be"
    ] [
        modelClassName="BeItemModel"
        modelPackageName="senegai.codegen.renderer.model.be"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="SilvaOptionum" replaceByExpression="model.itemName.pascalCase" ]
        [ searchValue="opusmagnum" replaceByExpression="model.entityName.lowerCase" ]

    @modify-provided-filepath-by-replacements

}}}@ */
package senegai.server.exampledata.bo

import org.springframework.stereotype.Component
import senegai.server.service.bo.SilvaOptionumBO

/**
 * Creates example data for the business object [SilvaOptionumBO].
 *
 * Delegates the creation of nested objects to the dedicated example data creators of the
 * respective business objects.
 */
@Component
class SilvaOptionumExampleDataCreator(
    /* @tt{{{
        @foreach [ iteratorExpression="model.directlyNestedItems" loopVariable="nestedItem" ]
        @replace-value-by-expression
            [ searchValue="ArticulusInterior" replaceByExpression="nestedItem.itemName.pascalCase" ]
            [ searchValue="articulusInterior" replaceByExpression="nestedItem.itemName.camelCase" ]
    }}}@ */
    private val articulusInteriorExampleDataCreator: ArticulusInteriorExampleDataCreator,
    /* @tt{{{   @end-foreach  }}}@ */
    /* @tt{{{
        @foreach [ iteratorExpression="model.usedEnums" loopVariable="usedEnum" ]
        @replace-value-by-expression
            [ searchValue="AppellatioComis" replaceByExpression="usedEnum.enumName.pascalCase" ]
            [ searchValue="appellatioComis" replaceByExpression="usedEnum.enumName.camelCase" ]
    }}}@ */
    private val appellatioComisExampleDataCreator: AppellatioComisExampleDataCreator,
    /* @tt{{{   @end-foreach  }}}@ */
) {

    /** A single fully populated example aggregate. */
    fun create(): SilvaOptionumBO = SilvaOptionumBO(
        /* @tt{{{
            @foreach [ iteratorExpression="model.builtInAttributes" loopVariable="builtInAttribute" ]
            @replace-value-by-expression
                [ searchValue="campusNumerorum" replaceByExpression="builtInAttribute.attributeName.camelCase" ]
                [ searchValue="42" replaceByExpression="builtInAttribute.kotlinExampleValue" ]
        }}}@ */
        campusNumerorum = 42,
        /* @tt{{{   @end-foreach  }}}@ */
        /* @tt{{{
            @foreach [ iteratorExpression="model.attributesWithItemType" loopVariable="itemAttribute" ]
            @replace-value-by-expression
                [ searchValue="articulusInteriorSingularis" replaceByExpression="itemAttribute.attributeName.camelCase" ]
                [ searchValue="articulusInterior" replaceByExpression="itemAttribute.referencedItem.itemName.camelCase" ]
                [ searchValue="create()" replaceByExpression="itemAttribute.exampleDataCreatorCall" ]
        }}}@ */
        articulusInteriorSingularis = articulusInteriorExampleDataCreator.create(),
        /* @tt{{{   @end-foreach  }}}@ */
        /* @tt{{{
            @foreach [ iteratorExpression="model.attributesWithEnumType" loopVariable="enumAttribute" ]
            @replace-value-by-expression
                [ searchValue="appellatioComis" replaceByExpression="enumAttribute.enum.enumName.camelCase" ]
                [ searchValue="appellatio" replaceByExpression="enumAttribute.attributeName.camelCase" ]
                [ searchValue="create()" replaceByExpression="enumAttribute.exampleDataCreatorCall" ]
        }}}@ */
        appellatio = appellatioComisExampleDataCreator.create(),
        /* @tt{{{   @end-foreach  }}}@ */
        /* @tt{{{   @ignore-text  }}}@ */
        indexUnicus = "exemplum",
        campusTextusObligatorius = "exemplum",
        campusTextusOptionalis = "exemplum",
        articulusInteriorIteratus = articulusInteriorExampleDataCreator.createList(),
        articulusInteriorSingularisOptionalis = articulusInteriorExampleDataCreator.create(),
        articulusInteriorOptionalisIteratus = articulusInteriorExampleDataCreator.createList(),
        appellatioOptionalisIteratus = appellatioComisExampleDataCreator.createList(),
        campusDiei = null,
        campusBivalens = true,
        iteratioSimpliciumTextuum = listOf("exemplum"),
        /* @tt{{{   @end-ignore-text  }}}@ */
    )

    /** A list of distinct example aggregates. */
    fun createList(): List<SilvaOptionumBO> = listOf(
        create(),
        create(),
        create(),
    )
}
