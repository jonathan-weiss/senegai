import {FormArray, FormControl} from "@angular/forms";
import {
    LibraryAwardFormPartFieldName
} from "@app/author/author-form/author-library-award-form-part/library-award-form-part-field-name";


export interface LibraryAwardFormPartGroup {
    [LibraryAwardFormPartFieldName.libraryAwardListDescription]: FormControl<string>,
    [LibraryAwardFormPartFieldName.libraryAwardListYear]: FormControl<number>,
    [LibraryAwardFormPartFieldName.libraryAwardListJuryList]: FormArray<FormControl<string>>,
}
