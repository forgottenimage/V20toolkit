package V20toolkit.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ResourceBundle;

public class v20Resource extends v20Object{

    private final StringProperty abbreviation;

    public v20Resource(ResourceBundle i18n) {
        this(
                i18n.getString("v20_name_default"),
                i18n.getString("v20_description_default"),
                i18n.getString("v20_abbreviation_default"));
    }

    public v20Resource(String name, String description, String abbreviation) {
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.abbreviation = new SimpleStringProperty(abbreviation);
    }

    public String getAbbreviation() {
        return abbreviation.get();
    }

    public StringProperty abbreviationProperty() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation.set(abbreviation);
    }

}
