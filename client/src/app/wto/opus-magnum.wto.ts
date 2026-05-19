import {SilvaOptionumWTO} from "@app/wto/silva-optionum.wto";

/**
 * The OpusMagnum WTO (Web Transfer Object) class.
 */
export interface OpusMagnumWTO {
    indexUnicus: string;
    title: string;
    silvaOptionum: SilvaOptionumWTO;
}
