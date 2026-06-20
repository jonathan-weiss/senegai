import {FormControl} from "@angular/forms";
import {
    ArticulusInteriorFormPartFieldName
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-form-part/articulus-interior-form-part-field-name";


export interface ArticulusInteriorFormPartGroup {
    [ArticulusInteriorFormPartFieldName.description]: FormControl<string>,
    [ArticulusInteriorFormPartFieldName.year]: FormControl<number>,
}
