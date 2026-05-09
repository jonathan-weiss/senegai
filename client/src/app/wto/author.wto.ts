/* @tt{{{
    @remove-blanks-and-linebreak-before-comment

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
        [ searchValue="Author" replaceByExpression="model.itemName.pascalCase" ]
        [ searchValue="author" replaceByExpression="model.itemName.camelCase" ]

    @modify-provided-filename-by-replacements

    @remove-blanks-and-linebreak-after-comment

}}}@ */

/* @tt{{{ @remove-blanks-and-linebreak-before-comment  @ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
import {LibraryAwardWTO} from "@app/wto/library-award.wto";
import {GenderEnum} from "@app/wto/gender.enum";
/* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */

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
        @remove-blanks-and-linebreak-before-comment
        @end-foreach
        @remove-blanks-and-linebreak-after-comment
    }}}@ */
    /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
    nickname: string | null;
    lastname: string;
    gender: GenderEnum;
    libraryAwardList: Array<LibraryAwardWTO>;
    birthday: Date | null;
    vegetarian: boolean;
    id: string;
    /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text }}}@ */
}
