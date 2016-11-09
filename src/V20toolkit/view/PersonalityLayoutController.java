package V20toolkit.view;

import V20toolkit.model.PersonalityListWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import V20toolkit.V20toolkit;
import V20toolkit.model.Personality;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;

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
    private ObservableList<Personality> personalityData;

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
        personalityData = v20toolkit.getPersonalityData();
        personalityTable.setItems(personalityData);
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

    public void handleSavePersonalities() {
        File file = new File("resources/data/personalities.xml");
        try {
            JAXBContext context = JAXBContext.newInstance(PersonalityListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            PersonalityListWrapper wrapper = new PersonalityListWrapper();
            wrapper.setPersonalities(personalityData);
            m.marshal(wrapper, file);
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());
            alert.showAndWait();
        }
    }
}
