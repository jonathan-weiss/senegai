import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {
    OpusMagnumFormPartFieldName
} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part-field-name";
import {SilvaOptionumFormPartGroup} from "@app/opus-magnum/opus-magnum-form/silva-optionum-form-part/silva-optionum-form-part-group";


export interface OpusMagnumFormPartGroup {
    [OpusMagnumFormPartFieldName.indexUnicus]: FormControl<string>,
    [OpusMagnumFormPartFieldName.title]: FormControl<string>,
    [OpusMagnumFormPartFieldName.silvaOptionum]: FormGroup<SilvaOptionumFormPartGroup>,
}
