package V20toolkit.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import V20toolkit.model.v20Personality;

import java.util.Locale;
import java.util.ResourceBundle;

public class PersonalityEditDialogController {

    private ResourceBundle i18n;
    @FXML
    private Label nameLabel;
    @FXML
    private TextArea nameTextArea;
    @FXML
    private Label descriptionLabel;
    @FXML
    private TextArea descriptionTestArea;
    @FXML
    private Label willpowerConditionLabel;
    @FXML
    private TextArea willpowerConditionTextArea;

    private Stage dialogStage;
    private v20Personality v20Personality;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
        i18n = ResourceBundle.getBundle("bundles.locale", new Locale("en", "GB"));
        nameLabel.setText(i18n.getString("cmm_name"));
        descriptionLabel.setText(i18n.getString("cmm_description"));
        willpowerConditionLabel.setText(i18n.getString("v20_willpowerCondition"));
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setV20Personality(v20Personality v20Personality) {
        this.v20Personality = v20Personality;

        nameTextArea.setText(v20Personality.getName());
        descriptionTestArea.setText(v20Personality.getDescription());
        willpowerConditionTextArea.setText(v20Personality.getWillpowerCondition());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            v20Personality.setName(nameTextArea.getText());
            v20Personality.setDescription(descriptionTestArea.getText());
            v20Personality.setWillpowerCondition(willpowerConditionTextArea.getText());
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