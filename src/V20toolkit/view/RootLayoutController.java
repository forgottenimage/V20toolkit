package V20toolkit.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import V20toolkit.V20toolkit;

import java.io.File;

/**
 * Created by baarzul on 19.10.16.
 */
public class RootLayoutController {

    // Reference to the main application
    private V20toolkit v20toolkit;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param v20toolkit
     */
    public void setMainApp(V20toolkit v20toolkit) {

        this.v20toolkit = v20toolkit;
    }

    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("V20toolkit");
        alert.setHeaderText("About");
        alert.setContentText("Author: Raiko Niederlein\nGithub: https://github.com/scherben\nWebsite: http://scherben.github.io");

        alert.showAndWait();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {

        System.exit(0);
    }

    /**
     * Shows the Overview for Personalities
     */
    @FXML
    private void handlePersonalityOverview() {

        v20toolkit.showPersonalityOverview();
    }

    @FXML
    private void handleSave() {
        File personalityFile = v20toolkit.getPersonFilePath();
        if (personalityFile != null) {
            v20toolkit.savePersonalityDataToFile(personalityFile);
        } else {
            //Todo: Errorhandling
            //handleSaveAs();
        }
    }
}
