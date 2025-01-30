package uk.ac.ebi.pwp.widgets.chebi.events;

import com.google.gwt.event.shared.GwtEvent;
import uk.ac.ebi.pwp.widgets.chebi.handlers.ChemicalRetrievedHandler;
import uk.ac.ebi.pwp.widgets.chebi.model.Chemical;

/**
 * @author Guilherme Viteri <gviteri@ebi.ac.uk>
 * @author Kostas Sidiropoulos <ksidiro@ebi.ac.uk>
 */
public class ChemicalRetrievedEvent extends GwtEvent<ChemicalRetrievedHandler> {
    public static Type<ChemicalRetrievedHandler> TYPE = new Type<>();
    private final Chemical chemical;

    public ChemicalRetrievedEvent(Chemical chemical) {
        this.chemical = chemical;
    }

    @Override
    public Type<ChemicalRetrievedHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ChemicalRetrievedHandler handler) {
        handler.onChemicalRetrieved(this);
    }

    public Chemical getChemical() {
        return chemical;
    }
}
