package io.haufe.beercatalogueservice.service.serviceImp;

import io.haufe.beercatalogueservice.models.Manufacturer;
import io.haufe.beercatalogueservice.repository.ManufacturerRepository;
import io.haufe.beercatalogueservice.service.IService;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImp implements IService<Manufacturer> {
    @Autowired
    ManufacturerRepository manufacturerRepository;

    @Override
    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        Optional<Manufacturer> manufacturer = manufacturerRepository.findById(id);
        return manufacturer;
    }

    @Override
    public Manufacturer saveOrUpdate(Manufacturer manufacturer) {

        return manufacturerRepository.saveAndFlush(manufacturer);
    }

    @Override
    public String deleteById(Long id) {

        JSONObject jsonObject = new JSONObject();
        try {
            manufacturerRepository.deleteById(id);
            jsonObject.put("message", "Manufacturer deleted successfully");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();

    }


}
