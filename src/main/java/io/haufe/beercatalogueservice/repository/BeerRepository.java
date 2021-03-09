package io.haufe.beercatalogueservice.repository;

import io.haufe.beercatalogueservice.models.Beers;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BeerRepository extends JpaRepository<Beers,Long> {

}
