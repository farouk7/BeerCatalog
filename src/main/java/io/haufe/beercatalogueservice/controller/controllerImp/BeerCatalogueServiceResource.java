package io.haufe.beercatalogueservice.controller.controllerImp;


import io.haufe.beercatalogueservice.controller.BeerController;
import io.haufe.beercatalogueservice.models.Beers;
import io.haufe.beercatalogueservice.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/catalog")
public class BeerCatalogueServiceResource implements BeerController {
    @Autowired
    BeerService beerService;

    @Override
    @GetMapping("/beers")
    public ResponseEntity<List<Beers>> findAllBeers() {
        try {
            List<Beers> beers = beerService.findAllBeers();

            if (beers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(beers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/beers/{id}")
    public ResponseEntity<Optional<Beers>> getBeerById(@PathVariable("id") Long id) {
        Optional<Beers> beerData = beerService.findBeerById(id);

        if (beerData.isPresent()) {
            return new ResponseEntity(beerData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PostMapping("/beers")
    public ResponseEntity<Beers> addBeer(Beers beer) {
        try {
            Beers _beer = beerService.saveBeer(beer);
            return new ResponseEntity<>(_beer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @DeleteMapping("/beers/{id}")
    public ResponseEntity<HttpStatus> deleteBeer(@PathVariable("id") Long id) {
        try {
            beerService.deleteBeer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PutMapping("/beers/{id}")
    public ResponseEntity<Beers> updateBeer(@PathVariable("id") long id, Beers beer) {
        Optional<Beers> beerData = beerService.findBeerById(id);

        if (beerData.isPresent()) {
            beerService.updateBeer(beer);
            return new ResponseEntity<>(beer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
