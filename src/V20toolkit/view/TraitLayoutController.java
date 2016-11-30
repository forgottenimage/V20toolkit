package V20toolkit.view;

import V20toolkit.V20toolkit;
import V20toolkit.model.Trait;
import V20toolkit.util.XMLProcessing;
import V20toolkit.util.XMLWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

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
    private Label resourceLabel;
    @FXML
    private TextArea descriptionTextArea;

    private V20toolkit v20toolkit;
    private ObservableList traitData;

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
            resourceLabel.setText(trait.getResource());
            descriptionTextArea.setText(trait.getDescription());
        } else {
            nameLabel.setText("");
            typeLabel.setText("");
            attributeLabel.setText("");
            pointsLabel.setText("");
            resourceLabel.setText("");
            descriptionTextArea.setText("");
        }
    }

    @FXML
    private void handleDeleteTrait() {
        int selectedIndex = traitTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            traitTable.getItems().remove(selectedIndex);
        } else {
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
        String path = "resources/data/traits.xml";
        XMLWrapper wrapper = new XMLWrapper();
        wrapper.setTraits(traitData);
        XMLProcessing.marshalToFile(path, wrapper);
    }
}
