package io.haufe.beercatalogueservice.service.serviceImp;

import io.haufe.beercatalogueservice.models.Manufacturer;
import io.haufe.beercatalogueservice.repository.ManufacturerRepository;
import io.haufe.beercatalogueservice.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImp implements ManufacturerService {
    @Autowired
    ManufacturerRepository manufacturerRepository;

    @Override
    public List<Manufacturer> findAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findManufacturerById(Long id) {
        Optional<Manufacturer> manufacturer = manufacturerRepository.findById(id);
        return manufacturer;
    }

    @Override
    public Manufacturer saveManufacturer(Manufacturer manufacturer) {
        if (manufacturer != null) {
            return manufacturerRepository.save(manufacturer);
        }
        return new Manufacturer();
    }

    @Override
    public String deleteManufacturer(Long id) {
        return null;
    }

    @Override
    public String updateManufacturer(Manufacturer manufacturer) {
        Long num = manufacturer.getId();
        if (manufacturerRepository.findById(num).isPresent()) {
            Manufacturer manufacturerToUpdate = new Manufacturer();
            manufacturerToUpdate.setId(manufacturer.getId());
            manufacturerToUpdate.setName(manufacturer.getName());
            manufacturerToUpdate.setNacionality(manufacturer.getNacionality());
            manufacturerRepository.save(manufacturerToUpdate);
            return "Manufacturer is updated";
        }
        return "Error to updated Manufacturer ";
    }

}
