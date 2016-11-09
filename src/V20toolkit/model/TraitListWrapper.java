package V20toolkit.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "traits")
public class TraitListWrapper {

    private List<Trait> traits;

    @XmlElement(name = "trait")
    public List<Trait> getTraits() {

        return traits;
    }

    public void setTraits(List<Trait> traits) {

        this.traits = traits;
    }
}
