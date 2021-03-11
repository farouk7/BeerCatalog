package io.haufe.beercatalogueservice.ControllerTests;

import io.haufe.beercatalogueservice.models.Beers;
import io.haufe.beercatalogueservice.service.IService;
import io.haufe.beercatalogueservice.service.serviceImp.BeerServiceImp;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class BeersControllerTests {
/*
        @Mock
        private RestTemplate restTemplate;

        @InjectMocks
        private IService<Beers> beerService ;

        @Test
        @WithMockUser(username = "", password = "", roles = "")
        public void testGetBeers() {
            Long id = Long.valueOf(1);
            Beers _beer = new Beers();
            Mockito.when(restTemplate.getForEntity("http://localhost:8081/Beers/1", Beers.class))
          .thenReturn(new ResponseEntity(_beer, HttpStatus.OK));

            Optional<Beers> beer = beerService.findById(id);
            Assert.assertEquals(_beer, beer);
        }*/
    }

