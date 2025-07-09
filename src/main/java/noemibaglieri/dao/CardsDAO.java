package noemibaglieri.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import noemibaglieri.entities.Card;
import noemibaglieri.exceptions.CardNotFoundException;

import java.time.LocalDate;

public class CardsDAO {
    private final EntityManager entityManager;

    public CardsDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Card newCard) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newCard);
        transaction.commit();
        System.out.println("The card for the user * " + newCard.getUser().getName() + " " + newCard.getUser().getSurname() + " * ID: " + newCard.getId() + " was successfully registered.");
    }

    public Card findById(Long cardId) {
        Card found = entityManager.find(Card.class, cardId);
        if (found == null) throw new CardNotFoundException(cardId);
        return found;
    }

    //metodo per controllare che una card sia valida
    //(controllo che la data di scadenza sia successiva a oggi e che sia attiva)
    public boolean isCardValid(Long cardId) {
        Card card = entityManager.find(Card.class, cardId);
        if (card == null) {
            System.out.println("Card con ID " + cardId + " non trovata.");
            throw new CardNotFoundException(cardId);
        }
        boolean notExpired =  card.getExpiryDate().isAfter(LocalDate.now());
        boolean isActive = card.isActive();
        if (notExpired && isActive) {
            System.out.println("La tessera è valida.");}
        else {System.out.println("La tessera NON è valida.");}
        return notExpired && isActive;
    }







}