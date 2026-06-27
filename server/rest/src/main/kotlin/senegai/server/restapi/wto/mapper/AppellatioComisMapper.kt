/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EnumMapperRenderer"
        templateRendererPackageName="senegai.codegen.renderer.be"
        templateRendererInterfaceName="BeEnumRenderer"
        templateRendererInterfacePackageName="senegai.codegen.renderer.be"
    ] [
        modelClassName="BeEnumModel"
        modelPackageName="senegai.codegen.renderer.model.be"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="AppellatioComis" replaceByExpression="model.enumName.pascalCase" ]

    @modify-provided-filepath-by-replacements

}}}@ */
package senegai.server.restapi.wto.mapper

import senegai.server.restapi.wto.AppellatioComisEnum
import senegai.server.service.bo.AppellatioComis

/**
 * Maps between the WTOs (transport layer) and BOs (business layer).
 */
object AppellatioComisMapper {

    fun AppellatioComisEnum.toBo() = when (this) {
        /* @tt{{{
            @foreach [ iteratorExpression="model.enumValues" loopVariable="enumValue" ]
            @replace-value-by-expression
                [ searchValue="VIR_HONORATUS" replaceByExpression="enumValue.screamingSnakeCase" ]
        }}}@ */
        AppellatioComisEnum.VIR_HONORATUS -> AppellatioComis.VIR_HONORATUS
        /* @tt{{{   @end-foreach  }}}@ */
        /* @tt{{{   @ignore-text  }}}@ */
        AppellatioComisEnum.FEMINA_HONESTA -> AppellatioComis.FEMINA_HONESTA
        /* @tt{{{   @end-ignore-text  }}}@ */
    }

    fun AppellatioComis.toWto() = when (this) {
        /* @tt{{{
            @foreach [ iteratorExpression="model.enumValues" loopVariable="enumValue" ]
            @replace-value-by-expression
                [ searchValue="VIR_HONORATUS" replaceByExpression="enumValue.screamingSnakeCase" ]
        }}}@ */
        AppellatioComis.VIR_HONORATUS -> AppellatioComisEnum.VIR_HONORATUS
        /* @tt{{{   @end-foreach  }}}@ */
        /* @tt{{{   @ignore-text  }}}@ */
        AppellatioComis.FEMINA_HONESTA -> AppellatioComisEnum.FEMINA_HONESTA
        /* @tt{{{   @end-ignore-text  }}}@ */
    }
}
