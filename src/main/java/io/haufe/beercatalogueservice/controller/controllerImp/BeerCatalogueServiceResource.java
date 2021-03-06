package io.haufe.beercatalogueservice.controller.controllerImp;

import io.haufe.beercatalogueservice.controller.Controller;
import io.haufe.beercatalogueservice.models.Beer;
import io.haufe.beercatalogueservice.models.Users;
import io.haufe.beercatalogueservice.service.IService;
import io.haufe.beercatalogueservice.util.Util;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/Beers")
public class BeerCatalogueServiceResource implements Controller<Beer> {
    @Autowired
    IService<Beer> beerService;
    @Autowired
    IService<Users> userService;



    @Override
    public ResponseEntity<Collection<Beer>> findAll() {
        try {
            Collection<Beer> beers = beerService.findAll();
            if (beers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(beers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Beer> findById(Long id) {
        Optional<Beer> beerData = beerService.findById(id);
        if (beerData.isPresent()) {
            return new ResponseEntity(beerData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Beer> add(Beer beer) {
        try {
            JSONObject jsonObject = new JSONObject();
            if (new Util(userService, beerService).checkAutorization(beer.getId())) {
                Beer _beer = beerService.saveOrUpdate(beer);
                return new ResponseEntity<>(_beer, HttpStatus.CREATED);
            } else
                jsonObject.put("message", "you can not delete this Beer");
            return new ResponseEntity(jsonObject.toString(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Beer> update(Beer beer) {
        try {
            JSONObject jsonObject = new JSONObject();
            if (new Util(userService, beerService).checkAutorization(beer.getId())) {
                Optional<Beer> beerData = beerService.findById(beer.getId());
                if (beerData.isPresent()) {
                    beerService.saveOrUpdate(beer);
                    return new ResponseEntity<>(beer, HttpStatus.OK);
                } else {
                    jsonObject.put("message", "you can not update this Beer");
                    return new ResponseEntity(jsonObject.toString(), HttpStatus.UNAUTHORIZED);
                }
            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (JSONException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        try {
            JSONObject jsonObject = new JSONObject();
            Optional<Beer> beer = beerService.findById(id);
            if (beer.isPresent()) {
                if (new Util(userService, beerService).checkAutorization(id)) {
                    beerService.deleteById(id);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else
                    jsonObject.put("message", "you can not delete this Beer");
                return new ResponseEntity<>(jsonObject.toString(), HttpStatus.UNAUTHORIZED);
            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (JSONException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> invalid() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("message", "something is missing, please check everything before sending the request!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }


}

