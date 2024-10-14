package no.hvl.dat250.jpa.tutorial.creditcards.driver;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import no.hvl.dat250.jpa.tutorial.creditcards.*;

public class CreditCardsMain {

    static final String PERSISTENCE_UNIT_NAME = "jpa-tutorial";

    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory(
                PERSISTENCE_UNIT_NAME); EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            createObjects(em);
            em.getTransaction().commit();
        }

    }

    private static void createObjects(EntityManager em) {
        // Create Address
        Address address = new Address();
        address.setStreet("Inndalsveien");
        address.setNumber(28);

        // Create Customer
        Customer customer = new Customer();
        customer.setName("Max Mustermann");

        // Set relationships between Customer and Address
        customer.getAddresses().add(address);
        address.getOwners().add(customer);

        // Create Bank
        Bank bank = new Bank();
        bank.setName("Pengebank");

        // Create Pincode
        Pincode pincode = new Pincode();
        pincode.setCode("123");
        pincode.setCount(1);

        // Create CreditCard 1
        CreditCard firstCard = new CreditCard();
        firstCard.setNumber(12345);
        firstCard.setCreditLimit(-10000);
        firstCard.setBalance(-5000);
        firstCard.setOwningBank(bank);
        firstCard.setPincode(pincode);

        // Create CreditCard 2
        CreditCard secondCard = new CreditCard();
        secondCard.setNumber(123);
        secondCard.setCreditLimit(2000);
        secondCard.setBalance(1);
        secondCard.setOwningBank(bank);
        secondCard.setPincode(pincode);

        // Set relationships between Customer and CreditCards
        customer.getCreditCards().add(firstCard);
        customer.getCreditCards().add(secondCard);
        firstCard.getOwners().add(customer);
        secondCard.getOwners().add(customer);

        // Persist all objects
        em.persist(address);
        em.persist(customer);
        em.persist(bank);
        em.persist(pincode);
        em.persist(firstCard);
        em.persist(secondCard);
    }
}
