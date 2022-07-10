package com.trifectatravel.travel_api.controllers;

import com.trifectatravel.travel_api.entities.TravelPackage;
import com.trifectatravel.travel_api.repositories.TravelPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/travelpackage")
public class TravelPackageController {

    @Autowired
    private TravelPackageRepository travelPackageRepository;

    public TravelPackageController(final TravelPackageRepository travelPackageRepository){
        this.travelPackageRepository = travelPackageRepository;
    }

    @GetMapping("/all")
    public Iterable<TravelPackage> getAllTravelPackages() {
        return this.travelPackageRepository.findAll();
    }

    // curl localhost:4001/travelpackage/2
    @GetMapping("/{id}")
    public Optional<TravelPackage> getTravelPackageById(@PathVariable("id") Integer id) {

        return this.travelPackageRepository.findById(id);
    }

    // curl -X POST -d '{"name": "TravisPlant", "quantity": 3, "wateringFrequency": 5, "hasFruit": true}' -H "Content-Type:application/json" localhost:4001/plants
    @PostMapping("/addtravelpackage")
    public TravelPackage createNewTravelPackage(@RequestBody TravelPackage travelPackage) {
        return this.travelPackageRepository.save(travelPackage);
    }

    // curl -X PUT -d '{"quantity": 36, "wateringFrequency": 3}' -H "Content-Type:application/json" localhost:4001/plants/2
    @PutMapping("/plants/{id}")
    public TravelPackage updateTravelPackage(@PathVariable("id") Integer id, @RequestBody TravelPackage p) {
        Optional<TravelPackage> packageToUpdateOptional = this.travelPackageRepository.findById(id);
        if (!packageToUpdateOptional.isPresent()) {
            return null;
        }

        TravelPackage packageToUpdate = packageToUpdateOptional.get();
         packageToUpdate.setTripName(p.getTripName() != null ? p.getTripName() : packageToUpdate.getTripName());
         packageToUpdate.setDescription(p.getDescription() != null ? p.getDescription() : packageToUpdate.getDescription());
         packageToUpdate.setImageFilePath(p.getImageFilePath() != null ? p.getImageFilePath() : packageToUpdate.getImageFilePath());

        TravelPackage updatedPackage = this.travelPackageRepository.save(packageToUpdate);
        return updatedPackage;
    }

    @DeleteMapping("/{id}")
    public TravelPackage deleteTravelPackage(@PathVariable("id") Integer id) {
        Optional<TravelPackage> travelPackageToDeleteOptional = this.travelPackageRepository.findById(id);
        if (!travelPackageToDeleteOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Package not found for id=%d ".formatted(id));

        }
        TravelPackage travelPackageToDelete = travelPackageToDeleteOptional.get();
        this.travelPackageRepository.delete(travelPackageToDelete);
        return travelPackageToDelete;
    }




}
