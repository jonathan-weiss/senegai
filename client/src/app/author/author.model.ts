/* @tt{{{
    @slbc

    @template-renderer [ templateRendererClassName="ItemModelInterfaceRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="ItemRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

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
    /* @tt{{{
        @foreach [ iteratorExpression="model.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="firstname" replaceByExpression="attribute.attributeName" ]
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
    libraryAwardList: ReadonlyArray<AuthorLibraryAward>;
    birthday: Date | null;
    vegetarian: boolean;
    id: string;
    /* @tt{{{ @slbc  @end-ignore-text }}}@ */
}
