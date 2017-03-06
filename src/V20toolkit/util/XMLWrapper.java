package V20toolkit.util;

import V20toolkit.model.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "root")
public class XMLWrapper {

    private List<v20Personality> personalities;
    private List<v20Trait> v20Traits;
    /*private List<v20Discipline> v20Disciplines;
    private List<v20Ritual> v20Rituals;
    private List<v20Background> v20Backgrounds;
    private List<v20Path> v20Paths;*/

    public XMLWrapper () {}

    @XmlElement(name = "personality")
    public List<v20Personality> getPersonalities() {
        return personalities;
    }

    public void setPersonalities(List<v20Personality> personalities) {
        this.personalities = personalities;
    }

    @XmlElement(name = "trait")
    public List<v20Trait> getV20Traits() {
        return v20Traits;
    }

    public void setV20Traits(List<v20Trait> traits) {
        this.v20Traits = traits;
    }

    /*@XmlElement(name = "discipline")
    public List<v20Discipline> getV20Disciplines() {
        return v20Disciplines;
    }

    public void setV20Disciplines(List<v20Discipline> v20Disciplines) {
        this.v20Disciplines = v20Disciplines;
    }

    @XmlElement(name = "ritual")
    public List<v20Ritual> getV20Rituals() {
        return v20Rituals;
    }

    public void setV20Rituals(List<v20Ritual> v20Rituals) {
        this.v20Rituals = v20Rituals;
    }

    @XmlElement(name = "background")
    public List<v20Background> getV20Backgrounds() {
        return v20Backgrounds;
    }

    public void setV20Backgrounds(List<v20Background> v20Backgrounds) {
        this.v20Backgrounds = v20Backgrounds;
    }

    @XmlElement(name = "path")
    public List<v20Path> getV20Paths() {
        return v20Paths;
    }

    public void setV20Paths(List<v20Path> v20Paths) {
        this.v20Paths = v20Paths;
    }*/
}