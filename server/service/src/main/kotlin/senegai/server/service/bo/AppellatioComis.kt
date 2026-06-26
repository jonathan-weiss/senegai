/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EnumBORenderer"
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

    @modify-provided-filename-by-replacements

}}}@ */
package senegai.server.service.bo

/**
 * Business enum [AppellatioComis]. Pure business representation,
 * independent of the WTO layer used for transport.
 */
enum class AppellatioComis {
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
