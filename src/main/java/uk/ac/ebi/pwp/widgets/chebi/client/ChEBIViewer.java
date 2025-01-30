package uk.ac.ebi.pwp.widgets.chebi.client;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.*;
import uk.ac.ebi.pwp.widgets.chebi.data.ChemicalFactory;
import uk.ac.ebi.pwp.widgets.chebi.events.ChEBIChemicalLoadedEvent;
import uk.ac.ebi.pwp.widgets.chebi.events.ChEBIChemicalNotAvailableEvent;
import uk.ac.ebi.pwp.widgets.chebi.events.ChemicalRetrievedEvent;
import uk.ac.ebi.pwp.widgets.chebi.handlers.ChEBIChemicalLoadedHandler;
import uk.ac.ebi.pwp.widgets.chebi.handlers.ChEBIChemicalNotAvailableHandler;
import uk.ac.ebi.pwp.widgets.chebi.handlers.ChemicalRetrievedHandler;
import uk.ac.ebi.pwp.widgets.chebi.model.Chemical;

/**
 * @author Guilherme Viteri <gviteri@ebi.ac.uk>
 * @author Kostas Sidiropoulos <ksidiro@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class ChEBIViewer extends Composite implements HasHandlers, ChemicalRetrievedHandler {
    private HorizontalPanel container;

    public ChEBIViewer(TextResource xml) {
        initialize();
        showChemical(ChemicalFactory.getChemical(xml));
    }

    public ChEBIViewer(String chebiId) {
        initialize();
        ChemicalFactory.getChemical(chebiId, this);
    }

    public HandlerRegistration addChEBIChemicalLoadedHandler(ChEBIChemicalLoadedHandler handler) {
        return this.addHandler(handler, ChEBIChemicalLoadedEvent.TYPE);
    }

    public HandlerRegistration addCheEBIChemicalNotAvailableHandler(ChEBIChemicalNotAvailableHandler handler) {
        return this.addHandler(handler, ChEBIChemicalNotAvailableEvent.TYPE);
    }

    public static Widget getMessage(ImageResource imageResource, String customMessage) {
        HorizontalPanel hp = new HorizontalPanel();
        hp.setSpacing(5);

        hp.add(new Image(imageResource.getSafeUri()));

        InlineLabel label = new InlineLabel(customMessage);
        label.getElement().getStyle().setFontWeight(Style.FontWeight.NORMAL);
        label.getElement().getStyle().setFloat(Style.Float.LEFT);
        hp.add(label);

        return hp;
    }

    private void initialize() {
        this.container = new HorizontalPanel();
        this.container.setWidth("100%");
        this.container.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        //noinspection GWTStyleCheck
        this.container.addStyleName("chebi-ChEBIViewer");

        String msg = "Loading the ChEBI structure. Please wait...";
        container.add(getMessage(Images.INSTANCE.getLoadingImage(), msg));
        initWidget(container);
    }

    @Override
    public void onChemicalRetrieved(ChemicalRetrievedEvent event) {
        this.showChemical(event.getChemical());
    }

    @Override
    public void onChemicalFactoryError(Throwable exception) {
        this.showErrorMessage(exception.getMessage());
    }

    private void showChemical(Chemical c) {
        this.container.clear();

        FlexTable t = new FlexTable();
        t.setWidget(0, 0, getImage(c));
        t.setWidget(0, 1, new ChEBITable(c));

        this.container.add(t);
        fireEvent(new ChEBIChemicalLoadedEvent());
    }

    private void showErrorMessage(String msg) {
        this.container.clear();
        this.container.add(getMessage(Images.INSTANCE.getAlertImage(), msg));
        fireEvent(new ChEBIChemicalNotAvailableEvent());
    }


    private Widget getImage(Chemical chemical) {
        int w = 200;
        String url = "https://www.ebi.ac.uk/chebi/displayImage.do?defaultImage=true&chebiId=" + chemical.getChebiId() + "&dimensions=" + w + "&scaleMolecule=true&transbg=true";
        Image image = new Image(url);
        image.getElement().getStyle().setBorderWidth(0, Style.Unit.PX);
        Anchor anchor = new Anchor("", "https://www.ebi.ac.uk/chebi/advancedSearchFT.do?searchString=" + chemical.getChebiId(), "_blank");
        DOM.insertBefore(anchor.getElement(), image.getElement(), DOM.getFirstChild(anchor.getElement()));
        anchor.setWidth(w + "px");
        return anchor;
    }
}
