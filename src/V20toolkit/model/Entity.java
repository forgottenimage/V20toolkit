package V20toolkit.model;

import javafx.beans.property.*;


/**
 * Created by baarzul on 19.10.16.
 */
public class Entity {
    public StringProperty name;
    public ListProperty<Attribute> attributes;
    public ListProperty<Ability> abilities;

    /**
     * Default constructor.
     */
    public Entity() { this(null); }

    public Entity(String name) {
        this.name = new SimpleStringProperty(name);
        this.abilities = new SimpleListProperty();
        this.attributes = new SimpleListProperty();
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }
}
