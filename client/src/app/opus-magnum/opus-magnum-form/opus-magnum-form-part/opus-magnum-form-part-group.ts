import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {AuthorFormPartFieldName} from "@app/opus-magnum/opus-magnum-form/author-form-part/author-form-part-field-name";
import {GenderEnum} from "@app/wto/gender.enum";
import {
    LibraryAwardFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part-group";


export interface OpusMagnumFormPartGroup {
    [AuthorFormPartFieldName.firstname]: FormControl<string>,
    [AuthorFormPartFieldName.nicknameIsNotNull]: FormControl<boolean>,
    [AuthorFormPartFieldName.nickname]: FormControl<string | null>,
    [AuthorFormPartFieldName.lastname]: FormControl<string>,
    [AuthorFormPartFieldName.libraryAwardList]: FormArray<FormGroup<LibraryAwardFormPartGroup>>,
    [AuthorFormPartFieldName.birthdayIsNotNull]: FormControl<boolean>,
    [AuthorFormPartFieldName.birthday]: FormControl<Date | null>,
    [AuthorFormPartFieldName.vegetarian]: FormControl<boolean>,
    [AuthorFormPartFieldName.gender]: FormControl<GenderEnum>,
    [AuthorFormPartFieldName.id]: FormControl<string>,
}
