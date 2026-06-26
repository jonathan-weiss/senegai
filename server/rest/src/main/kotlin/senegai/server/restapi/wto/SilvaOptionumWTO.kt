/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="ItemWTORenderer"
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
package senegai.server.restapi.wto

/* @tt{{{   @ignore-text  }}}@ */
import java.time.LocalDate
/* @tt{{{   @end-ignore-text  }}}@ */

/**
 * Root WTO (Web Transfer Object), mirroring the Angular `SilvaOptionumWTO` interface
 * field by field so it serializes 1:1 for the client.
 */
data class SilvaOptionumWTO(
    /* @tt{{{
        @foreach [ iteratorExpression="model.attributes" loopVariable="attribute" ]
        @replace-value-by-expression
            [ searchValue="indexUnicus" replaceByExpression="attribute.attributeName.camelCase" ]
            [ searchValue="String" replaceByExpression="attribute.wtoAttributeType" ]
    }}}@ */
    val indexUnicus: String,
    /* @tt{{{   @end-foreach  }}}@ */
    /* @tt{{{   @ignore-text  }}}@ */
    val campusTextusObligatorius: String,
    val campusTextusOptionalis: String?,
    val appellatio: AppellatioComisEnum,
    val articulusInteriorSingularis: ArticulusInteriorWTO,
    val articulusInteriorIteratus: List<ArticulusInteriorWTO>,
    val articulusInteriorSingularisOptionalis: ArticulusInteriorWTO?,
    val articulusInteriorOptionalisIteratus: List<ArticulusInteriorWTO>?,
    val appellatioOptionalisIteratus: List<AppellatioComisEnum>?,
    val campusDiei: LocalDate?,
    val campusBivalens: Boolean,
    val campusNumerorum: Int,
    val iteratioSimpliciumTextuum: List<String>,
    /* @tt{{{   @end-ignore-text  }}}@ */
)
