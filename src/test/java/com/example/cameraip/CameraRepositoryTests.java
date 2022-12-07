package com.example.cameraip;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CameraRepositoryTests {
    @Autowired
    CameraRepository cameraRepo;

    @Autowired
    LocationRepository locationRepo;


    @Test
    public void testCreateCamera() {
        Location location = locationRepo.findById(10).get();
        Camera cam1 = new Camera("cam-5",location);
        Camera cam2 = new Camera("cam-6",location);
        Camera cam3 = new Camera("cam-7",location);
        Camera cam4 = new Camera("cam-8",location);
        cameraRepo.saveAll(List.of(cam1,cam2,cam3,cam4));
    }

    @Test
    public void listAll() {
        List<Camera> list = cameraRepo.findAll();
        for (Camera x:list) {
            System.out.println(x);
        }
    }
}
