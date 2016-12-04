package V20toolkit.view;

import V20toolkit.V20toolkit;
import V20toolkit.model.v20Trait;
import V20toolkit.util.XMLProcessing;
import V20toolkit.util.XMLWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TraitLayoutController {

    @FXML
    private TableView<v20Trait> traitTable;
    @FXML
    private TableColumn<v20Trait, String> traitColumn;
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
        traitData = v20toolkit.getV20TraitData();
        traitTable.setItems(traitData);
    }

    private void showTraitDetails(v20Trait v20Trait) {
        if (v20Trait != null) {
            nameLabel.setText(v20Trait.getName());
            typeLabel.setText(v20Trait.getType());
            attributeLabel.setText(v20Trait.getAttribute());
            pointsLabel.setText("" + v20Trait.getCost());
            resourceLabel.setText(v20Trait.getResources());
            descriptionTextArea.setText(v20Trait.getDescription());
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
            alert.setHeaderText("No v20Trait Selected");
            alert.setContentText("Please select a v20Trait in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewTrait() {
        v20Trait tempV20Trait = new v20Trait();
        boolean okClicked = v20toolkit.showTraitEditDialog(tempV20Trait);
        if (okClicked) {
            v20toolkit.getV20TraitData().add(tempV20Trait);
        }
    }

    @FXML
    private void handleEditTrait() {
        v20Trait selectedV20Trait = traitTable.getSelectionModel().getSelectedItem();
        if (selectedV20Trait != null) {
            boolean okClicked = v20toolkit.showTraitEditDialog(selectedV20Trait);
            if (okClicked) {
                showTraitDetails(selectedV20Trait);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(v20toolkit.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No v20Trait Selected");
            alert.setContentText("Please select a trait in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleSaveTraits() {
        String path = "resources/data/traits.xml";
        XMLWrapper wrapper = new XMLWrapper();
        wrapper.setV20Traits(traitData);
        XMLProcessing.marshalToFile(path, wrapper);
    }
}
