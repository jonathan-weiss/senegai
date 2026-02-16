import {FormGroup} from "@angular/forms";
import {AuthorFormLibraryAwardListFormGroup} from "@app/author/author-form/author-form-field-name";

export interface AuthorLibraryAwardListTableRow {
    description: string
    year: number
    formGroup: FormGroup<AuthorFormLibraryAwardListFormGroup>
}
