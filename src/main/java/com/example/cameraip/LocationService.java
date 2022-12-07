package com.example.cameraip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class LocationService {
    @Autowired LocationRepository repo;

    public List<Location> listAll() {
        return repo.findAll();
    }
    private void listChildren(List<Location> list, Location location, int n) {
        String s = "";
        for (int i=1;i<=n;i++) {
            s+="--";
        }
        s += location.getName();
        Location l = Location.copyFull(repo.findById(location.getId()).get());
        l.setName(s);
        list.add(l);
        n++;
        if (location.getChildren().size() ==0) {
            return;
        }
        else {
            for (Location x:location.getChildren()) {
                listChildren(list,x,n);
            }
        }
    }

    public List<Location> listLocation(Location location) {
        List<Location> list= new ArrayList<>();
        listChildren(list,location,0);
        return list;
    }

    private void printChildren( List<FakeLocation> list,Location location, int subLevel) {
//        list.add(new FakeLocation(location.getName()));
        int newSublevel = subLevel+1;
        Set<Location> children = location.getChildren();

        for (Location subLocation:children) {
            String s = "";
            for (int i=0; i<newSublevel-1; i++) {
                s+="--";
            }
            s+=subLocation.getName();
            list.add(new FakeLocation(s));
            printChildren(list,subLocation,newSublevel);
        }
    }

    public List<FakeLocation> listAllLocation() {
        List<FakeLocation> list = new ArrayList<>();
        Location location = repo.findById(1).get();
        list.add(new FakeLocation(location.getName()));
        printChildren(list,location,1);
        return list;
    }

    public List<Location> listAllRootLocations() {
        List<Location> list = new ArrayList<>();
        for (Location l:repo.findAll()) {
            if (l.getParent() == null) list.add(l);
        }
        return list;
    }
}
