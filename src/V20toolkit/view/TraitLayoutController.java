package V20toolkit.view;

import V20toolkit.V20toolkit;
import V20toolkit.model.Personality;
import V20toolkit.model.PersonalityListWrapper;
import V20toolkit.model.Trait;
import V20toolkit.model.TraitListWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Created by baarzul on 09.11.16.
 */
public class TraitLayoutController {

    @FXML
    private TableView<Trait> traitTable;
    @FXML
    private TableColumn<Trait, String> traitColumn;
    @FXML
    private Label typeLabel;
    @FXML
    private Label attributeLabel;
    @FXML
    private Label pointsLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private TextArea descriptionTextArea;

    private V20toolkit v20toolkit;
    private ObservableList<Trait> traitData;

    public TraitLayoutController(){}

    @FXML
    private void initialize() {
        // TODO Workaround: Used TableView instead of ListView to show the Name. It works but doesn't look very well.
        traitColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        showTraitDetails(null);
        traitTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showTraitDetails(newValue));
    }

    public void setMainApp(V20toolkit v20toolkit) {
        this.v20toolkit = v20toolkit;
        traitData = v20toolkit.getTraitData();
        traitTable.setItems(traitData);
    }

    private void showTraitDetails(Trait trait) {
        if (trait != null) {
            nameLabel.setText(trait.getName());
            typeLabel.setText(trait.getType());
            attributeLabel.setText(trait.getAttribute());
            pointsLabel.setText("" + trait.getPoints());
            descriptionTextArea.setText(trait.getDescription());
        } else {
            nameLabel.setText("");
            typeLabel.setText("");
            attributeLabel.setText("");
            pointsLabel.setText("");
            descriptionTextArea.setText("");
        }
    }

    @FXML
    private void handleDeleteTrait() {
        int selectedIndex = traitTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            traitTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(v20toolkit.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Trait Selected");
            alert.setContentText("Please select a Trait in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewTrait() {
        Trait tempTrait = new Trait();
        boolean okClicked = v20toolkit.showTraitEditDialog(tempTrait);
        if (okClicked) {
            v20toolkit.getTraitData().add(tempTrait);
        }
    }

    @FXML
    private void handleEditTrait() {
        Trait selectedTrait= traitTable.getSelectionModel().getSelectedItem();
        if (selectedTrait != null) {
            boolean okClicked = v20toolkit.showTraitEditDialog(selectedTrait);
            if (okClicked) {
                showTraitDetails(selectedTrait);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(v20toolkit.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Trait Selected");
            alert.setContentText("Please select a trait in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleSaveTraits() {
        File file = new File("resources/data/traits.xml");

        try {
            JAXBContext context = JAXBContext.newInstance(TraitListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            TraitListWrapper wrapper = new TraitListWrapper();
            wrapper.setTraits(traitData);

            m.marshal(wrapper, file);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
        }
    }
}
