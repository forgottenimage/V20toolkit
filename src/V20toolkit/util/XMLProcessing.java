package V20toolkit.util;

import javafx.scene.control.Alert;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XMLProcessing {

    public static XMLWrapper unmarshalFromFile(File file) {
        XMLWrapper wrapper = new XMLWrapper();
        try {
            JAXBContext context = JAXBContext.newInstance(XMLWrapper.class);
            Unmarshaller um = context.createUnmarshaller();
            wrapper = (XMLWrapper) um.unmarshal(file);
        } catch (Exception e) {
            System.out.print(e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());
            alert.showAndWait();
        }
        return wrapper;
    }

    public static void marshalToFile(String path, XMLWrapper wrapper) {
        File file = new File(path);
        try {
            JAXBContext context = JAXBContext.newInstance(XMLWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(wrapper, file);
        } catch (Exception e) {
            System.out.print(e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());
            alert.showAndWait();
        }
    }
}