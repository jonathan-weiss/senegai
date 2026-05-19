import {Injectable} from '@angular/core';


@Injectable({providedIn: 'root'})
export class ArticulusInteriorFormPartInitialValueService {

    articulusInteriorListDescriptionInitialValue(): string {
        return ''
    }

    articulusInteriorListYearInitialValue(): number {
        return 2025
    }

    articulusInteriorListJuryListInitialValue(): string {
        return ''
    }
}
