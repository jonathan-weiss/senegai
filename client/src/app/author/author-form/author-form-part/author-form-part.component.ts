/* @tt{{{

    @slbc

    @template-renderer [ templateRendererClassName="ItemFormPartComponentTypescriptRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="ItemRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

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
import {Component, Input} from '@angular/core';
import {FormArray, FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {MatButtonModule} from "@angular/material/button";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatTableModule} from "@angular/material/table";
import {MatCardModule} from "@angular/material/card";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatIconModule} from "@angular/material/icon";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatListModule} from "@angular/material/list";
import {MatDialogModule} from "@angular/material/dialog";
import {FormUtil} from "@app/shared/form-controls/form.util";
import {FieldWrapperComponent} from "@app/shared/form-controls/field-wrapper/field-wrapper.component";
import {MatDatepicker, MatDatepickerInput, MatDatepickerToggle} from "@angular/material/datepicker";
import {MatNativeDateModule, MatOption} from "@angular/material/core";
import {MatCheckbox} from "@angular/material/checkbox";
import {MatSelect} from "@angular/material/select";
/* @tt{{{ @slbc  @ignore-text @slac }}}@ */

import {
    AuthorLibraryAwardTableComponent
} from "@app/author/author-form/author-library-award-table/author-library-award-table.component";
import {
    AuthorLibraryAwardFormPartComponent
} from "@app/author/author-form/author-library-award-form-part/author-library-award-form-part.component";
import {GenderEnumValues} from "@app/author/gender.enum";
import {GenderI18nComponent} from "@app/author/gender-i18n/gender-i18n.component";
/* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */

@Component({
    selector: 'app-author-form-part',
    templateUrl: './author-form-part.component.html',
    styleUrls: ['./author-form-part.component.scss'],
    imports: [
        ReactiveFormsModule,
        MatButtonModule,
        MatToolbarModule,
        MatTableModule,
        MatCardModule,
        MatFormFieldModule,
        MatInputModule,
        MatIconModule,
        MatExpansionModule,
        MatSidenavModule,
        MatListModule,
        MatDialogModule,
        FieldWrapperComponent,
        /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
        MatDatepickerInput,
        MatDatepickerToggle,
        MatDatepicker,
        MatNativeDateModule,
        MatCheckbox,
        MatSelect,
        MatOption,
        /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
        /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
        AuthorLibraryAwardTableComponent,
        AuthorLibraryAwardFormPartComponent,
        GenderI18nComponent,
        /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
    ]
})
export class AuthorFormPartComponent {
    @Input({ required: true }) authorForm!: FormGroup;

    /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
    authorLibraryAwardUnderEdit: FormGroup | undefined = undefined;
    /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */

    get idControl(): FormControl {
        return FormUtil.requiredFormControl(this.authorForm, "id");
    }

    /* @tt{{{ @slbc
        @foreach [ iteratorExpression="model.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="firstname" replaceByExpression="attribute.attributeName" ]

        @slac
    }}}@  */
    get firstnameControl(): FormControl {
        return FormUtil.requiredFormControl(this.authorForm, "firstname");
    }
    /* @tt{{{ @slbc @end-foreach @slac }}}@ */
    /* @tt{{{ @slbc  @ignore-text @slac }}}@ */

    get nicknameIsNotNullControl(): FormControl {
        return FormUtil.requiredFormControl(this.authorForm, "nicknameIsNotNull");
    }

    get nicknameControl(): FormControl {
        return FormUtil.requiredFormControl(this.authorForm, "nickname");
    }

    get lastnameControl(): FormControl {
        return FormUtil.requiredFormControl(this.authorForm, "lastname");
    }

    get authorLibraryAwardFormArray(): FormArray {
        return FormUtil.requiredFormArray(this.authorForm, "libraryAwardList");
    }

    get birthdayIsNotNullControl(): FormControl {
        return FormUtil.requiredFormControl(this.authorForm, "birthdayIsNotNull");
    }

    get birthdayControl(): FormControl {
        return FormUtil.requiredFormControl(this.authorForm, "birthday");
    }

    get vegetarianControl(): FormControl {
        return FormUtil.requiredFormControl(this.authorForm, "vegetarian");
    }

    get genderControl(): FormControl {
        return FormUtil.requiredFormControl(this.authorForm, "gender");
    }

    protected genderList = GenderEnumValues

    onAuthorLibraryAwardFormGroupEdit(formGroup: FormGroup): void {
        this.authorLibraryAwardUnderEdit = formGroup;
    }

    onAuthorLibraryAwardFormGroupDelete(formGroup: FormGroup): void {
        if(this.authorLibraryAwardUnderEdit == formGroup) {
            this.authorLibraryAwardUnderEdit = undefined
        }
        FormUtil.removeControl(this.authorLibraryAwardFormArray, formGroup)
    }

    closeAuthorLibraryAwardUnderEdit(): void {
        this.authorLibraryAwardUnderEdit = undefined;
    }
    /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */

    hasError(controlName: string, errorName: string): boolean {
        return FormUtil.hasError(this.authorForm, controlName, errorName)
    }
}
