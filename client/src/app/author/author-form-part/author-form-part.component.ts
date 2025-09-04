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
import {
    AuthorLibraryAwardsTableComponent
} from "@app/author/author-fields/author-library-awards-table/author-library-awards-table.component";
import {
    AuthorLibraryAwardFormPartComponent
} from "@app/author/author-items/author-library-award/author-library-award-form-part/author-library-award-form-part.component";
import {FieldWrapperComponent} from "@app/shared/form-controls/field-wrapper/field-wrapper.component";
import {MatDatepicker, MatDatepickerInput, MatDatepickerToggle} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {MatCheckbox} from "@angular/material/checkbox";

@Component({
    selector: 'app-author-form-part',
    templateUrl: './author-form-part.component.html',
    styleUrls: ['./author-form-part.component.scss'],
    standalone: true,
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
        AuthorLibraryAwardsTableComponent,
        AuthorLibraryAwardFormPartComponent,
        FieldWrapperComponent,
        MatDatepickerInput,
        MatDatepickerToggle,
        MatDatepicker,
        MatNativeDateModule,
        MatCheckbox,
    ],
})
export class AuthorFormPartComponent {
    @Input({ required: true }) authorForm!: FormGroup;

    authorLibraryAwardUnderEdit: FormGroup | undefined = undefined;

    get idControl(): FormControl {
        return FormUtil.requiredFormControl(this.authorForm, "id");
    }

    get nicknameIsNotNullControl(): FormControl {
        return FormUtil.requiredFormControl(this.authorForm, "nicknameIsNotNull");
    }

    get nicknameControl(): FormControl {
        return FormUtil.requiredFormControl(this.authorForm, "nickname");
    }

    get authorLibraryAwardFormArray(): FormArray {
        return FormUtil.requiredFormArray(this.authorForm, "libraryAwardList");
    }

    get firstnameControl(): FormControl {
        return FormUtil.requiredFormControl(this.authorForm, "firstname");
    }

    get lastnameControl(): FormControl {
        return FormUtil.requiredFormControl(this.authorForm, "lastname");
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

    onAuthorLibraryAwardFormGroupSelect(formGroup: FormGroup): void {
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

    hasError(controlName: string, errorName: string): boolean {
        return FormUtil.hasError(this.authorForm, controlName, errorName)
    }
}
