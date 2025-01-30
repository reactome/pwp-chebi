package uk.ac.ebi.pwp.widgets.chebi.handlers;

import com.google.gwt.event.shared.EventHandler;
import uk.ac.ebi.pwp.widgets.chebi.events.ChEBIChemicalLoadedEvent;

/**
 * @author Guilherme Viteri <gviteri@ebi.ac.uk>
 * @author Kostas Sidiropoulos <ksidiro@ebi.ac.uk>
 */
public interface ChEBIChemicalLoadedHandler extends EventHandler {

    void onChEBIChemicalLoaded(ChEBIChemicalLoadedEvent event);
}
