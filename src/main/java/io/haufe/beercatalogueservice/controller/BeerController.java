package io.haufe.beercatalogueservice.controller;

import io.haufe.beercatalogueservice.models.Beers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface BeerController {
    ResponseEntity<List<Beers>> findAllBeers();

    ResponseEntity<Optional<Beers>> getBeerById(Long id);

    ResponseEntity<Beers> addBeer(Beers Beer);

    ResponseEntity<HttpStatus> deleteBeer(Long id);

    ResponseEntity<Beers> updateBeer(long id, Beers Beer);

}
