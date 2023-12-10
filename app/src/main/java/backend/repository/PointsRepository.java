package backend.repository;

import java.util.List;

import backend.model.Points;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PointsRepository extends JpaRepository<Points, Long> {
    List<Points> getAllByUserId(long userId);
}
