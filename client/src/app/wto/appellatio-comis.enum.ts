/* @tt{{{


    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EnumDefinitionTypescriptRenderer"
        templateRendererPackageName="senegai.codegen.renderer.angular"
        templateRendererInterfaceName="UiEnumRenderer"
        templateRendererInterfacePackageName="senegai.codegen.renderer.angular"
    ] [
        modelClassName="UiEnumModel"
        modelPackageName="senegai.codegen.renderer.model.ui"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="AppellatioComis" replaceByExpression="model.enumName.pascalCase" ]
        [ searchValue="appellatioComis" replaceByExpression="model.enumName.camelCase" ]
        [ searchValue="appellatio-comis" replaceByExpression="model.enumName.kebabCase" ]

    @modify-provided-filename-by-replacements


}}}@ */
export enum AppellatioComisEnum {
    /* @tt{{{
        @foreach [ iteratorExpression="model.enumValues" loopVariable="enumValue" ]

        @replace-value-by-expression
            [ searchValue="VIR_HONORATUS" replaceByExpression="enumValue.screamingSnakeCase" ]

    }}}@  */
    VIR_HONORATUS = 'VIR_HONORATUS',
    /* @tt{{{   @end-foreach  }}}@ */
    /* @tt{{{   @ignore-text  }}}@ */
    FEMINA_HONESTA = 'FEMINA_HONESTA',
    /* @tt{{{   @end-ignore-text  }}}@ */
}

export const AppellatioComisEnumValues: ReadonlyArray<AppellatioComisEnum> = [
    /* @tt{{{
        @foreach [ iteratorExpression="model.enumValues" loopVariable="enumValue" ]

        @replace-value-by-expression
            [ searchValue="VIR_HONORATUS" replaceByExpression="enumValue.screamingSnakeCase" ]

    }}}@  */
    AppellatioComisEnum.VIR_HONORATUS,
    /* @tt{{{   @end-foreach  }}}@ */
    /* @tt{{{   @ignore-text  }}}@ */
    AppellatioComisEnum.FEMINA_HONESTA,
    /* @tt{{{   @end-ignore-text  }}}@ */
]
