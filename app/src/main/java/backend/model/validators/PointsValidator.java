package backend.model.validators;

import backend.DTO.PointsDTO;
import backend.security.JwtUtils;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PointsValidator extends Validator {
    private final JwtUtils jwtUtils;

    public PointsValidator validateToken(PointsDTO req) {
        if (jwtUtils.validateAccessToken(req.getUserToken().getToken())) {
            this.addViolation("token", "Passed token is ivalid.");
        }
        return this;
    }
}
