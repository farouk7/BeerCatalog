package io.haufe.beercatalogueservice.controller;

import io.haufe.beercatalogueservice.models.Manufacturer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ManufacturerController {
    ResponseEntity<List<Manufacturer>> getManufacturers();

    ResponseEntity<Optional<Manufacturer>> getManufacturerById(Long id);

    ResponseEntity<Manufacturer> addManufacturer(Manufacturer manufacturer);

    ResponseEntity<HttpStatus> deleteManufacturer(Long id);

    ResponseEntity<Manufacturer> updateManufacturer(Long id, Manufacturer manufacturer);
}
