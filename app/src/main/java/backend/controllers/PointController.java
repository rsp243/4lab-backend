package backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.DTO.PointsCreatedDTO;
import backend.DTO.PointsDTO;
import backend.DTO.TokenDTO;
import backend.DTO.UsersDTO;
import backend.exceptions.ApiException;
import backend.model.validators.PointsValidator;
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

    @PostMapping(path = "/add")
    public ResponseEntity<?> addPoint(@RequestBody PointsDTO req) {
        PointsValidator validator = new PointsValidator(jwtUtils);
        
        return ControllerExecutor.execute(validator, () -> {
            PointsCreatedDTO pointDTO = pointsService.addPoint(req);
            return ResponseEntity.ok().body(pointDTO);
        });
    }
}
