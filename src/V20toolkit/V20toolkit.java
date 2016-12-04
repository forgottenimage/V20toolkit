package V20toolkit;

import V20toolkit.model.*;
import V20toolkit.util.XMLProcessing;
import V20toolkit.util.XMLWrapper;
import V20toolkit.view.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class V20toolkit extends Application {

    private ResourceBundle i18n;
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<v20Personality> v20PersonalityData = FXCollections.observableArrayList();
    private ObservableList<v20Trait> v20TraitData = FXCollections.observableArrayList();

    public ObservableList<v20Personality> getV20PersonalityData() {
        return v20PersonalityData;
    }
    public ObservableList<v20Trait> getV20TraitData() { return v20TraitData; }
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public V20toolkit (){}

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(V20toolkit.class.getResource("/V20toolkit/view/RootLayout.fxml"));
            rootLayout = loader.load();
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
        v20PersonalityData.add(new v20Personality(i18n));
        File file = new File("resources/data/personalities.xml");
        if (file.isFile()) {
            XMLWrapper wrapper =  XMLProcessing.unmarshalFromFile(file);
            if (wrapper.getPersonalities()!= null) {
                v20PersonalityData.clear();
                v20PersonalityData.addAll(wrapper.getPersonalities());
            }
        }
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(V20toolkit.class.getResource("/V20toolkit/view/PersonalityLayout.fxml"));
            AnchorPane personalityOverview = loader.load();
            rootLayout.setCenter(personalityOverview);
            PersonalityLayoutController controller = loader.getController();
            controller.setMainApp(this, i18n);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showTraitOverview() {
        v20TraitData.add(new v20Trait(i18n));
        File file = new File("resources/data/traits.xml");
        if (file.isFile()) {
            XMLWrapper wrapper = XMLProcessing.unmarshalFromFile(file);
            if (wrapper.getV20Traits() != null) {
                v20TraitData.clear();
                v20TraitData.addAll(wrapper.getV20Traits());
            }
        }
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(V20toolkit.class.getResource("/V20toolkit/view/TraitLayout.fxml"));
            AnchorPane traitOverview = loader.load();
            rootLayout.setCenter(traitOverview);
            TraitLayoutController controller = loader.getController();
            controller.setMainApp(this, i18n);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showPersonalityEditDialog(v20Personality v20Personality) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(V20toolkit.class.getResource("/V20toolkit/view/PersonalityEditDialog.fxml"));
            AnchorPane page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle(i18n.getString("v20_edit_personality"));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            PersonalityEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setV20Personality(v20Personality);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showTraitEditDialog(v20Trait v20Trait) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(V20toolkit.class.getResource("/V20toolkit/view/TraitEditDialog.fxml"));
            AnchorPane page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle(i18n.getString("v20_edit_trait"));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            TraitEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setV20Trait(v20Trait);
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
        i18n = ResourceBundle.getBundle("bundles.locale", new Locale("en", "GB"));
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(i18n.getString("v20_title"));
        this.primaryStage.getIcons().add(new Image("file:resources/images/ident_icon.png"));
        initRootLayout();
    }
}