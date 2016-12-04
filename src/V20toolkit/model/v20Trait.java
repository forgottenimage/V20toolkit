package V20toolkit.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class v20Trait extends v20ObjectWithResource{

    private final StringProperty attribute;
    private final StringProperty type;
    private final IntegerProperty cost;

    public v20Trait(ResourceBundle i18n){
        this(
                i18n.getString("v20_name_default"),
                i18n.getString("v20_description_default"),
                new ArrayList<>(),
                i18n.getString("v20_attribute_default"),
                i18n.getString("v20_type_default"),
                0);
    }

    public v20Trait(String name, String description, ArrayList<v20Resource> resources, String attribute, String type, Integer cost) {
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.resources = FXCollections.observableArrayList(resources);
        this.attribute = new SimpleStringProperty(attribute);
        this.type = new SimpleStringProperty(type);
        this.cost = new SimpleIntegerProperty(cost);
    }

    public String getAttribute() {
        return attribute.get();
    }

    public StringProperty attributeProperty() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute.set(attribute);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public int getCost() {
        return cost.get();
    }

    public IntegerProperty costProperty() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost.set(cost);
    }
}
