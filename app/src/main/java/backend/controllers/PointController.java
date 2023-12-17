package backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.LinkedList;
import backend.model.Points;
import backend.DTO.PointsCreatedDTO;
import backend.DTO.PointsDTO;
import backend.DTO.PointsDeletedDTO;
import backend.DTO.TokenDTO;
import backend.DTO.UsersDTO;
import backend.exceptions.ApiException;
import backend.model.validators.TokenValidator;
import backend.model.validators.UsersValidator;
import backend.security.JwtUtils;
import backend.services.PointsService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/point", produces = { "application/json" })
public class PointController {

    private final JwtUtils jwtUtils;
    private final PointsService pointsService;

    @PostMapping(path = "/getAll")
    public ResponseEntity<?> getAll(@RequestBody TokenDTO req) {
        TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req.getToken());

        return ControllerExecutor.execute(validator, () -> {
            List<Points> allPoints = pointsService.getAllPointsCreatedByUser(req);
            List<PointsCreatedDTO> result = new LinkedList<PointsCreatedDTO>();
            for (int i = 0; i < allPoints.size(); i++) {
                Points iPoint = allPoints.get(i);
                result.add(iPoint.getCreatedPoint(iPoint));
            }
            return ResponseEntity.ok().body(result);
        });
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> addPoint(@RequestBody PointsDTO req) {
        TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req);

        return ControllerExecutor.execute(validator, () -> {
            PointsCreatedDTO pointDTO = pointsService.addPoint(req);
            return ResponseEntity.ok().body(pointDTO);
        });
    }

    @PostMapping(path = "/delete")
    public ResponseEntity<?> deleteAllPoints(@RequestBody TokenDTO req) {
        TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req.getToken());

        return ControllerExecutor.execute(validator, () -> {
            PointsDeletedDTO pointDTO = pointsService.deleteAllPoints(
                pointsService.getAllPointsCreatedByUser(req));
            return ResponseEntity.ok().body(pointDTO);
        });
    }
}
