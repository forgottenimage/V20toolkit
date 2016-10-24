package v20toolkit.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "personalities")
public class PersonalityListWrapper {

    private List<Personality> personalities;

    @XmlElement(name = "personality")
    public List<Personality> getPersonalities() {

        return personalities;
    }

    public void setPersonalities(List<Personality> persons) {

        this.personalities = persons;
    }
}
