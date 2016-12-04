package V20toolkit.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by baarzul on 22.11.16.
 */
public class v20Background extends v20ObjectWithResource{

    private final IntegerProperty maxRating;

    public v20Background(ResourceBundle i18n) {
        this(
                i18n.getString("v20_name_default"),
                i18n.getString("v20_description_default"),
                new ArrayList<>(),
                10);
    }

    public v20Background(String name, String description, ArrayList<v20Resource> resources,Integer maxRating) {
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.resources = FXCollections.observableArrayList(resources);
        this.maxRating = new SimpleIntegerProperty(maxRating);
    }

    public int getMaxRating() {
        return maxRating.get();
    }

    public IntegerProperty maxRatingProperty() {
        return maxRating;
    }

    public void setMaxRating(int maxRating) {
        this.maxRating.set(maxRating);
    }
}
