package uk.ac.ebi.pwp.widgets.chebi.events;

import com.google.gwt.event.shared.GwtEvent;
import uk.ac.ebi.pwp.widgets.chebi.handlers.ChEBIChemicalLoadedHandler;

/**
 * @author Guilherme Viteri (gviteri@ebi.ac.uk)
 * @author Kostas Sidiropoulos (ksidiro@ebi.ac.uk)
 */
public class ChEBIChemicalLoadedEvent extends GwtEvent<ChEBIChemicalLoadedHandler> {
    public static Type<ChEBIChemicalLoadedHandler> TYPE = new Type<>();

    @Override
    public Type<ChEBIChemicalLoadedHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ChEBIChemicalLoadedHandler handler) {
        handler.onChEBIChemicalLoaded(this);
    }
}
