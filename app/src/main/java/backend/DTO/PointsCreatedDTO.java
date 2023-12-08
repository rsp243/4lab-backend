package backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PointsCreatedDTO {
    private float x;
    private float y;
    private float r;
    private boolean isHit;
    // private long userId;
}
