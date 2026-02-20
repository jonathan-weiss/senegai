/* @tt{{{
    @slbc

    @template-renderer [ templateRendererClassName="ItemWTOInterfaceRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="UiItemRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

    @template-model [
    modelClassName="UiItemModel"
    modelPackageName="senegai.codegen.renderer.model.ui"
    modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="Author" replaceByExpression="model.itemName.pascalCase" ]
        [ searchValue="author" replaceByExpression="model.itemName.camelCase" ]

    @modify-provided-filename-by-replacements

    @slac

}}}@ */

/* @tt{{{ @slbc  @ignore-text @slac }}}@ */
import {LibraryAwardWTO} from "@app/wto/library-award.wto";
import {GenderEnum} from "@app/wto/gender.enum";
/* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */

/**
 * The Author WTO (Web Transfer Object) class.
 */
export interface AuthorWTO {
    /* @tt{{{
        @foreach [ iteratorExpression="model.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="firstname" replaceByExpression="attribute.attributeName.camelCase" ]
            [ searchValue="string" replaceByExpression="attribute.typescriptAttributeType" ]

    }}}@  */
    firstname: string;
    /* @tt{{{
        @slbc
        @end-foreach
        @slac
    }}}@ */
    /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
    nickname: string | null;
    lastname: string;
    gender: GenderEnum;
    libraryAwardList: ReadonlyArray<LibraryAwardWTO>;
    birthday: Date | null;
    vegetarian: boolean;
    id: string;
    /* @tt{{{ @slbc  @end-ignore-text }}}@ */
}
