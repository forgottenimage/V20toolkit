package V20toolkit.view;

import V20toolkit.util.XMLProcessing;
import V20toolkit.util.XMLWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import V20toolkit.V20toolkit;
import V20toolkit.model.v20Personality;

import java.util.ResourceBundle;

public class PersonalityLayoutController {

    private ResourceBundle i18n;
    @FXML
    private TableView<v20Personality> personalityTable;
    @FXML
    private TableColumn<v20Personality, String> personalityColumn;
    @FXML
    private Label nameLabel;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private TextArea willpowerConditionTextArea;

    private V20toolkit v20toolkit;
    private ObservableList personalityData;

    public PersonalityLayoutController(){}

    @FXML
    private void initialize() {
        // TODO Workaround: Used TableView instead of ListView to show the Name. It works but doesn't look very well.
        personalityColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        showPersonalityDetails(null);
        personalityTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPersonalityDetails(newValue));
    }

    public void setMainApp(V20toolkit v20toolkit, ResourceBundle i18n) {
        this.v20toolkit = v20toolkit;
        this.i18n = i18n;
        personalityData = v20toolkit.getV20PersonalityData();
        personalityTable.setItems(personalityData);
    }

    private void showPersonalityDetails(v20Personality v20Personality) {
        if (v20Personality != null) {
            nameLabel.setText(v20Personality.getName());
            descriptionTextArea.setText(v20Personality.getDescription());
            willpowerConditionTextArea.setText(v20Personality.getWillpowerCondition());
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
            alert.setHeaderText("No v20Personality Selected");
            alert.setContentText("Please select a personality in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewPersonality() {
        v20Personality tempV20Personality = new v20Personality(i18n);
        boolean okClicked = v20toolkit.showPersonalityEditDialog(tempV20Personality);
        if (okClicked) {
            v20toolkit.getV20PersonalityData().add(tempV20Personality);
        }
    }

    @FXML
    private void handleEditPersonality() {
        v20Personality selectedV20Personality = personalityTable.getSelectionModel().getSelectedItem();
        if (selectedV20Personality != null) {
            boolean okClicked = v20toolkit.showPersonalityEditDialog(selectedV20Personality);
            if (okClicked) {
                showPersonalityDetails(selectedV20Personality);
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

    public void handleSavePersonalities() {
        String path = "resources/data/personalities.xml";
        XMLWrapper wrapper = new XMLWrapper();
        wrapper.setPersonalities(personalityData);
        XMLProcessing.marshalToFile(path, wrapper);
    }
}
