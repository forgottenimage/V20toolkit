package V20toolkit.view;

import V20toolkit.model.v20Trait;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Created by baarzul on 09.11.16.
 */
public class TraitEditDialogController {

    @FXML
    private TextArea nameTextArea;
    @FXML
    private TextArea typeTextArea;
    @FXML
    private TextArea attributeTextArea;
    @FXML
    private TextArea pointsTextArea;
    @FXML
    private TextArea resourceTextArea;
    @FXML
    private TextArea descriptionTextArea;

    private Stage dialogStage;
    private v20Trait v20Trait;
    private boolean okClicked = false;

    @FXML
    private void initialize() {}

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setV20Trait(v20Trait v20Trait) {
        this.v20Trait = v20Trait;

        nameTextArea.setText(v20Trait.getName());
        descriptionTextArea.setText(v20Trait.getDescription());
        attributeTextArea.setText(v20Trait.getAttribute());
        pointsTextArea.setText("" + v20Trait.getCost());
        resourceTextArea.setText("");
        typeTextArea.setText(v20Trait.getType());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            v20Trait.setName(nameTextArea.getText());
            v20Trait.setDescription(descriptionTextArea.getText());
            v20Trait.setAttribute(attributeTextArea.getText());
            v20Trait.setCost(Integer.parseInt(pointsTextArea.getText()));
            //v20Trait.setResources("");
            v20Trait.setType(typeTextArea.getText());
            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (nameTextArea.getText() == null || nameTextArea.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (descriptionTextArea.getText() == null || descriptionTextArea.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (attributeTextArea.getText() == null || attributeTextArea.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (pointsTextArea.getText() == null || pointsTextArea.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (resourceTextArea.getText() == null || resourceTextArea.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (typeTextArea.getText() == null || typeTextArea.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
