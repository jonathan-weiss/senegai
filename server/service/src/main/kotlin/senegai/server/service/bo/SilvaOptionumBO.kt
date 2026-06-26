/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="ItemBORenderer"
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

    @modify-provided-filename-by-replacements

}}}@ */
package senegai.server.service.bo

/* @tt{{{   @ignore-text  }}}@ */
import java.time.LocalDate
/* @tt{{{   @end-ignore-text  }}}@ */

/**
 * Business object for the [SilvaOptionumBO] item.
 */
data class SilvaOptionumBO(
    /* @tt{{{
        @foreach [ iteratorExpression="model.attributes" loopVariable="attribute" ]
        @replace-value-by-expression
            [ searchValue="indexUnicus" replaceByExpression="attribute.attributeName.camelCase" ]
            [ searchValue="String" replaceByExpression="attribute.kotlinAttributeType" ]
    }}}@ */
    val indexUnicus: String,
    /* @tt{{{   @end-foreach  }}}@ */
    /* @tt{{{   @ignore-text  }}}@ */
    val campusTextusObligatorius: String,
    val campusTextusOptionalis: String?,
    val appellatio: AppellatioComis,
    val articulusInteriorSingularis: ArticulusInteriorBO,
    val articulusInteriorIteratus: List<ArticulusInteriorBO>,
    val articulusInteriorSingularisOptionalis: ArticulusInteriorBO?,
    val articulusInteriorOptionalisIteratus: List<ArticulusInteriorBO>?,
    val appellatioOptionalisIteratus: List<AppellatioComis>?,
    val campusDiei: LocalDate?,
    val campusBivalens: Boolean,
    val campusNumerorum: Int,
    val iteratioSimpliciumTextuum: List<String>,
    /* @tt{{{   @end-ignore-text  }}}@ */
)
