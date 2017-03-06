package V20toolkit.model;

import V20toolkit.Interfaces.IV20Description;
import V20toolkit.Interfaces.IV20Resources;
import V20toolkit.Interfaces.Iv20Name;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class v20Trait implements IV20Resources, IV20Description, Iv20Name{

    private final StringProperty attribute;
    private final StringProperty type;
    private final IntegerProperty cost;
    private final StringProperty name;
    private final StringProperty description;
    private final ObservableList<v20Resource> resources;

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

    @Override
    public String getName() {
        return name.get();
    }

    @Override
    public StringProperty getNameProperty() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name.set(name);
    }

    @Override
    public String getDescription() {
        return description.get();
    }

    @Override
    public StringProperty getDescriptionProperty() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description.set(description);
    }

    @Override
    public ObservableList<v20Resource> getResources() {
        return resources;
    }

    @Override
    public void setResources(List<v20Resource> resources) {
        this.resources.setAll(resources);
    }
}
