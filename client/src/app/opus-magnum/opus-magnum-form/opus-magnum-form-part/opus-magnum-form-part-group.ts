import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {OpusMagnumFormPartFieldName} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part-field-name";
import {GenderEnum} from "@app/wto/gender.enum";
import {
    LibraryAwardFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part-group";


export interface OpusMagnumFormPartGroup {
    [OpusMagnumFormPartFieldName.firstname]: FormControl<string>,
    [OpusMagnumFormPartFieldName.nicknameIsNotNull]: FormControl<boolean>,
    [OpusMagnumFormPartFieldName.nickname]: FormControl<string | null>,
    [OpusMagnumFormPartFieldName.lastname]: FormControl<string>,
    [OpusMagnumFormPartFieldName.libraryAwardList]: FormArray<FormGroup<LibraryAwardFormPartGroup>>,
    [OpusMagnumFormPartFieldName.birthdayIsNotNull]: FormControl<boolean>,
    [OpusMagnumFormPartFieldName.birthday]: FormControl<Date | null>,
    [OpusMagnumFormPartFieldName.vegetarian]: FormControl<boolean>,
    [OpusMagnumFormPartFieldName.gender]: FormControl<GenderEnum>,
    [OpusMagnumFormPartFieldName.id]: FormControl<string>,
}
