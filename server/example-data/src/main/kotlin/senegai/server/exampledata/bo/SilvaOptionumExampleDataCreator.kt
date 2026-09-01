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
import senegai.server.exampledata.DataContext
import senegai.server.exampledata.framework.datafaker.FakerHelper
import senegai.server.service.bo.SilvaOptionumBO
/* @tt{{{
    @foreach [ iteratorExpression="model.exampleDataGeneratorConfigs" loopVariable="exampleDataGeneratorConfig" ]
    @replace-value-by-expression
        [ searchValue="RandomString" replaceByExpression="exampleDataGeneratorConfig.generatorNamePrefix.pascalCase" ]
        [ searchValue="randomString" replaceByExpression="exampleDataGeneratorConfig.generatorNamePrefix.camelCase" ]
}}}@ */
import senegai.server.exampledata.framework.datagenerator.RandomStringDataGenerator
/* @tt{{{   @end-foreach  }}}@ */
/* @tt{{{
    @foreach [ iteratorExpression="model.referencedEntities" loopVariable="referencedEntity" ]
    @replace-value-by-expression
        [ searchValue="EntitasRelata" replaceByExpression="referencedEntity.entityName.pascalCase" ]
        [ searchValue="entitasrelata" replaceByExpression="referencedEntity.entityName.lowerCase" ]
}}}@ */
import senegai.server.exampledata.entitasrelata.EntitasRelataExampleDataFetcher
/* @tt{{{   @end-foreach  }}}@ */
/* @tt{{{   @ignore-text  }}}@ */
import senegai.server.exampledata.framework.datagenerator.RandomBooleanDataGenerator
import senegai.server.exampledata.framework.datagenerator.RandomNumberDataGenerator
import java.util.UUID
/* @tt{{{   @end-ignore-text  }}}@ */



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
    /* @tt{{{
        @foreach [ iteratorExpression="model.exampleDataGeneratorConfigs" loopVariable="exampleDataGeneratorConfig" ]
        @replace-value-by-expression
            [ searchValue="RandomString" replaceByExpression="exampleDataGeneratorConfig.generatorNamePrefix.pascalCase" ]
            [ searchValue="randomString" replaceByExpression="exampleDataGeneratorConfig.generatorNamePrefix.camelCase" ]
    }}}@ */
    private val randomStringDataGenerator: RandomStringDataGenerator,
    /* @tt{{{   @end-foreach  }}}@ */
    /* @tt{{{
        @foreach [ iteratorExpression="model.referencedEntities" loopVariable="referencedEntity" ]
        @replace-value-by-expression
            [ searchValue="EntitasRelata" replaceByExpression="referencedEntity.entityName.pascalCase" ]
            [ searchValue="entitasRelata" replaceByExpression="referencedEntity.entityName.camelCase" ]
            [ searchValue="entitasrelata" replaceByExpression="referencedEntity.entityName.lowerCase" ]
    }}}@ */
    private val entitasRelataExampleDataFetcher: EntitasRelataExampleDataFetcher,
    /* @tt{{{   @end-foreach  }}}@ */
    /* @tt{{{   @ignore-text  }}}@ */
    private val randomNumberDataGenerator: RandomNumberDataGenerator,
    private val randomBooleanDataGenerator: RandomBooleanDataGenerator,
    /* @tt{{{   @end-ignore-text  }}}@ */
) {

    fun create(dataContext: DataContext): SilvaOptionumBO = SilvaOptionumBO(
        /* @tt{{{
            @foreach [ iteratorExpression="model.builtInAttributes.filter { !it.isEntityReference }" loopVariable="builtInAttribute" ]
            @replace-value-by-expression
                [ searchValue="iteratioSimpliciumTextuum" replaceByExpression="builtInAttribute.attributeName.camelCase" ]
                [ searchValue="campusTextusObligatorius" replaceByExpression="builtInAttribute.attributeName.camelCase" ]
                [ searchValue="randomString" replaceByExpression="builtInAttribute.exampleDataGeneratorConfig.generatorNamePrefix.camelCase" ]
        }}}@ */
        /* @tt{{{   @if [ conditionExpression="builtInAttribute.isList"]  }}}@ */
        iteratioSimpliciumTextuum = randomStringDataGenerator.generateDataList(dataContext, size = FakerHelper.innerListRandomSize(dataContext)),
        /* @tt{{{   @else }}}@ */
        campusTextusObligatorius = randomStringDataGenerator.generateData(dataContext),
        /* @tt{{{   @end-if  }}}@ */
        /* @tt{{{   @end-foreach  }}}@ */
        /* @tt{{{
            @foreach [ iteratorExpression="model.attributesWithItemType" loopVariable="itemAttribute" ]
            @replace-value-by-expression
                [ searchValue="articulusInteriorSingularis" replaceByExpression="itemAttribute.attributeName.camelCase" ]
                [ searchValue="articulusInteriorIteratus" replaceByExpression="itemAttribute.attributeName.camelCase" ]
                [ searchValue="articulusInterior" replaceByExpression="itemAttribute.referencedItem.itemName.camelCase" ]
        }}}@ */
        /* @tt{{{   @if [ conditionExpression="itemAttribute.isList"]  }}}@ */
        articulusInteriorIteratus = articulusInteriorExampleDataCreator.createList(dataContext, FakerHelper.innerListRandomSize(dataContext)),
        /* @tt{{{   @else }}}@ */
        articulusInteriorSingularis = articulusInteriorExampleDataCreator.create(dataContext),
        /* @tt{{{   @end-if  }}}@ */

        /* @tt{{{   @end-foreach  }}}@ */
        /* @tt{{{
            @foreach [ iteratorExpression="model.attributesWithEnumType" loopVariable="enumAttribute" ]
            @replace-value-by-expression
                [ searchValue="appellatioOptionalisIteratus" replaceByExpression="enumAttribute.attributeName.camelCase" ]
                [ searchValue="appellatioComis" replaceByExpression="enumAttribute.enum.enumName.camelCase" ]
                [ searchValue="appellatio" replaceByExpression="enumAttribute.attributeName.camelCase" ]
        }}}@ */
        /* @tt{{{   @if [ conditionExpression="enumAttribute.isList"]  }}}@ */
        appellatioOptionalisIteratus = appellatioComisExampleDataCreator.createList(dataContext, FakerHelper.innerListRandomSize(dataContext)),
        /* @tt{{{   @else }}}@ */
        appellatio = appellatioComisExampleDataCreator.create(dataContext),
        /* @tt{{{   @end-if  }}}@ */
        /* @tt{{{   @end-foreach  }}}@ */
        /* @tt{{{
            @foreach [ iteratorExpression="model.attributesWithEntityReference" loopVariable="referenceAttribute" ]
            @replace-value-by-expression
                [ searchValue="relatioAdEntitatemOptionalisIteratus" replaceByExpression="referenceAttribute.attributeName.camelCase" ]
                [ searchValue="relatioAdEntitatemOptionalis" replaceByExpression="referenceAttribute.attributeName.camelCase" ]
                [ searchValue="entitasRelata" replaceByExpression="referenceAttribute.referencedEntity.entityName.camelCase" ]
        }}}@ */
        /* @tt{{{   @if [ conditionExpression="referenceAttribute.isList"]  }}}@ */
        relatioAdEntitatemOptionalisIteratus = entitasRelataExampleDataFetcher.fetchRandomKeysList(dataContext),
        /* @tt{{{   @else }}}@ */
        relatioAdEntitatemOptionalis = entitasRelataExampleDataFetcher.fetchRandomKey(dataContext),
        /* @tt{{{   @end-if  }}}@ */
        /* @tt{{{   @end-foreach  }}}@ */
        /* @tt{{{   @ignore-text  }}}@ */
        indexUnicus = UUID.randomUUID(),
        campusNumerorum = randomNumberDataGenerator.generateData(dataContext),
        campusTextusOptionalis = randomStringDataGenerator.generateData(dataContext),
        articulusInteriorSingularisOptionalis = articulusInteriorExampleDataCreator.create(dataContext),
        articulusInteriorOptionalisIteratus = articulusInteriorExampleDataCreator.createList(dataContext, FakerHelper.innerListRandomSize(dataContext)),
        campusDiei = null,
        campusBivalens = randomBooleanDataGenerator.generateData(dataContext),
        /* @tt{{{   @end-ignore-text  }}}@ */
    )

    fun createList(dataContext: DataContext, size: Int): List<SilvaOptionumBO> =
        List( size = size) { create(dataContext) }
}
