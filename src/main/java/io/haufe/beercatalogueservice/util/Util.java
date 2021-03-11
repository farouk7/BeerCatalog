package io.haufe.beercatalogueservice.util;

import io.haufe.beercatalogueservice.models.Beers;
import io.haufe.beercatalogueservice.models.Users;
import io.haufe.beercatalogueservice.repository.BeerRepository;
import io.haufe.beercatalogueservice.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Util {


    private final UserRepository usersService;

    private final BeerRepository beersRepository;

    public Util(UserRepository usersService, BeerRepository beersRepository) {
        this.usersService = usersService;
        this.beersRepository = beersRepository;
    }

    public boolean checkAutorization(Long id) {
        Beers beer = this.beersRepository.findBeerById(id);
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final Users CurrentUsers = this.usersService.findByEmail(auth.getPrincipal().toString());
        return CurrentUsers.getRole().getName().equals("admin") || (CurrentUsers.getRole().getName().equals("user") && CurrentUsers.getManufacturer().getId() == beer.getManufacturerByManufacturerId().getId());
    }
}
