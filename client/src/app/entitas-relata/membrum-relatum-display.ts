import {UUID} from "@app/shared/uuid";
import {MembrumRelatumWTO} from "@app/wto/membrum-relatum.wto";

/**
 * One reference to an EntitasRelata, ready to be rendered: the display attributes of the
 * resolved MembrumRelatum, already filled with the fallback where the reference could not be
 * resolved.
 *
 * Shared by every place that shows such references, no matter whether the form holds a single
 * reference or a whole list of them.
 */
export interface MembrumRelatumDisplayRow {
    clavisPrimaria: UUID
    descriptioExDistanti: string
}

/**
 * The display attributes of a MembrumRelatum: the attributes that identify an instance for a
 * human reader. A reference to an EntitasRelata is stored as a bare UUID, which tells the user
 * nothing, so every place that shows such a reference resolves it to the whole
 * MembrumRelatumWTO and renders these attributes instead of the UUID alone.
 */
export const MEMBRUM_RELATUM_DISPLAY_ATTRIBUTE_NAMES: ReadonlyArray<keyof MembrumRelatumDisplayRow> = [
    'clavisPrimaria',
    'descriptioExDistanti',
];

/** Shown for a display attribute whose reference could not be resolved. */
export const MEMBRUM_RELATUM_UNRESOLVED_DISPLAY_VALUE = '—';

/** Flattens the display attributes of a (possibly unresolved) reference into one row. */
export function membrumRelatumDisplayRow(
    clavisPrimaria: UUID,
    membrumRelatum: MembrumRelatumWTO | undefined,
): MembrumRelatumDisplayRow {
    return {
        clavisPrimaria: clavisPrimaria,
        descriptioExDistanti: membrumRelatum?.descriptioExDistanti ?? MEMBRUM_RELATUM_UNRESOLVED_DISPLAY_VALUE,
    }
}

/**
 * Joins the display attributes of one reference into a single line, for the places that have
 * room for one line only (the typeahead suggestions and the search field that shows the picked
 * entry).
 */
export function membrumRelatumDisplayRowLabel(displayRow: MembrumRelatumDisplayRow): string {
    return MEMBRUM_RELATUM_DISPLAY_ATTRIBUTE_NAMES
        .map(attributeName => displayRow[attributeName])
        .join(' — ');
}

/** The single line label of a whole MembrumRelatum, whose references are all resolved. */
export function membrumRelatumDisplayLabel(membrumRelatum: MembrumRelatumWTO): string {
    return membrumRelatumDisplayRowLabel(
        membrumRelatumDisplayRow(membrumRelatum.clavisPrimaria, membrumRelatum)
    );
}
