package V20toolkit.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import V20toolkit.model.Personality;

public class PersonalityEditDialogController {

    @FXML
    private TextArea nameTextArea;
    @FXML
    private TextArea descriptionTestArea;
    @FXML
    private TextArea willpowerConditionTextArea;

    private Stage dialogStage;
    private Personality personality;
    private boolean okClicked = false;

    @FXML
    private void initialize() {}

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPersonality(Personality personality) {
        this.personality = personality;

        nameTextArea.setText(personality.getName());
        descriptionTestArea.setText(personality.getDescription());
        willpowerConditionTextArea.setText(personality.getWillpowerCondition());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            personality.setName(nameTextArea.getText());
            personality.setDescription(descriptionTestArea.getText());
            personality.setWillpowerCondition(willpowerConditionTextArea.getText());
            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (nameTextArea.getText() == null || nameTextArea.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (descriptionTestArea.getText() == null || descriptionTestArea.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (willpowerConditionTextArea.getText() == null || willpowerConditionTextArea.getText().length() == 0) {
            errorMessage += "No valid street!\n";
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