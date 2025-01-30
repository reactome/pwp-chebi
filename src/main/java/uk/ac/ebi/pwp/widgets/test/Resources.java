package uk.ac.ebi.pwp.widgets.test;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author Guilherme Viteri <gviteri@ebi.ac.uk>
 * @author Kostas Sidiropoulos <ksidiro@ebi.ac.uk>
 */
public interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source("chebi1148.xml")
    TextResource responseText();
}
