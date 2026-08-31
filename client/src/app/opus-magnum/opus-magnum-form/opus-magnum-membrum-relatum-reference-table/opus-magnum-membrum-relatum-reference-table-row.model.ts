import {FormControl} from "@angular/forms";
import {MembrumRelatumDisplayRow} from "@app/entitas-relata/membrum-relatum-display";
import {UUID} from "@app/shared/uuid";

/**
 * One row of the MembrumRelatum reference table.
 *
 * A reference is stored as a bare UUID in the form, which is meaningless to the user. The row
 * therefore carries the display attributes of the resolved MembrumRelatum flattened out, plus
 * a back-reference to the FormControl that actually holds the UUID.
 */
export interface OpusMagnumMembrumRelatumReferenceTableRow extends MembrumRelatumDisplayRow {
    formControl: FormControl<UUID>
}
