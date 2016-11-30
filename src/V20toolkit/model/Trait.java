package V20toolkit.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Trait {
    private final StringProperty name;
    private final StringProperty description;
    private final StringProperty attribute;
    private final StringProperty type;
    private final IntegerProperty points;
    private final StringProperty resource;

    public Trait(){
        this("Name of Trait", "Describe the Trait", "Physical, Social, Mental or Magical?", "Merit or Flaw?", 0, "Where did you find it?");
    }

    public Trait(String name, String description, String attribute, String type, Integer points, String resource) {
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.attribute = new SimpleStringProperty(attribute);
        this.type = new SimpleStringProperty(type);
        this.points = new SimpleIntegerProperty(points);
        this.resource = new SimpleStringProperty(resource);
    }

    public String getResource() { return resource.get(); }

    public StringProperty resourceProperty() { return resource; }

    public void setResource(String resource) { this.resource.set(resource); }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
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

    public int getPoints() {
        return points.get();
    }

    public IntegerProperty pointsProperty() {
        return points;
    }

    public void setPoints(int points) {
        this.points.set(points);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
