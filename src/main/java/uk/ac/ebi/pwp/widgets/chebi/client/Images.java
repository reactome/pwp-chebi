package uk.ac.ebi.pwp.widgets.chebi.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * @author Guilherme Viteri <gviteri@ebi.ac.uk>
 * @author Kostas Sidiropoulos <ksidiro@ebi.ac.uk>
 */
interface Images extends ClientBundle {

    Images INSTANCE = GWT.create(Images.class);

    @Source("images/alert.png")
    ImageResource getAlertImage();

    @Source("images/loading.gif")
    ImageResource getLoadingImage();

    @Source("images/goldenstar.gif")
    ImageResource getGoldenStar();
}