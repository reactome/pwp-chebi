package uk.ac.ebi.pwp.widgets.chebi.data;

import com.google.gwt.http.client.*;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.xml.client.*;
import uk.ac.ebi.pwp.widgets.chebi.events.ChemicalRetrievedEvent;
import uk.ac.ebi.pwp.widgets.chebi.handlers.ChemicalRetrievedHandler;
import uk.ac.ebi.pwp.widgets.chebi.model.Chemical;

/**
 * @author Guilherme Viteri <gviteri@ebi.ac.uk>
 * @author Kostas Sidiropoulos <ksidiro@ebi.ac.uk>
 */
public final class ChemicalFactory {

    public final static Integer TIME_OUT = 4000;

    public static void getChemical(String chebiId, final ChemicalRetrievedHandler handler) {
        String uri = "ebi/webservices/chebi/2.0/test/getCompleteEntity?chebiId=" + chebiId; //1148
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, uri);
        requestBuilder.setTimeoutMillis(TIME_OUT);
        try {
            requestBuilder.sendRequest(null, new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {
                    Document xml = XMLParser.parse(response.getText());
                    handler.onChemicalRetrieved(new ChemicalRetrievedEvent(getChemical(xml)));
                }

                @Override
                public void onError(Request request, Throwable exception) {
                    handler.onChemicalFactoryError(exception);
                }
            });
        } catch (RequestException e) {
            handler.onChemicalFactoryError(e);
        }
    }

    public static Chemical getChemical(TextResource resource) {
        Document xml = XMLParser.parse(resource.getText());
        return getChemical(xml);
    }

    private static Chemical getChemical(Document xml) {
        String name = getElement(xml, "chebiAsciiName");
        String chebiId = getElement(xml, "chebiId");

        Chemical chemical = new Chemical(chebiId, name);

        chemical.setDefinition(getElement(xml, "definition"));
        chemical.setEntityStar(Integer.valueOf(getElement(xml, "entityStar")));

        NodeList secondaryChEBIIds = xml.getElementsByTagName("SecondaryChEBIIds");
        for (int i = 0; i < secondaryChEBIIds.getLength(); i++) {
            chemical.addSecondaryChEBIIds(((Text) secondaryChEBIIds.item(i).getFirstChild()).getData());
        }

        return chemical;
    }

    private static String getElement(Document xml, String element) {
        NodeList n = xml.getElementsByTagName(element);
        if (n != null) {
            if (n.getLength() > 0) {
                Element aux = (Element) n.item(0);
                if (aux.hasChildNodes()) {
                    return ((Text) aux.getFirstChild()).getData();
                }
            }
        }
        return null;
    }
}
