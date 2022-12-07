package com.example.cameraip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class LocationRestController {
    @Autowired LocationService sv;
    @Autowired LocationRepository localtionRepo;


    @GetMapping("/locations")
    public List<Location> listLocations() {
        return sv.listAll();
    }

    @PostMapping("/locations")
    public Location addLocations(@RequestBody LocationDTO location) {
        Location l = localtionRepo.findByName(location.getParent());
        Location l1 = new Location(location.getName(),l);
        Location savedLocation = localtionRepo.save(l1);
        return savedLocation;
    }

    @PutMapping("/locations/{id}")
    public Location updateLocations(@RequestBody LocationDTO location, @PathVariable(name = "id") Integer id) {
        Location l = localtionRepo.findById(id).get();
        l.setName(location.getName());
        l.setParent(localtionRepo.findByName(location.getParent()));
        Location savedLocation = localtionRepo.save(l);
        return savedLocation;
    }

    @DeleteMapping("/locations/{id}")
    public void deleteLocation(@PathVariable(name = "id") Integer id) {
        Location l = localtionRepo.findById(id).get();
        localtionRepo.delete(l);
    }

    @GetMapping("/locations/{id}")
    public Set<Location> getChildrenOfLocation(@PathVariable(name = "id") Integer id) {
        Location l = localtionRepo.findById(id).get();
        return l.getChildren();
    }

    @GetMapping("/locationsParent")
    public List<Location> listRootLocations() {
        return sv.listAllRootLocations();
    }
}
