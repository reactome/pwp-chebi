package uk.ac.ebi.pwp.widgets.chebi.client;

import com.google.gwt.user.client.ui.*;
import uk.ac.ebi.pwp.widgets.chebi.model.Chemical;

import java.util.List;

/**
 * @author Guilherme Viteri <gviteri@ebi.ac.uk>
 * @author Kostas Sidiropoulos <ksidiro@ebi.ac.uk>
 */
public class ChEBITable extends Composite {
    private final HTMLTable table;

    public ChEBITable(Chemical chemical) {
        this.table = new FlexTable();
        this.table.setCellSpacing(15);
        this.table.getColumnFormatter().setWidth(0, "150px");
        initialize(chemical);
        this.setWidth("100%");
    }

    private void initialize(Chemical chemical) {
        this.addProperty("ChEBI Name", chemical.getName());
        this.addIdentifier(chemical.getChebiId());
        this.addProperty("Definition", chemical.getDefinition());
        this.addGoldenStars(chemical.getEntityStar());
        this.addProperty("Secondary ChEBI IDs", chemical.getSecondaryChEBIIds());
        this.initWidget(this.table);
    }

    private void addIdentifier(String identifier) {
        Anchor anchor = new Anchor(identifier, "https://www.ebi.ac.uk/chebi/advancedSearchFT.do?searchString=" + identifier, "_blank");
        int row = this.table.getRowCount();
        this.table.setWidget(row, 0, new Label("ChEBI ID"));
        this.table.setWidget(row, 1, anchor);
    }

    private void addProperty(String title, String content) {
        if (content == null) return;
        int row = this.table.getRowCount();
        this.table.setWidget(row, 0, new Label(title));
        this.table.setWidget(row, 1, new Label(content));
    }

    private void addProperty(String title, List<String> content) {
        StringBuilder sb = new StringBuilder();
        for (String s : content) {
            sb.append(s);
            sb.append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        this.addProperty(title, sb.toString());
    }

    private void addGoldenStars(Integer num) {
        FlowPanel starsPanel = new FlowPanel();
        for (int i = 0; i < num; i++) {
            starsPanel.add(new Image(Images.INSTANCE.getGoldenStar().getSafeUri()));
        }
        int row = this.table.getRowCount();
        this.table.setWidget(row, 0, new Label("Stars"));
        this.table.setWidget(row, 1, starsPanel);
    }
}
