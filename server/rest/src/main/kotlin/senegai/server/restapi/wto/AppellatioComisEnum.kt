/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EnumWTORenderer"
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
package senegai.server.restapi.wto

/**
 * WTO (Web Transfer Object) enum mirroring the Angular `AppellatioComisEnum`.
 */
enum class AppellatioComisEnum {
    /* @tt{{{
        @foreach [ iteratorExpression="model.enumValues" loopVariable="enumValue" ]
        @replace-value-by-expression
            [ searchValue="VIR_HONORATUS" replaceByExpression="enumValue.screamingSnakeCase" ]
    }}}@ */
    VIR_HONORATUS,
    /* @tt{{{   @end-foreach  }}}@ */
    /* @tt{{{   @ignore-text  }}}@ */
    FEMINA_HONESTA,
    /* @tt{{{   @end-ignore-text  }}}@ */
}
