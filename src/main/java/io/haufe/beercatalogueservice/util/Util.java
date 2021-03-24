package io.haufe.beercatalogueservice.util;

import io.haufe.beercatalogueservice.models.Beer;
import io.haufe.beercatalogueservice.models.Users;
import io.haufe.beercatalogueservice.service.IService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Util {


   // private final UserRepository usersService;

    //private final BeerRepository beersRepository;

    private final IService<Beer> beerService;
    private final IService<Users> usersService;





    public Util(IService<Users> userService, IService<Beer> beerService) {
        this.usersService = userService;
        this.beerService = beerService;
    }

    public boolean checkAutorization(Long id) {

        //Beer beer = this.beersRepository.findBeerById(id);
        Optional<Beer> beer = this.beerService.findById(id);
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final Users CurrentUsers = this.usersService.findbyString(auth.getPrincipal().toString());

        return CurrentUsers.getRole().getName().equals("admin") || (CurrentUsers.getRole().getName().equals("user") && CurrentUsers.getManufacturer().getId() == beer.get().getManufacturerByManufacturerId().getId());
    }
}
