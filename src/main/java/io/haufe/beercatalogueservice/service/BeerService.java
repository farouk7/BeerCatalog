package io.haufe.beercatalogueservice.service;

import io.haufe.beercatalogueservice.models.Beers;

import java.util.List;
import java.util.Optional;

public interface BeerService {

    List<Beers> findAllBeers();

    Optional<Beers> findBeerById(Long id);

    Beers saveBeer(Beers Beer);

    String deleteBeer(Long id);

    String updateBeer(Beers Beer);
}
