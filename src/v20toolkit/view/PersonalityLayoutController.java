package v20toolkit.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import v20toolkit.V20toolkit;
import v20toolkit.model.Personality;

public class PersonalityLayoutController {

    @FXML
    private ListView<Personality> personalityList;
    @FXML
    private Label nameLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label willpowerConditionLabel;

    private V20toolkit v20toolkit;

    public PersonalityLayoutController(){}

    @FXML
    private void initialize() {
        showPersonalityDetails(null);
        personalityList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPersonalityDetails(newValue));
    }

    public void setMainApp(V20toolkit v20toolkit) {
        this.v20toolkit = v20toolkit;
        personalityList.setItems(v20toolkit.getPersonalityData());
    }

    private void showPersonalityDetails(Personality personality) {
        if (personality != null) {
            nameLabel.setText(personality.getName());
            descriptionLabel.setText(personality.getDescription());
            willpowerConditionLabel.setText(personality.getWillpowerCondition());
        } else {
            nameLabel.setText("");
            descriptionLabel.setText("");
            willpowerConditionLabel.setText("");
        }
    }
}
