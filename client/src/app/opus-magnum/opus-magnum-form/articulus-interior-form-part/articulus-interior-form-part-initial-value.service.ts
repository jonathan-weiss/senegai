import {Injectable} from '@angular/core';


@Injectable({providedIn: 'root'})
export class ArticulusInteriorFormPartInitialValueService {

    articulusInteriorListScriptumTrivialeInitialValue(): string {
        return ''
    }

    articulusInteriorListNumerusStupidusInitialValue(): number {
        return 2025
    }
}
