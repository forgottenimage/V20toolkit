package V20toolkit.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Personality {
    private final StringProperty name;
    private final StringProperty description;
    private final StringProperty willpowerCondition;

    public Personality() {this(null, null, null);}

    public Personality(String name, String description, String willpowerCondition) {
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.willpowerCondition = new SimpleStringProperty(willpowerCondition);
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

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getWillpowerCondition() {
        return willpowerCondition.get();
    }

    public StringProperty willpowerConditionProperty() {
        return willpowerCondition;
    }

    public void setWillpowerCondition(String willpowerCondition) {
        this.willpowerCondition.set(willpowerCondition);
    }
}
