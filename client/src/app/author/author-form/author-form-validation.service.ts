/* @tt{{{
    @slbc

    @template-renderer [ templateRendererClassName="ItemFormValidationServiceRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="ItemRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

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

import {Injectable} from '@angular/core';
import {Author} from "@app/author/author.model";
import {AbstractControl, FormArray, FormControl, FormGroup, ValidatorFn, Validators} from "@angular/forms";
import {FormUtil} from "@app/shared/form-controls/form.util";
import {AuthorFormFieldName} from "@app/author/author-form/author-form-field-name";
import {NamedValidator} from "@app/shared/form-controls/named-validator";
/* @tt{{{ @slbc  @ignore-text @slac }}}@ */
import {AuthorLibraryAward} from "@app/author/author-library-award.model";
import {
    AuthorLibraryAwardEditFormService
} from "@app/author/author-form/author-library-award-form-part/author-library-award-edit-form.service";
import {GenderEnum} from "@app/author/gender.enum";
/* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */


@Injectable({providedIn: 'root'})
export class AuthorFormValidationService {

    validatorFunctions(field: AuthorFormFieldName): Array<ValidatorFn> {
        return this.namedValidators(field).map(namedValidator => namedValidator.validatorFunction)
    }

    namedValidators(field: AuthorFormFieldName): ReadonlyArray<NamedValidator> {
        // TODO use mapped types https://www.typescriptlang.org/docs/handbook/2/mapped-types.html
        switch(field) {
            /* @tt{{{ @slbc
                @foreach [ iteratorExpression="model.attributes" loopVariable="attribute" ]

                @replace-value-by-expression
                    [ searchValue="firstname" replaceByExpression="attribute.attributeName" ]

            }}}@  */
            case AuthorFormFieldName.firstname: return [
                {
                    validatorName: "required",
                    validatorFunction: Validators.required,
                },
                {
                    validatorName: "minlength",
                    validatorFunction: Validators.minLength(2),
                },
            ]
            /* @tt{{{ @slbc @end-foreach @slac }}}@ */
            /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
            case AuthorFormFieldName.nickname: return [
                {
                    validatorName: "required",
                    validatorFunction: Validators.required,
                },
                {
                    validatorName: "minlength",
                    validatorFunction: Validators.minLength(2),
                },
            ]
            case AuthorFormFieldName.lastname: return [
                {
                    validatorName: "required",
                    validatorFunction: Validators.required,
                },
                {
                    validatorName: "minlength",
                    validatorFunction: Validators.minLength(2),
                },
            ]
            /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
            default: return []
        }
    };
} 
