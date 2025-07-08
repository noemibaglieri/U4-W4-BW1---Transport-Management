package noemibaglieri.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name= "cards")

public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "card_id")
    private Long cardId;
    @Column(name = "issue_date")
    private LocalDate issueDate;
    @Column(name = "expiry_date")
    private LocalDate expiryDate;
    @Column (name = "is_active")
    private boolean isActive;

    @OneToOne(mappedBy = "card")
    private User user;

    @OneToMany(mappedBy = "card")
     private List<Pass> passes;

    public Card () {}


    public Card(LocalDate issueDate,  User user) {
        this.issueDate= issueDate;
        this.expiryDate = expiryDate;
        this.isActive = isActive;
        this.user = user ;
    }

    public Long getId() {
        return cardId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public User getUser() {
        return user;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", issueDate=" + issueDate +
                ", expiryDate=" + expiryDate +
                ", isActive=" + isActive +
                ", user=" + user +
                '}';
    }
}
