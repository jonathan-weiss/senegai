import {Injectable} from '@angular/core';
import {GenderEnum} from "@app/wto/gender.enum";


@Injectable({providedIn: 'root'})
export class OpusMagnumFormPartInitialValueService {
    idInitialValue(): string {
        return ''
    }

    firstnameInitialValue(): string {
        return ''
    }

    nicknameInitialValue(): string | null {
        return null
    }

    lastnameInitialValue(): string {
        return ''
    }
    birthdayInitialValue(): Date {
        return new Date();
    }
    vegetarianInitialValue(): boolean {
        return false;
    }
    genderInitialValue(): GenderEnum {
        return GenderEnum.FEMALE;
    }
}
