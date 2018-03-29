package dhbw.leftlovers.service.uaa.service;

import dhbw.leftlovers.service.uaa.entity.Location;
import dhbw.leftlovers.service.uaa.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Optional<Location> findByLatAndLng(Double lat, Double lng) {
        return locationRepository.findByLatAndLng(lat,lng);
    }
}
