package io.haufe.beercatalogueservice.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Beer {
    private long id;
    private String name;
    private String graduation;
    private String type;
    private String description;
    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturerByManufacturerId;

    public Beer() {

    }

    public Beer(String name, String graduation, String type, String description, Manufacturer manufacturerByManufacturerId) {
        this.name = name;
        this.graduation = graduation;
        this.type = type;
        this.description = description;
        this.manufacturerByManufacturerId = manufacturerByManufacturerId;
    }

    public Beer(String name, String graduation, String type, String description) {
        this.name = name;
        this.graduation = graduation;
        this.type = type;
        this.description = description;
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
    @Column(name = "graduation")
    public String getGraduation() {
        return graduation;
    }

    public void setGraduation(String graduation) {
        this.graduation = graduation;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beer beer = (Beer) o;
        return id == beer.id &&
                Objects.equals(name, beer.name) &&
                Objects.equals(graduation, beer.graduation) &&
                Objects.equals(type, beer.type) &&
                Objects.equals(description, beer.description) ;

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, graduation, type, description);
    }

    @ManyToOne
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id", insertable = false, updatable = false)
    public Manufacturer getManufacturerByManufacturerId() {
        return manufacturerByManufacturerId;
    }

    public void setManufacturerByManufacturerId(Manufacturer manufacturerByManufacturerId) {
        this.manufacturerByManufacturerId = manufacturerByManufacturerId;
    }
}
