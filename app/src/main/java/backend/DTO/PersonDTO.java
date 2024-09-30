package backend.DTO;

import backend.model.Color;
import backend.model.Coordinates;
import backend.model.Country;
import backend.model.Location;
import lombok.Data;

@Data
public class PersonDTO implements TokenizedDTO {
    private String name;
    private Long coordinatesId;
    private Color eyeColor;
    private Color hairColor;
    private Long locationId;
    private int height;
    private Country nationality;
    private TokenDTO token;
}
