package V20toolkit.util;

import V20toolkit.model.*;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadFactory;

import static java.awt.SystemColor.text;

public class XMLProcessing {

    public static ObservableList<?> loadXML(String path, ObservableList<?> data){
        XMLWrapper wrapper;
        File file = new File(path);
        try {
            JAXBContext context = JAXBContext.newInstance(XMLWrapper.class);
            Unmarshaller um = context.createUnmarshaller();
            wrapper = (XMLWrapper) um.unmarshal(file);
            if (data.get(0) instanceof Trait) {
                data = wrapper.getTraits();
            } else if (data.get(0) instanceof Personality) {
                data = wrapper.getPersonalities();
            } else if (data.get(0) instanceof Ritual) {
                data = wrapper.getRituals();
            } else if (data.get(0) instanceof Background) {
                data = wrapper.getBackgrounds();
            } else if (data.get(0) instanceof Discipline) {
                data = wrapper.getDisciplines();
            }
        } catch (Exception e) {
            System.out.print(e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());
            alert.showAndWait();
        }
        return data;
    }

    public static void saveXML(String path, List<?> data) {
        File file = new File(path);

        try {
            JAXBContext context = JAXBContext.newInstance(XMLWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            XMLWrapper wrapper = new XMLWrapper();

            if (data.get(0) instanceof Trait) {
                wrapper.setTraits((ObservableList<Trait>) data);
            } else if (data.get(0) instanceof Personality) {
                wrapper.setPersonalities((ObservableList<Personality>) data);
            } else if (data.get(0) instanceof Ritual) {
                wrapper.setRituals((ObservableList<Ritual>) data);
            } else if (data.get(0) instanceof Background) {
                wrapper.setBackgrounds((ObservableList<Background>) data);
            } else if (data.get(0) instanceof Discipline) {
                wrapper.setDisciplines((ObservableList<Discipline>) data);
            }
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