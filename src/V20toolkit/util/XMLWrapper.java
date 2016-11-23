package V20toolkit.util;

import V20toolkit.model.*;
import javafx.collections.ObservableList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "resource")
public class XMLWrapper {

    private ObservableList<Personality> personalities;
    private ObservableList<Trait> traits;
    private ObservableList<Discipline> disciplines;
    private ObservableList<Ritual> rituals;
    private ObservableList<Background> backgrounds;
    private ObservableList<Path> paths;

    @XmlElement(name = "personality")
    public ObservableList<?> getPersonalities() {
        return personalities;
    }

    public void setPersonalities(ObservableList<Personality> persons) {
        this.personalities = persons;
    }

    @XmlElement(name = "trait")
    public ObservableList<?> getTraits() {
        return traits;
    }

    public void setTraits(ObservableList<Trait> traits) {
        this.traits = traits;
    }

    @XmlElement(name = "discipline")
    public ObservableList<?> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(ObservableList<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    @XmlElement(name = "ritual")
    public ObservableList<?> getRituals() {
        return rituals;
    }

    public void setRituals(ObservableList<Ritual> rituals) {
        this.rituals = rituals;
    }

    @XmlElement(name = "background")
    public ObservableList<?> getBackgrounds() {
        return backgrounds;
    }

    public void setBackgrounds(ObservableList<Background> backgrounds) {
        this.backgrounds = backgrounds;
    }

    @XmlElement(name = "path")
    public List<Path> getPaths() {
        return paths;
    }

    public void setPaths(ObservableList<Path> paths) {
        this.paths = paths;
    }

}