import {Injectable} from '@angular/core';


@Injectable({providedIn: 'root'})
export class LibraryAwardFormPartInitialValueService {

    libraryAwardListDescriptionInitialValue(): string {
        return ''
    }

    libraryAwardListYearInitialValue(): number {
        return 2025
    }

    libraryAwardListJuryListInitialValue(): string {
        return ''
    }
}
