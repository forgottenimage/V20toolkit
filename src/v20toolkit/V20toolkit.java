package v20toolkit;

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
import v20toolkit.model.Personality;
import v20toolkit.model.PersonalityListWrapper;
import v20toolkit.view.PersonalityEditDialogController;
import v20toolkit.view.PersonalityLayoutController;
import v20toolkit.view.RootLayoutController;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

public class V20toolkit extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Personality> personalityData = FXCollections.observableArrayList();

    public ObservableList<Personality> getPersonalityData() {
        return personalityData;
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public V20toolkit (){
        personalityData.add(new Personality("test", "test", "test"));
    }

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(V20toolkit.class.getResource("/v20toolkit/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the v20toolkit.controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPersonalityOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(V20toolkit.class.getResource("/v20toolkit/view/PersonalityLayout.fxml"));
            AnchorPane personalityOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personalityOverview);

            // Give the controller access to the main app.
            PersonalityLayoutController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showPersonalityEditDialog(Personality personality) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(V20toolkit.class.getResource("/v20toolkit/view/PersonalityEditDialog.fxml"));
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

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void loadPersonalityDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(PersonalityListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            PersonalityListWrapper wrapper = (PersonalityListWrapper) um.unmarshal(file);

            personalityData.clear();
            personalityData.addAll(wrapper.getPersonalities());

        } catch (Exception e) { // catches ANY exception
            System.out.print(e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    public void savePersonalityDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(PersonalityListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            PersonalityListWrapper wrapper = new PersonalityListWrapper();
            wrapper.setPersonalities(personalityData);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
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
        //savePersonalityDataToFile(new File("resources/data/personalities.xml"));
        loadPersonalityDataFromFile(new File("resources/data/personalities.xml"));
    }
}