package com.example.cameraip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CameraRestController {
    @Autowired CameraRepository cameraRepo;

    @Autowired LocationRepository locationRepo;

    @GetMapping("/cameras")
    public List<Camera> listCameras() {
        return  cameraRepo.findAll();
    }

    @PostMapping("/cameras")
    public Camera addCamera(@RequestBody CameraDTO cam) {
        Location location = locationRepo.findByName(cam.getLocation());
        Camera c = new Camera(cam.getName(),location);
        Camera sCamera = cameraRepo.save(c);
        return sCamera;
    }

    @PutMapping("/cameras/{id}")
    public Camera updateCamera(@RequestBody CameraDTO cam, @PathVariable(name = "id") Integer id) {
        Camera c = cameraRepo.findById(id).get();
        Location location = locationRepo.findByName(cam.getLocation());
        c.setName(cam.getName());
        c.setLocation(location);
        Camera sCamera = cameraRepo.save(c);
        return sCamera;
    }

    @DeleteMapping("/cameras/{id}")
    public void deleteCamera(@PathVariable(name = "id") Integer id) {
        Camera c = cameraRepo.findById(id).get();
        cameraRepo.delete(c);
    }
}
