import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {
    OpusMagnumFormPartFieldName
} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part-field-name";
import {AuthorFormPartGroup} from "@app/opus-magnum/opus-magnum-form/author-form-part/author-form-part-group";


export interface OpusMagnumFormPartGroup {
    [OpusMagnumFormPartFieldName.id]: FormControl<string>,
    [OpusMagnumFormPartFieldName.title]: FormControl<string>,
    [OpusMagnumFormPartFieldName.author]: FormGroup<AuthorFormPartGroup>,
}
