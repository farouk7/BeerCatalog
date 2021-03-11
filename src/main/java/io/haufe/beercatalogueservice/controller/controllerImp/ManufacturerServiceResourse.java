package io.haufe.beercatalogueservice.controller.controllerImp;

import io.haufe.beercatalogueservice.controller.Controller;
import io.haufe.beercatalogueservice.models.Manufacturer;
import io.haufe.beercatalogueservice.repository.BeerRepository;
import io.haufe.beercatalogueservice.repository.UserRepository;
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
@RequestMapping("/manufacturer")
public class ManufacturerServiceResourse implements Controller<Manufacturer> {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    private IService<Manufacturer> manufacturerService;

    @Override
    public ResponseEntity<Collection<Manufacturer>> findAll() {
        try {
            Collection<Manufacturer> manufacturers = manufacturerService.findAll();

            if (manufacturers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(manufacturers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Manufacturer> findById(Long id) {
        Optional<Manufacturer> manufacturerData = manufacturerService.findById(id);

        if (manufacturerData.isPresent()) {
            return new ResponseEntity(manufacturerData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Manufacturer> add(Manufacturer manufacturer) {
        try {
            JSONObject jsonObject = new JSONObject();
            if (new Util(userRepository, beerRepository).checkAutorization(manufacturer.getId())) {
                Manufacturer _manufacturer = manufacturerService.saveOrUpdate(manufacturer);
                return new ResponseEntity<>(_manufacturer, HttpStatus.CREATED);
            } else
                jsonObject.put("message", "you can not delete this Beer");
            return new ResponseEntity(jsonObject.toString(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Manufacturer> update(Manufacturer manufacturer) {
        try {
            Optional<Manufacturer> manufacturerData = manufacturerService.findById(manufacturer.getId());
            JSONObject jsonObject = new JSONObject();
            if (new Util(userRepository, beerRepository).checkAutorization(manufacturer.getId())) {
                if (manufacturerData.isPresent()) {
                    manufacturerService.saveOrUpdate(manufacturer);
                    return new ResponseEntity<>(manufacturer, HttpStatus.OK);
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
            if (new Util(userRepository, beerRepository).checkAutorization(id)) {
                manufacturerService.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> invalid() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("message", "something is missing, please check everything before sending the request!!!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }
}
