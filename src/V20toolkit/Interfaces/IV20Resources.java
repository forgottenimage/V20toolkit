package V20toolkit.Interfaces;

import V20toolkit.model.v20Resource;
import javafx.collections.ObservableList;

import java.util.List;

public interface IV20Resources {
    ObservableList<v20Resource> getResources();
    void setResources(List<v20Resource> resources);
}
