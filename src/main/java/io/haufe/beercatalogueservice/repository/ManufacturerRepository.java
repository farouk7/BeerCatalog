package io.haufe.beercatalogueservice.repository;

import io.haufe.beercatalogueservice.models.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

}
