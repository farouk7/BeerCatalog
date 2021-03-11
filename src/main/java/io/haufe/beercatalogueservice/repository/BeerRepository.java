package io.haufe.beercatalogueservice.repository;

import io.haufe.beercatalogueservice.models.Beers;
import io.haufe.beercatalogueservice.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BeerRepository extends JpaRepository<Beers, Long> {

    @Query("FROM Beers WHERE id=:id")
    Beers findBeerById(@Param("id") Long id);
}
