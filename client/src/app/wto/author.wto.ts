/* @tt{{{
    #expand-comment [ direction="backward" strip="linebreak"]

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
        [ searchValue="Author" replaceByExpression="model.itemName.pascalCase" ]
        [ searchValue="author" replaceByExpression="model.itemName.camelCase" ]

    @modify-provided-filename-by-replacements

    #expand-comment [ direction="forward" strip="linebreak"]

}}}@ */

/* @tt{{{ #expand-comment [ direction="backward" strip="linebreak"]  @ignore-text #expand-comment [ direction="forward" strip="linebreak"] }}}@ */
import {LibraryAwardWTO} from "@app/wto/library-award.wto";
import {GenderEnum} from "@app/wto/gender.enum";
/* @tt{{{ #expand-comment [ direction="backward" strip="linebreak"]  @end-ignore-text #expand-comment [ direction="forward" strip="linebreak"] }}}@ */

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
        #expand-comment [ direction="backward" strip="linebreak"]
        @end-foreach
        #expand-comment [ direction="forward" strip="linebreak"]
    }}}@ */
    /* @tt{{{ #expand-comment [ direction="backward" strip="linebreak"]  @ignore-text #expand-comment [ direction="forward" strip="linebreak"] }}}@ */
    nickname: string | null;
    lastname: string;
    gender: GenderEnum;
    libraryAwardList: Array<LibraryAwardWTO>;
    birthday: Date | null;
    vegetarian: boolean;
    id: string;
    /* @tt{{{ #expand-comment [ direction="backward" strip="linebreak"]  @end-ignore-text }}}@ */
}
