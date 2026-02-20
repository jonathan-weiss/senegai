import {FormArray, FormControl} from "@angular/forms";
import {
    LibraryAwardFormPartFieldName
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part-field-name";


export interface LibraryAwardFormPartGroup {
    [LibraryAwardFormPartFieldName.description]: FormControl<string>,
    [LibraryAwardFormPartFieldName.year]: FormControl<number>,
    [LibraryAwardFormPartFieldName.juryList]: FormArray<FormControl<string>>,
}
