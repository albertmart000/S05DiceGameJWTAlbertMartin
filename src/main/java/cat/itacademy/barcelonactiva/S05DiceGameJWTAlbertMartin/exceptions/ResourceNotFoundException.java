package cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    //@Serial
    private static final long serialVersionUID = 1L;

    private String resourceName;
    private String attributeName;
    private long attributeValue;
    private String attributeText;

    public ResourceNotFoundException(String resourceName, String attributeName, long attributeValue) {
        super(String.format("%s no trobat amb %s : '%s'", resourceName, attributeName, attributeValue));
        this.resourceName = resourceName;
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    public ResourceNotFoundException(String resourceName, String attributeName, String attributeText) {
        super(String.format("%s no encontrada con : %s : '%s'", resourceName, attributeName, attributeText));
        this.resourceName = resourceName;
        this.attributeName = attributeName;
        this.attributeText = attributeText;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public long getValorDelCampo() {
        return attributeValue;
    }

    public void setValorDelCampo(long valorDelCampo) {
        this.attributeValue = valorDelCampo;
    }

}
