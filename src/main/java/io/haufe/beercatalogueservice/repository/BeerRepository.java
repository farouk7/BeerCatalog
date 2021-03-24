package io.haufe.beercatalogueservice.repository;

import io.haufe.beercatalogueservice.models.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BeerRepository extends JpaRepository<Beer, Long> {

    @Query("FROM Beer WHERE id=:id")
    Beer findBeerById(@Param("id") Long id);
}
