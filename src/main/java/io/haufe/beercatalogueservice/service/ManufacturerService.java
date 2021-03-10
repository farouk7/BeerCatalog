package io.haufe.beercatalogueservice.service;

import io.haufe.beercatalogueservice.models.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {
    List<Manufacturer> findAllManufacturers();

    Optional<Manufacturer> findManufacturerById(Long id);

    Manufacturer saveManufacturer(Manufacturer manufacturer);

    String deleteManufacturer(Long id);

    String updateManufacturer(Manufacturer manufacturer);
}
