package V20toolkit.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class v20Personality extends v20ObjectWithResource{
    private final StringProperty willpowerCondition;

    public v20Personality(ResourceBundle i18n) {
        this(
                i18n.getString("v20_name_default"),
                i18n.getString("v20_description_default"),
                new ArrayList<>(),
                i18n.getString("v20_willpowerCondition_default"));
    }

    public v20Personality(String name, String description, ArrayList<v20Resource> resources, String willpowerCondition) {
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.resources = FXCollections.observableArrayList(resources);
        this.willpowerCondition = new SimpleStringProperty(willpowerCondition);
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
