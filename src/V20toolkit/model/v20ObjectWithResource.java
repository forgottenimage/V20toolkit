package V20toolkit.model;

import javafx.collections.ObservableList;

public class v20ObjectWithResource extends v20Object{
    public ObservableList<v20Resource> resources;

    public void setResources(ObservableList<v20Resource> resources) { this.resources = resources; }

    public ObservableList<v20Resource> getResources() {
        return resources;
    }
}
