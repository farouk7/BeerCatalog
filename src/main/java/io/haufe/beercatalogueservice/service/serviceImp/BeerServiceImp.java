package io.haufe.beercatalogueservice.service.serviceImp;

import io.haufe.beercatalogueservice.models.Beers;
import io.haufe.beercatalogueservice.repository.BeerRepository;
import io.haufe.beercatalogueservice.service.IService;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeerServiceImp implements IService<Beers> {
    @Autowired
    BeerRepository beerRepository;

    @Override
    public List<Beers> findAll() {
        return beerRepository.findAll();
    }

    @Override
    public Optional<Beers> findById(Long id) {
        Optional<Beers> Beers = beerRepository.findById(id);
        return Beers;
    }

    @Override
    public Beers saveOrUpdate(Beers beer) {

        return beerRepository.saveAndFlush(beer);
    }

    @Override
    public String deleteById(Long id) {

        JSONObject jsonObject = new JSONObject();
        try {
            beerRepository.deleteById(id);
            jsonObject.put("message", "Beer deleted successfully");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }



}
