package io.haufe.beercatalogueservice.models;



import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;
@Entity
public class Role {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(targetEntity = Users.class, mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Users> users;

    public Role() {
    }

    public Role(Long id, @NotNull String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
