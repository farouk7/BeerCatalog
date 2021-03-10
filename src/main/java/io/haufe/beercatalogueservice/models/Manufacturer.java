package io.haufe.beercatalogueservice.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Manufacturer {
    private long id;
    private String name;
    private String nacionality;
    private Collection<Beers> beersById;

    public Manufacturer() {
    }

    public Manufacturer(String name, String nacionality) {
        this.name = name;
        this.nacionality = nacionality;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "nacionality")
    public String getNacionality() {
        return nacionality;
    }

    public void setNacionality(String nacionality) {
        this.nacionality = nacionality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manufacturer that = (Manufacturer) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(nacionality, that.nacionality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, nacionality);
    }

    /*@OneToMany(mappedBy = "manufacturerByManufacturerId")
    public Collection<Beers> getBeersById() {
        return beersById;
    }*/

    public void setBeersById(Collection<Beers> beersById) {
        this.beersById = beersById;
    }
}
