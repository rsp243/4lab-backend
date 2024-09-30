package backend.DTO;

import backend.model.Color;
import backend.model.Coordinates;
import backend.model.Country;
import backend.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonCreatedDTO {
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDate creationDate;
    private Color eyeColor;
    private Color hairColor;
    private Location location;
    private int height;
    private Country nationality;
}
