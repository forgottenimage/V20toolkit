package V20toolkit.model;

import V20toolkit.Interfaces.IV20Description;
import V20toolkit.Interfaces.IV20Resources;
import V20toolkit.Interfaces.Iv20Name;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class v20Personality implements IV20Description, Iv20Name, IV20Resources{
    private final StringProperty willpowerCondition;
    private final StringProperty name;
    private final StringProperty description;
    private final ObservableList<v20Resource> resources;

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
