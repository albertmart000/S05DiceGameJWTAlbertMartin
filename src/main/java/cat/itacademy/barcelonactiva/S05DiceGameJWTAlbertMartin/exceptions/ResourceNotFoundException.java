package cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private String resourceName;
    private String attributeName;
    private long attributeValue;
    private String attributeText;

    public ResourceNotFoundException(String resourceName, String attributeName, long attributeValue) {
        super(String.format("No s'ha trobat %s amb %s : '%s'", resourceName, attributeName, attributeValue));
        this.resourceName = resourceName;
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    public ResourceNotFoundException(String resourceName, String attributeName, String attributeText) {
        super(String.format("No s'ha trobat %s amb : %s : '%s'", resourceName, attributeName, attributeText));
        this.resourceName = resourceName;
        this.attributeName = attributeName;
        this.attributeText = attributeText;
    }
}
