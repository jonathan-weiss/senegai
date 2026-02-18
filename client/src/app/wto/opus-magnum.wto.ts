import {AuthorWTO} from "@app/wto/author.wto";

/**
 * The OpusMagnum WTO (Web Transfer Object) class.
 */
export interface OpusMagnumWTO {
    id: string;
    title: string;
    author: AuthorWTO;
}
