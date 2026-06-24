/* @tt{{{
    

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="ItemWTOInterfaceRenderer"
        templateRendererPackageName="senegai.codegen.renderer.angular"
        templateRendererInterfaceName="UiItemRenderer"
        templateRendererInterfacePackageName="senegai.codegen.renderer.angular"
    ] [
        modelClassName="UiItemModel"
        modelPackageName="senegai.codegen.renderer.model.ui"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="silva-optionum" replaceByExpression="model.itemName.kebabCase" ]
        [ searchValue="SilvaOptionum" replaceByExpression="model.itemName.pascalCase" ]
        [ searchValue="silvaOptionum" replaceByExpression="model.itemName.camelCase" ]

    @modify-provided-filename-by-replacements

    

}}}@ */

/* @tt{{{
    @foreach [ iteratorExpression="model.directlyNestedItems" loopVariable="nestedItem" ]

    @replace-value-by-expression
        [ searchValue="ArticulusInterior" replaceByExpression="nestedItem.itemName.pascalCase" ]
        [ searchValue="articulus-interior" replaceByExpression="nestedItem.itemName.kebabCase" ]
}}}@  */
import {ArticulusInteriorWTO} from "@app/wto/articulus-interior.wto";
/* @tt{{{   @end-foreach  }}}@ */
/* @tt{{{
    @foreach [ iteratorExpression="model.usedEnums" loopVariable="usedEnum" ]

    @replace-value-by-expression
        [ searchValue="AppellatioComis" replaceByExpression="usedEnum.enumName.pascalCase" ]
        [ searchValue="appellatio-comis" replaceByExpression="usedEnum.enumName.kebabCase" ]
}}}@  */
import {AppellatioComisEnum} from "@app/wto/appellatio-comis.enum";
/* @tt{{{   @end-foreach  }}}@ */

/**
 * The Silva Optionum WTO (Web Transfer Object) class.
 */
export interface SilvaOptionumWTO {
    /* @tt{{{
        @foreach [ iteratorExpression="model.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="campusTextusObligatorius" replaceByExpression="attribute.attributeName.camelCase" ]
            [ searchValue="string" replaceByExpression="attribute.typescriptAttributeType" ]

    }}}@  */
    campusTextusObligatorius: string;
    /* @tt{{{
        
        @end-foreach
        
    }}}@ */
    /* @tt{{{   @ignore-text  }}}@ */
    campusTextusOptionalis: string | null;
    appellatio: AppellatioComisEnum;
    articulusInteriorSingularis: ArticulusInteriorWTO;
    articulusInteriorIteratus: Array<ArticulusInteriorWTO>;
    articulusInteriorSingularisOptionalis: ArticulusInteriorWTO | null;
    articulusInteriorOptionalisIteratus: Array<ArticulusInteriorWTO> | null;
    appellatioOptionalisIteratus: Array<AppellatioComisEnum> | null;
    campusDiei: Date | null;
    campusBivalens: boolean;
    campusNumerorum: number;
    indexUnicus: string;
    iteratioSimpliciumTextuum: Array<string>;
    /* @tt{{{   @end-ignore-text }}}@ */
}
