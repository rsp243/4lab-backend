package backend.repository;

import backend.model.Points;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PointsRepository extends JpaRepository<Points, Long> {
    
}
