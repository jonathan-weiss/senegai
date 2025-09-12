/* @tt{{{
    @slbc

    @template-renderer [ templateRendererClassName="ItemModelInterface" templateRendererPackageName="senegai.codegen.renderer.angular" ]

    @template-model [
    modelClassName="ItemModel"
    modelPackageName="senegai.codegen.renderer.model"
    modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="Author" replaceByExpression="model.itemName" ]
        [ searchValue="author" replaceByExpression="model.itemNameLowercase" ]

    @modify-provided-filename-by-replacements

    @slac

}}}@ */

/* @tt{{{ @slbc  @ignore-text @slac }}}@ */
import {AuthorLibraryAward} from "@app/author/author-library-award.model";
import {GenderEnum} from "@app/author/gender.enum";
/* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */

/**
 * The Author DTO (Data Transfer Object) class.
 */
export interface Author {
    id: number;
    /* @tt{{{
        @foreach [ iteratorExpression="model.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="firstname" replaceByExpression="attribute.attributeName" ]

    }}}@  */
    firstname: string;
    /* @tt{{{
        @slbc
        @end-foreach
        @ignore-text
        @slac
    }}}@ */
    nickname: string | null;
    lastname: string;
    gender: GenderEnum;
    libraryAwardList: ReadonlyArray<AuthorLibraryAward>;
    birthday: Date | null;
    vegetarian: boolean;
    /* @tt{{{ @slbc  @end-ignore-text }}}@ */
}
