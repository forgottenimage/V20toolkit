package V20toolkit.model;

import javafx.beans.property.StringProperty;

public class v20Object {
    public StringProperty name;
    public StringProperty description;

    public String getName() {
        return nameProperty().get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
}
