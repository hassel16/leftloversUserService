package dhbw.leftlovers.service.uaa.service;

import dhbw.leftlovers.service.uaa.entity.Location;

import java.util.Optional;

public interface LocationService {

    Optional<Location> findByLatAndLng(Double lat, Double lng);
}
