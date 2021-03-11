package io.haufe.beercatalogueservice.repository;

import io.haufe.beercatalogueservice.models.Manufacturer;
import io.haufe.beercatalogueservice.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Users, Long>{

    @Query("FROM Users WHERE email=:email")
    Users findByEmail(@Param("email") String email);

}
