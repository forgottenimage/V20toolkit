package V20toolkit.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import V20toolkit.V20toolkit;
import V20toolkit.model.Personality;

public class PersonalityLayoutController {

    @FXML
    private TableView<Personality> personalityTable;
    @FXML
    private TableColumn<Personality, String> personalityColumn;
    @FXML
    private Label nameLabel;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private TextArea willpowerConditionTextArea;

    private V20toolkit v20toolkit;

    public PersonalityLayoutController(){}

    @FXML
    private void initialize() {
        // TODO Workaround: Used TableView instead of ListView to show the Name. It works but doesn't look very well.
        personalityColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        showPersonalityDetails(null);
        personalityTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPersonalityDetails(newValue));
    }

    public void setMainApp(V20toolkit v20toolkit) {
        this.v20toolkit = v20toolkit;
        personalityTable.setItems(v20toolkit.getPersonalityData());
    }

    private void showPersonalityDetails(Personality personality) {
        if (personality != null) {
            nameLabel.setText(personality.getName());
            descriptionTextArea.setText(personality.getDescription());
            willpowerConditionTextArea.setText(personality.getWillpowerCondition());
        } else {
            nameLabel.setText("");
            descriptionTextArea.setText("");
            willpowerConditionTextArea.setText("");
        }
    }

    @FXML
    private void handleDeletePersonality() {
        int selectedIndex = personalityTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personalityTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(v20toolkit.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Personality Selected");
            alert.setContentText("Please select a personality in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewPersonality() {
        Personality tempPersonality = new Personality();
        boolean okClicked = v20toolkit.showPersonalityEditDialog(tempPersonality);
        if (okClicked) {
            v20toolkit.getPersonalityData().add(tempPersonality);
        }
    }

    @FXML
    private void handleEditPersonality() {
        Personality selectedPersonality = personalityTable.getSelectionModel().getSelectedItem();
        if (selectedPersonality != null) {
            boolean okClicked = v20toolkit.showPersonalityEditDialog(selectedPersonality);
            if (okClicked) {
                showPersonalityDetails(selectedPersonality);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(v20toolkit.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No personality Selected");
            alert.setContentText("Please select a personality in the table.");

            alert.showAndWait();
        }
    }
}
