package no.hvl.dat250.jpa.tutorial.creditcards;

import java.util.Collection;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private Integer number;

    @ManyToMany(mappedBy = "addresses")
    private Set<Customer> owners = new HashSet<>();

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long _id) {
        this.id = _id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String _street) {
        this.street = _street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer _number) {
        this.number = _number;
    }

    public Collection<Customer> getOwners() {
        return owners;
    }

    public void setOwners(Set<Customer> owners) {
        this.owners = owners;
    }
}
