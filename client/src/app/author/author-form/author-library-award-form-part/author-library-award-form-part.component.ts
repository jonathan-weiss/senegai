/* @tt{{{

    @slbc

    @template-renderer [ templateRendererClassName="ItemEditFormComponentTypescript" templateRendererPackageName="senegai.codegen.renderer.angular" ]

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
import {AbstractControl, FormArray, FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
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
import {FieldWrapperComponent} from "@app/shared/form-controls/field-wrapper/field-wrapper.component";
import {FormUtil} from "@app/shared/form-controls/form.util";
import {
    AuthorLibraryAwardTableComponent
} from "@app/author/author-form/author-library-award-table/author-library-award-table.component";
import {
    AuthorLibraryAwardJuryTableComponent
} from "@app/author/author-form/author-library-award-jury-table/author-library-award-jury-table.component";

@Component({
    selector: 'app-author-library-award-form-part',
    templateUrl: './author-library-award-form-part.component.html',
    styleUrls: ['./author-library-award-form-part.component.scss'],
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
        AuthorLibraryAwardJuryTableComponent,
    ]
})
export class AuthorLibraryAwardFormPartComponent {
    @Input({ required: true}) authorLibraryAwardForm!: FormGroup;

    get descriptionFormControl(): FormControl {
        return FormUtil.requiredFormControl(this.authorLibraryAwardForm, "description");
    }

    get yearFormControl(): FormControl {
        return FormUtil.requiredFormControl(this.authorLibraryAwardForm, "year");
    }

    get juryListFormArray(): FormArray {
        return FormUtil.requiredFormArray(this.authorLibraryAwardForm, "juryList");
    }

    hasError(controlName: string, errorName: string): boolean {
        return FormUtil.hasError(this.authorLibraryAwardForm, controlName, errorName)
    }
}
