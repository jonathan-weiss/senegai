import {SilvaOptionumWTO} from "@app/wto/silva-optionum.wto";

/**
 * The OpusMagnum WTO (Web Transfer Object) class.
 */
export interface OpusMagnumWTO {
    id: string;
    title: string;
    silvaOptionum: SilvaOptionumWTO;
}
