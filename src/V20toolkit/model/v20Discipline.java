package V20toolkit.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class v20Discipline extends v20ObjectWithResource{

    private final IntegerProperty maxRating;
    private final ObservableList<v20Power> powers;
    private Boolean hasRituals = false;
    private ObservableList<v20Ritual> rituals = null;

    public v20Discipline(ResourceBundle i18n) {
        this(
                i18n.getString("v20_name_default"),
                i18n.getString("v20_description_default"),
                new ArrayList<>(),
                10,
                new ArrayList<>());
    }

    public v20Discipline(String name, String description, ArrayList<v20Resource> resources, Integer maxRating, ArrayList<v20Power> powers, ArrayList<v20Ritual> rituals) {
        this(name, description, resources, maxRating, powers);
        this.hasRituals = true;
        this.rituals = FXCollections.observableArrayList(rituals);
    }

    private v20Discipline(String name, String description, ArrayList<v20Resource> resources, Integer maxRating, ArrayList<v20Power> powers) {
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.resources = FXCollections.observableArrayList(resources);
        this.maxRating = new SimpleIntegerProperty(maxRating);
        this.powers = FXCollections.observableArrayList(powers);
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

    public ObservableList<v20Power> getPowers() {
        return powers;
    }

    public Boolean getHasRituals() {
        return hasRituals;
    }

    public void setHasRituals(Boolean hasRituals) {
        this.hasRituals = hasRituals;
    }

    public ObservableList<v20Ritual> getRituals() {
        return rituals;
    }

    public void setRituals(ObservableList<v20Ritual> rituals) {
        this.rituals = rituals;
    }
}
