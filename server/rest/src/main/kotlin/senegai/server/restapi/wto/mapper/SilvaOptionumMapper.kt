/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="ItemMapperRenderer"
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

    @modify-provided-filepath-by-replacements

}}}@ */
package senegai.server.restapi.wto.mapper

import senegai.server.restapi.wto.SilvaOptionumWTO
/* @tt{{{
    @foreach [ iteratorExpression="model.usedEnums" loopVariable="usedEnum" ]
    @replace-value-by-expression
        [ searchValue="AppellatioComis" replaceByExpression="usedEnum.enumName.pascalCase" ]
}}}@ */
import senegai.server.restapi.wto.mapper.AppellatioComisMapper.toBo
import senegai.server.restapi.wto.mapper.AppellatioComisMapper.toWto
/* @tt{{{   @end-foreach  }}}@ */
/* @tt{{{
    @foreach [ iteratorExpression="model.directlyNestedItems" loopVariable="nestedItem" ]
    @replace-value-by-expression
        [ searchValue="ArticulusInterior" replaceByExpression="nestedItem.itemName.pascalCase" ]
}}}@ */
import senegai.server.restapi.wto.mapper.ArticulusInteriorMapper.toBo
import senegai.server.restapi.wto.mapper.ArticulusInteriorMapper.toWto
/* @tt{{{   @end-foreach  }}}@ */
import senegai.server.service.bo.SilvaOptionumBO

/**
 * Maps between the WTOs (transport layer) and BOs (business layer).
 */
object SilvaOptionumMapper {

    fun SilvaOptionumWTO.toBo(): SilvaOptionumBO = SilvaOptionumBO(
        /* @tt{{{
            @foreach [ iteratorExpression="model.attributes" loopVariable="attribute" ]
            @replace-value-by-expression
                [ searchValue="appellatio" replaceByExpression="attribute.attributeName.camelCase" ]
                [ searchValue=".toBo()" replaceByExpression="attribute.toBoMappingSuffix" ]
        }}}@ */
        appellatio = appellatio.toBo(),
        /* @tt{{{   @end-foreach  }}}@ */
        /* @tt{{{   @ignore-text  }}}@ */
        indexUnicus = indexUnicus,
        campusTextusObligatorius = campusTextusObligatorius,
        campusTextusOptionalis = campusTextusOptionalis,
        articulusInteriorSingularis = articulusInteriorSingularis.toBo(),
        articulusInteriorIteratus = articulusInteriorIteratus.map { it.toBo() },
        articulusInteriorSingularisOptionalis = articulusInteriorSingularisOptionalis?.toBo(),
        articulusInteriorOptionalisIteratus = articulusInteriorOptionalisIteratus?.map { it.toBo() },
        appellatioOptionalisIteratus = appellatioOptionalisIteratus?.map { it.toBo() },
        campusDiei = campusDiei,
        campusBivalens = campusBivalens,
        campusNumerorum = campusNumerorum,
        iteratioSimpliciumTextuum = iteratioSimpliciumTextuum,
        /* @tt{{{   @end-ignore-text  }}}@ */
    )

    fun SilvaOptionumBO.toWto(): SilvaOptionumWTO = SilvaOptionumWTO(
        /* @tt{{{
            @foreach [ iteratorExpression="model.attributes" loopVariable="attribute" ]
            @replace-value-by-expression
                [ searchValue="appellatio" replaceByExpression="attribute.attributeName.camelCase" ]
                [ searchValue=".toWto()" replaceByExpression="attribute.toWtoMappingSuffix" ]
        }}}@ */
        appellatio = appellatio.toWto(),
        /* @tt{{{   @end-foreach  }}}@ */
        /* @tt{{{   @ignore-text  }}}@ */
        indexUnicus = indexUnicus,
        campusTextusObligatorius = campusTextusObligatorius,
        campusTextusOptionalis = campusTextusOptionalis,
        articulusInteriorSingularis = articulusInteriorSingularis.toWto(),
        articulusInteriorIteratus = articulusInteriorIteratus.map { it.toWto() },
        articulusInteriorSingularisOptionalis = articulusInteriorSingularisOptionalis?.toWto(),
        articulusInteriorOptionalisIteratus = articulusInteriorOptionalisIteratus?.map { it.toWto() },
        appellatioOptionalisIteratus = appellatioOptionalisIteratus?.map { it.toWto() },
        campusDiei = campusDiei,
        campusBivalens = campusBivalens,
        campusNumerorum = campusNumerorum,
        iteratioSimpliciumTextuum = iteratioSimpliciumTextuum,
        /* @tt{{{   @end-ignore-text  }}}@ */
    )
}
