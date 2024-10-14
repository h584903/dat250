package no.hvl.dat250.jpa.tutorial.creditcards;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Bank() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "owningBank", cascade = CascadeType.ALL) // 'owningBank' should match the field name in
                                                                   // CreditCard
    private Set<CreditCard> ownedCards = new HashSet<>(); // Use 'ownedCards' instead of 'creditCards'

    public Collection<CreditCard> getOwnedCards() {
        return ownedCards;
    }
}
