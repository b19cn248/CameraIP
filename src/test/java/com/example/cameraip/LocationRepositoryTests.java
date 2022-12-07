package com.example.cameraip;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class LocationRepositoryTests {

    @Autowired
    LocationRepository locationRepo;

    @Test
    public void testCreateLocation() {
//        Location location = new Location("Tang 1 ");
//        locationRepo.save(location);

        Location l = locationRepo.findById(5).get();
        Location l1 = new Location("VT1",l);
        Location l2 = new Location("VT2",l);
        Location l3 = new Location("VT3",l);
        locationRepo.saveAll(List.of(l1,l2,l3));
    }

    private void printChildren(Location location, int subLevel) {
        int newSublevel = subLevel+1;
        Set<Location> children = location.getChildren();

        for (Location subLocation:children) {
            for (int i=0; i<newSublevel; i++) {
                System.out.print("--");
            }
            System.out.println(subLocation.getName());
            printChildren(subLocation,newSublevel);
        }
    }

    @Test
    public void testPrint(){
        printChildren(locationRepo.findById(1).get(),1);
    }
}
