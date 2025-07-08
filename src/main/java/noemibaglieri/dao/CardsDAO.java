package noemibaglieri.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import noemibaglieri.entities.Card;
import noemibaglieri.exceptions.CardNotFoundException;

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
}