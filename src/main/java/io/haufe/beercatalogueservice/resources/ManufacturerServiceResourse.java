package io.haufe.beercatalogueservice.resources;

import io.haufe.beercatalogueservice.models.Manufacturer;
import io.haufe.beercatalogueservice.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manufacturer")
public class ManufacturerServiceResourse {
    @Autowired
    ManufacturerRepository manufacturerRepository;

    @GetMapping("/manufacturers")
    public ResponseEntity<List<Manufacturer>> getAllManufacturers(@RequestParam(required = false)String name) {
        try {
            List<Manufacturer> manufacturers = new ArrayList<Manufacturer>();

            if (name == null)
                manufacturerRepository.findAll().forEach(manufacturers::add);


            if (manufacturers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(manufacturers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   

    @PostMapping("/manufacturers")
    public ResponseEntity<Manufacturer> createManufacturer(@RequestBody Manufacturer manufacturer) {
        try {
            Manufacturer _manufacturer = manufacturerRepository
                    .save(new Manufacturer(manufacturer.getName(), manufacturer.getNacionality()));
            return new ResponseEntity<>(_manufacturer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/manufacturers/{id}")
    public ResponseEntity<Manufacturer> updateManufacturers(@PathVariable("id") long id, @RequestBody Manufacturer manufacturer) {
        Optional<Manufacturer> manufacturerData = manufacturerRepository.findById(id);

        if (manufacturerData.isPresent()) {
            Manufacturer _manufacturer = manufacturerData.get();
            _manufacturer.setName(manufacturer.getName());
            _manufacturer.setNacionality(manufacturer.getNacionality());

            return new ResponseEntity<>(manufacturerRepository.save(_manufacturer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/manufacturers/{id}")
    public ResponseEntity<HttpStatus> deleteManufacturers(@PathVariable("id") long id) {
        try {
            manufacturerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/manufacturers")
    public ResponseEntity<HttpStatus> deleteAllManufacturerss() {
        try {
            manufacturerRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/manufacturers/manufacturer")
    public ResponseEntity<List<Manufacturer>> findByManufacturer() {
        try {
            List<Manufacturer> manufacturers = manufacturerRepository.findAll();

            if (manufacturers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(manufacturers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
