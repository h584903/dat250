package no.hvl.dat250.jpa.tutorial.creditcards;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer number;
    private Integer creditLimit;
    private Integer balance;

    public CreditCard() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Integer creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @ManyToOne
    @JoinColumn(name = "pincode_id")
    private Pincode pincode;

    public Pincode getPincode() {
        return pincode;
    }

    public void setPincode(Pincode _pincode) {
        this.pincode = _pincode;
    }

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank owningBank;

    public Bank getOwningBank() {
        return owningBank;
    }

    public void setOwningBank(Bank _bank) {
        this.owningBank = _bank;
    }

    @ManyToMany(mappedBy = "creditCards")
    private Set<Customer> owners = new HashSet<>(); // Add customers relationship

    public Set<Customer> getOwners() {
        return owners;
    }

    public void setOwners(Set<Customer> customers) {
        this.owners = customers;
    }
}
