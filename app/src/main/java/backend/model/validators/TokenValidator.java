package backend.model.validators;

import backend.DTO.PointsDTO;
import backend.security.JwtUtils;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TokenValidator extends Validator {
    private final JwtUtils jwtUtils;

    public TokenValidator validateToken(PointsDTO req) {
        if (req.getToken() == null) {
            this.addViolation("token", "Token was not passed.");
            return this;
        }
        return validateToken(req.getToken().getToken());
    }

    public TokenValidator validateToken(String token) {
        if (!jwtUtils.validateAccessToken(token)) {
            this.addViolation("token", "Passed token is invalid.");
        }
        return this;
    }
}
