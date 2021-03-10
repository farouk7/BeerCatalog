package io.haufe.beercatalogueservice.service.serviceImp;

import io.haufe.beercatalogueservice.models.Beers;
import io.haufe.beercatalogueservice.repository.BeerRepository;
import io.haufe.beercatalogueservice.service.BeerService;
import io.haufe.beercatalogueservice.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BeerServiceImp implements BeerService {
    @Autowired
    BeerRepository beerRepository;

    @Autowired
    ManufacturerService manufacturerService;

    @Override
    public List<Beers> findAllBeers() {
        return beerRepository.findAll();
    }

    @Override
    public Optional<Beers> findBeerById(Long id) {
        Optional<Beers> customer = beerRepository.findById(id);
        return customer;

    }

    @Override
    public Beers saveBeer(Beers beer) {
        if (beer != null) {
            return beerRepository.save(beer);
        }
        return new Beers();
    }

    @Override
    public String deleteBeer(Long id) {
        return null;
    }

    @Override
    public String updateBeer(Beers beer) {
        Long num = beer.getId();
        if (beerRepository.findById(num).isPresent()) {
            Beers beerToUpdate = new Beers();
            beerToUpdate.setId(beer.getId());
            beerToUpdate.setDescription(beer.getDescription());
            beerToUpdate.setGraduation(beer.getGraduation());
            manufacturerService.updateManufacturer(beer.getManufacturerByManufacturerId());
            beerRepository.save(beerToUpdate);
            return "Beer is updated";
        }
        return "Error to update beer";
    }

}
