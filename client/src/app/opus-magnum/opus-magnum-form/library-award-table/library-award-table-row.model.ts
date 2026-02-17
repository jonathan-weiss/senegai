import {FormGroup} from "@angular/forms";
import {
    LibraryAwardFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part-group";

export interface LibraryAwardTableRow {
    description: string
    year: number
    formGroup: FormGroup<LibraryAwardFormPartGroup>
}
