package V20toolkit.util;

import V20toolkit.model.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "resources")
public class XMLWrapper {

    private List<Personality> personalities;
    private List<Trait> traits;
    private List<Discipline> disciplines;
    private List<Ritual> rituals;
    private List<Background> backgrounds;
    private List<Path> paths;

    public XMLWrapper () {}

    @XmlElement(name = "personality")
    public List<Personality> getPersonalities() {
        return personalities;
    }

    public void setPersonalities(List<Personality> persons) {
        this.personalities = persons;
    }

    @XmlElement(name = "trait")
    public List<Trait> getTraits() {
        return traits;
    }

    public void setTraits(List<Trait> traits) {
        this.traits = traits;
    }

    @XmlElement(name = "discipline")
    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    @XmlElement(name = "ritual")
    public List<Ritual> getRituals() {
        return rituals;
    }

    public void setRituals(List<Ritual> rituals) {
        this.rituals = rituals;
    }

    @XmlElement(name = "background")
    public List<Background> getBackgrounds() {
        return backgrounds;
    }

    public void setBackgrounds(List<Background> backgrounds) {
        this.backgrounds = backgrounds;
    }

    @XmlElement(name = "path")
    public List<Path> getPaths() {
        return paths;
    }

    public void setPaths(List<Path> paths) {
        this.paths = paths;
    }
}