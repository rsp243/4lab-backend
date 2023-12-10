package backend.services;

import org.springframework.stereotype.Service;

import backend.DTO.PointsCreatedDTO;
import backend.DTO.PointsDTO;
import backend.DTO.PointsDeletedDTO;
import backend.DTO.TokenDTO;
import backend.exceptions.DoesNotExistException;
import backend.exceptions.TokenNotPassedException;
import backend.model.Points;
import backend.model.Users;
import backend.repository.PointsRepository;
import backend.repository.UserRepository;
import backend.security.JwtUtils;
import io.jsonwebtoken.Claims;
// import backend.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.LinkedList;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.lang.Math.abs;

@Service
@Slf4j
@RequiredArgsConstructor
public class PointsService {
    private final PointsRepository pointsRepository;
    // private final AuthentificatedMap authentificatedMap;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final AuthService authService;

    public PointsCreatedDTO addPoint(PointsDTO req) throws DoesNotExistException {
        final long userId = authService.getUserIdFromToken(req.getToken().getToken());
        
        final long startExec = System.nanoTime();
        final boolean result = getResult(req.getX(), req.getY(), req.getR());
        final long endExec = System.nanoTime();
        final int executionTime = (int) (endExec - startExec);

        Points point = Points.builder()
                .x(req.getX())
                .y(req.getY())
                .r(req.getR())
                .isHit(result)
                .currentTime(LocalDateTime.now())
                .executionTime(executionTime)
                .userId(userId)
                .build();
        
        
        pointsRepository.save(point);
        return new PointsCreatedDTO(
            point.getX(),
            point.getY(),
            point.getR(),
            point.isHit(),
            point.getFormattedCurrentTime(),
            point.getExecutionTime()
            /*, point.getUserId() */ );
    }

    public boolean checkArea(float xValue, float yValue, float rValue) {
        boolean inCircle = checkCircle(xValue, yValue, rValue);
        boolean inTriangle = checkTriangle(xValue, yValue, rValue);
        boolean inReсtangle = checkRectangle(xValue, yValue, rValue);
        return inCircle || inTriangle || inReсtangle;
    }

    private static boolean checkCircle(final float xValue, final float yValue, final float rValue) {
        return xValue > 0 && yValue > 0 && sqrt(pow(xValue, 2) + pow(yValue, 2)) <= rValue / 2;
    }

    private static boolean checkTriangle(final float xValue, final float yValue, final float rValue) {
        return xValue <= 0 && yValue <= 0 && abs(xValue) + abs(yValue) / 2 <= rValue / 2;
    }

    private static boolean checkRectangle(final float xValue, final float yValue, final float rValue) {
        return xValue <= 0 && yValue >= 0 && abs(xValue) <= rValue && yValue <= rValue / 2;
    }

    private static boolean validateXYR(float x, float y, float r) {
        return x >= -2 && x <= 2 && y >= -5 && y <= 5 && r >= -2 && r <= 2;
    }

    public boolean getResult(float x, float y, float r) {
        return validateXYR(x, y, r) && checkArea(x, y, r);
    }

    public void deletePoint(long pointId) {
        pointsRepository.deleteById(pointId);
    }

    public List<Points> getAllPointsCreatedByUser(TokenDTO req) throws DoesNotExistException {
        final long userId = authService.getUserIdFromToken(req.getToken());
        List<Points> pointsList = pointsRepository.getAllByUserId(userId);
        return pointsList;
    }

    public PointsDeletedDTO deleteAllPoints(List<Points> pointsList) {
        pointsRepository.deleteAll(pointsList);

        return new PointsDeletedDTO("Deleted successfully.");
    } 
}
