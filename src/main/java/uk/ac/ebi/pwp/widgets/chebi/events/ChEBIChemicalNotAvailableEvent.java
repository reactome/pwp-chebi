package uk.ac.ebi.pwp.widgets.chebi.events;

import com.google.gwt.event.shared.GwtEvent;
import uk.ac.ebi.pwp.widgets.chebi.handlers.ChEBIChemicalNotAvailableHandler;

/**
 * @author Guilherme Viteri <gviteri@ebi.ac.uk>
 * @author Kostas Sidiropoulos <ksidiro@ebi.ac.uk>
 */
public class ChEBIChemicalNotAvailableEvent extends GwtEvent<ChEBIChemicalNotAvailableHandler> {
    public static Type<ChEBIChemicalNotAvailableHandler> TYPE = new Type<>();

    @Override
    public Type<ChEBIChemicalNotAvailableHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ChEBIChemicalNotAvailableHandler handler) {
        handler.onCheEBIChemicalNotAvailableHandler(this);
    }
}
