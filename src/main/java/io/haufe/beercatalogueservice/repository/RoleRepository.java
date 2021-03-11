package io.haufe.beercatalogueservice.repository;

import io.haufe.beercatalogueservice.models.Role;
import io.haufe.beercatalogueservice.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
