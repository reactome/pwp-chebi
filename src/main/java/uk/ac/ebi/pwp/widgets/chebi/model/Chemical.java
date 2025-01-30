package uk.ac.ebi.pwp.widgets.chebi.model;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Guilherme Viteri <gviteri@ebi.ac.uk>
 * @author Kostas Sidiropoulos <ksidiro@ebi.ac.uk>
 */
public class Chemical {
    private final String chebiId;
    private final String name;
    private String definition;
    private String status;
    private String smile;
    private String inchi;
    private String inchiKey;
    private String charge;
    private String mass;
    private Integer entityStar;
    private final List<String> secondaryChEBIIds;

    public Chemical(String chebiId, String name) {
        this.chebiId = chebiId;
        this.name = name;
        this.secondaryChEBIIds = new LinkedList<>();
    }

    public String getChebiId() {
        return chebiId;
    }

    public String getName() {
        return name;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSmile() {
        return smile;
    }

    public void setSmile(String smile) {
        this.smile = smile;
    }

    public String getInchi() {
        return inchi;
    }

    public void setInchi(String inchi) {
        this.inchi = inchi;
    }

    public String getInchiKey() {
        return inchiKey;
    }

    public void setInchiKey(String inchiKey) {
        this.inchiKey = inchiKey;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public Integer getEntityStar() {
        return entityStar;
    }

    public void setEntityStar(Integer entityStar) {
        this.entityStar = entityStar;
    }

    public List<String> getSecondaryChEBIIds() {
        return secondaryChEBIIds;
    }

    public void addSecondaryChEBIIds(String secondaryChEBIId) {
        this.secondaryChEBIIds.add(secondaryChEBIId);
    }
}
