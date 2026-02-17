import {LibraryAwardWTO} from "@app/wto/library-award.wto";
import {GenderEnum} from "@app/wto/gender.enum";

/**
 * The OpusMagnum WTO (Web Transfer Object) class.
 */
export interface OpusMagnumWTO {
    firstname: string;
    nickname: string | null;
    lastname: string;
    gender: GenderEnum;
    libraryAwardList: ReadonlyArray<LibraryAwardWTO>;
    birthday: Date | null;
    vegetarian: boolean;
    id: string;
}
