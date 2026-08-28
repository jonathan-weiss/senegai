import {MembrumRelatumWTO} from "@app/wto/membrum-relatum.wto";

/**
 * The display attributes of a MembrumRelatum: the attributes that identify an instance for a
 * human reader. A reference to an EntitasRelata is stored as a bare UUID, which tells the user
 * nothing, so every place that shows such a reference resolves it to the whole
 * MembrumRelatumWTO and renders these attributes instead of the UUID alone.
 */
export const MEMBRUM_RELATUM_DISPLAY_ATTRIBUTE_NAMES: ReadonlyArray<keyof MembrumRelatumWTO> = [
    'clavisPrimaria',
    'descriptioExDistanti',
];

/** Shown for a display attribute whose reference could not be resolved. */
export const MEMBRUM_RELATUM_UNRESOLVED_DISPLAY_VALUE = '—';

/**
 * Joins the display attributes of a MembrumRelatum into a single line, for the places that
 * have room for one line only (the typeahead suggestions).
 */
export function membrumRelatumDisplayLabel(membrumRelatum: MembrumRelatumWTO): string {
    return MEMBRUM_RELATUM_DISPLAY_ATTRIBUTE_NAMES
        .map(attributeName => membrumRelatum[attributeName])
        .join(' — ');
}
