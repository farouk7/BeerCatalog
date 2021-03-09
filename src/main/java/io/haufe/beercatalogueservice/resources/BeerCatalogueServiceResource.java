package io.haufe.beercatalogueservice.resources;


import io.haufe.beercatalogueservice.models.Beers;
import io.haufe.beercatalogueservice.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/catalog")
public class BeerCatalogueServiceResource {
    @Autowired
    BeerRepository beerRepository;

    @GetMapping("/beers")
    public ResponseEntity<List<Beers>> getAllBeers(@RequestParam(required = false)String name) {
        try {
            List<Beers> beers = new ArrayList<Beers>();

            if (name == null)
                beerRepository.findAll().forEach(beers::add);


            if (beers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(beers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/beers/{id}")
    public ResponseEntity<Beers> getTutorialById(@PathVariable("id") long id) {
        Optional<Beers> beerData = beerRepository.findById(id);

        if (beerData.isPresent()) {
            return new ResponseEntity<>(beerData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/beers")
    public ResponseEntity<Beers> createBeer(@RequestBody Beers beer) {
        try {
            Beers _beer = beerRepository
                    .save(new Beers(beer.getName(), beer.getGraduation(), beer.getType(),beer.getDescription(),beer.getManufacturerId()));
            return new ResponseEntity<>(_beer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/beers/{id}")
    public ResponseEntity<Beers> updateBeers(@PathVariable("id") long id, @RequestBody Beers beer) {
        Optional<Beers> beerData = beerRepository.findById(id);

        if (beerData.isPresent()) {
            Beers _beer = beerData.get();
            _beer.setName(beer.getName());
            _beer.setDescription(beer.getDescription());
            _beer.setManufacturerByManufacturerId(beer.getManufacturerByManufacturerId());
            return new ResponseEntity<>(beerRepository.save(_beer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/beers/{id}")
    public ResponseEntity<HttpStatus> deleteBeers(@PathVariable("id") long id) {
        try {
            beerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/beers")
    public ResponseEntity<HttpStatus> deleteAllBeerss() {
        try {
            beerRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/beers/manufacturer")
    public ResponseEntity<List<Beers>> findByManufacturer() {
        try {
            List<Beers> beers = beerRepository.findAll();

            if (beers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(beers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/{userId}")
    public List<Beers> getBeers (@PathVariable("userId") String userId){
        return Collections.singletonList(new Beers("Estrella","6","rubia","savor tenso",1));
    }

}
