package V20toolkit;

import V20toolkit.model.Trait;
import V20toolkit.model.TraitListWrapper;
import V20toolkit.view.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import V20toolkit.model.Personality;
import V20toolkit.model.PersonalityListWrapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

public class V20toolkit extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Personality> personalityData = FXCollections.observableArrayList();
    private ObservableList<Trait> traitData = FXCollections.observableArrayList();
    public ObservableList<Personality> getPersonalityData() {
        return personalityData;
    }
    public ObservableList<Trait> getTraitData() { return traitData; }
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public V20toolkit (){}

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(V20toolkit.class.getResource("/V20toolkit/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPersonalityOverview() {
        File file = new File("resources/data/personalities.xml");
        try {
            JAXBContext context = JAXBContext.newInstance(PersonalityListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();
            PersonalityListWrapper wrapper = (PersonalityListWrapper) um.unmarshal(file);
            personalityData.clear();
            personalityData.addAll(wrapper.getPersonalities());
        } catch (Exception e) {
            System.out.print(e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());
            alert.showAndWait();
        }

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(V20toolkit.class.getResource("/V20toolkit/view/PersonalityLayout.fxml"));
            AnchorPane personalityOverview = (AnchorPane) loader.load();
            rootLayout.setCenter(personalityOverview);
            PersonalityLayoutController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showTraitOverview() {
        File file = new File("resources/data/traits.xml");
        try {
            JAXBContext context = JAXBContext.newInstance(TraitListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();
            TraitListWrapper wrapper = (TraitListWrapper) um.unmarshal(file);
            traitData.clear();
            traitData.addAll(wrapper.getTraits());
        } catch (Exception e) {
            System.out.print(e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());
            alert.showAndWait();
        }

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(V20toolkit.class.getResource("/V20toolkit/view/TraitLayout.fxml"));
            AnchorPane traitOverview = (AnchorPane) loader.load();
            rootLayout.setCenter(traitOverview);
            TraitLayoutController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showPersonalityEditDialog(Personality personality) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(V20toolkit.class.getResource("/V20toolkit/view/PersonalityEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Personality");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            PersonalityEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPersonality(personality);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showTraitEditDialog(Trait trait) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(V20toolkit.class.getResource("/V20toolkit/view/TraitEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Trait");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            TraitEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setTrait(trait);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("V20toolkit");
        this.primaryStage.getIcons().add(new Image("file:resources/images/ident_icon.png"));
        initRootLayout();
    }
}