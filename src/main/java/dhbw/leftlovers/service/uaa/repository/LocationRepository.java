package dhbw.leftlovers.service.uaa.repository;

import dhbw.leftlovers.service.uaa.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {

    Optional<Location> findByLatAndLng(Double lat, Double lng);
}
