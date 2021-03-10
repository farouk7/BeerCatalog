package io.haufe.beercatalogueservice.controller.controllerImp;

import io.haufe.beercatalogueservice.controller.ManufacturerController;
import io.haufe.beercatalogueservice.models.Manufacturer;
import io.haufe.beercatalogueservice.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manufacturer")
public class ManufacturerServiceResourse implements ManufacturerController {
    @Autowired
    ManufacturerService manufacturerService;


    @Override
    @GetMapping("/manufacturers")
    public ResponseEntity<List<Manufacturer>> getManufacturers() {
        try {
            List<Manufacturer> manufacturers = manufacturerService.findAllManufacturers();

            if (manufacturers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(manufacturers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/manufacturers/{id}")
    public ResponseEntity<Optional<Manufacturer>> getManufacturerById(@PathVariable("id") Long id) {
        Optional<Manufacturer> manufacturerData = manufacturerService.findManufacturerById(id);

        if (manufacturerData.isPresent()) {
            return new ResponseEntity(manufacturerData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PostMapping("/manufacturers")
    public ResponseEntity<Manufacturer> addManufacturer(Manufacturer manufacturer) {
        try {
            Manufacturer _manufacturer = manufacturerService.saveManufacturer(manufacturer);
            return new ResponseEntity<>(_manufacturer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @DeleteMapping("/manufacturers/{id}")
    public ResponseEntity<HttpStatus> deleteManufacturer(@PathVariable("id") Long id) {
        try {
            manufacturerService.deleteManufacturer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PutMapping("/manufacturers/{id}")
    public ResponseEntity<Manufacturer> updateManufacturer(Long id, Manufacturer manufacturer) {
        Optional<Manufacturer> beerData = manufacturerService.findManufacturerById(id);

        if (beerData.isPresent()) {
            manufacturerService.updateManufacturer(manufacturer);
            return new ResponseEntity<>(manufacturer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
