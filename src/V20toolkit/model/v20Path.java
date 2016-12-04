package V20toolkit.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class v20Path extends v20ObjectWithResource{

    private final StringProperty nickname;
    private final ObservableList<v20Virtue> virtues;
    private final StringProperty bearing;
    private final ObservableList<String> ethics;
    private final StringProperty history;
    private final StringProperty currentPractices;
    private final StringProperty followerDescription;
    private final StringProperty followingThePath;
    private final ObservableList<v20Ability> commonAbilities;
    private final ObservableList<v20Discipline> preferredDisciplines;
    private final ObservableList<ObservableList<String>> hierarchyOfSins;

    public v20Path(ResourceBundle i18n) {
        this(
                i18n.getString("v20_name_default"),
                i18n.getString("v20_description_default"),
                new ArrayList<>(),
                i18n.getString("v20_nickname_default"),
                new ArrayList<>(),
                i18n.getString("v20_bearing_default"),
                new ArrayList<>(),
                i18n.getString("v20_history_default"),
                i18n.getString("v20_currentPractices_default"),
                i18n.getString("v20_followerDescription_default"),
                i18n.getString("v20_followingThePath_default"),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>());
    }

    public v20Path(
            String name,
            String description,
            ArrayList<v20Resource> resources,
            String nickname,
            ArrayList<v20Virtue> virtues,
            String bearing,
            ArrayList<String> ethics,
            String history,
            String currentPractices,
            String followerDescription,
            String followingThePath,
            ArrayList<v20Ability> commonAbilities,
            ArrayList<v20Discipline> preferredDisciplines,
            ArrayList<ObservableList<String>> hierarchyOfSins) {
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.resources = FXCollections.observableArrayList(resources);
        this.nickname = new SimpleStringProperty(nickname);
        this.virtues = FXCollections.observableArrayList(virtues);
        this.bearing = new SimpleStringProperty(bearing);
        this.ethics = FXCollections.observableArrayList(ethics);
        this.history = new SimpleStringProperty(history);
        this.currentPractices = new SimpleStringProperty(currentPractices);
        this.followingThePath = new SimpleStringProperty(followingThePath);
        this.followerDescription = new SimpleStringProperty(followerDescription);
        this.commonAbilities = FXCollections.observableArrayList(commonAbilities);
        this.preferredDisciplines = FXCollections.observableArrayList(preferredDisciplines);
        this.hierarchyOfSins = FXCollections.observableArrayList(hierarchyOfSins);
    }

    public String getNickname() {
        return nickname.get();
    }

    public StringProperty nicknameProperty() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname.set(nickname);
    }

    public ObservableList<v20Virtue> getVirtues() {
        return virtues;
    }

    public String getBearing() {
        return bearing.get();
    }

    public StringProperty bearingProperty() {
        return bearing;
    }

    public void setBearing(String bearing) {
        this.bearing.set(bearing);
    }

    public ObservableList<String> getEthics() {
        return ethics;
    }

    public String getHistory() {
        return history.get();
    }

    public StringProperty historyProperty() {
        return history;
    }

    public void setHistory(String history) {
        this.history.set(history);
    }

    public String getCurrentPractices() {
        return currentPractices.get();
    }

    public StringProperty currentPracticesProperty() {
        return currentPractices;
    }

    public void setCurrentPractices(String currentPractices) {
        this.currentPractices.set(currentPractices);
    }

    public String getFollowerDescription() {
        return followerDescription.get();
    }

    public StringProperty followerDescriptionProperty() {
        return followerDescription;
    }

    public void setFollowerDescription(String followerDescription) {
        this.followerDescription.set(followerDescription);
    }

    public String getFollowingThePath() {
        return followingThePath.get();
    }

    public StringProperty followingThePathProperty() {
        return followingThePath;
    }

    public void setFollowingThePath(String followingThePath) {
        this.followingThePath.set(followingThePath);
    }

    public ObservableList<v20Ability> getCommonAbilities() {
        return commonAbilities;
    }

    public ObservableList<v20Discipline> getPreferredDisciplines() {
        return preferredDisciplines;
    }

    public ObservableList<ObservableList<String>> getHierarchyOfSins() {
        return hierarchyOfSins;
    }
}
