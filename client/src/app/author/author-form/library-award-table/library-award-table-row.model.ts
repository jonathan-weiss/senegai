import {FormGroup} from "@angular/forms";
import {
    LibraryAwardFormPartGroup
} from "@app/author/author-form/author-library-award-form-part/library-award-form-part-group";

export interface LibraryAwardTableRow {
    description: string
    year: number
    formGroup: FormGroup<LibraryAwardFormPartGroup>
}
