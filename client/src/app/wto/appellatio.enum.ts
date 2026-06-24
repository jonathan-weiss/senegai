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
        [ searchValue="Appellatio" replaceByExpression="model.enumName.pascalCase" ]
        [ searchValue="appellatio" replaceByExpression="model.enumName.camelCase" ]

    @modify-provided-filename-by-replacements


}}}@ */
export enum AppellatioEnum {
    /* @tt{{{
        @foreach [ iteratorExpression="model.enumValues" loopVariable="enumValue" ]

        @replace-value-by-expression
            [ searchValue="SENIOR" replaceByExpression="enumValue.screamingSnakeCase" ]

    }}}@  */
    SENIOR = 'SENIOR',
    /* @tt{{{   @end-foreach  }}}@ */
    /* @tt{{{   @ignore-text  }}}@ */
    MATRONA = 'MATRONA',
    /* @tt{{{   @end-ignore-text  }}}@ */
}

export const AppellatioEnumValues: ReadonlyArray<AppellatioEnum> = [
    /* @tt{{{
        @foreach [ iteratorExpression="model.enumValues" loopVariable="enumValue" ]

        @replace-value-by-expression
            [ searchValue="SENIOR" replaceByExpression="enumValue.screamingSnakeCase" ]

    }}}@  */
    AppellatioEnum.SENIOR,
    /* @tt{{{   @end-foreach  }}}@ */
    /* @tt{{{   @ignore-text  }}}@ */
    AppellatioEnum.MATRONA,
    /* @tt{{{   @end-ignore-text  }}}@ */
]
