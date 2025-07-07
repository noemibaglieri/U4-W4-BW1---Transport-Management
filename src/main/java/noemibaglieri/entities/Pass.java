package noemibaglieri.entities;

import jakarta.persistence.*;
import noemibaglieri.enums.PassType;

import java.time.LocalDate;

@Entity
@Table(name = "passes")

public class Pass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "pass_id")
    private Long passId;
    @Column(name= "pass_type")
    private PassType passType;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;


     @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;


    public Pass () {}

    public Pass(Card card, LocalDate endDate, LocalDate startDate) {
        this.card = card;
        this.endDate = endDate;
        this.startDate = startDate;
    }


    public Long getPassId() {
        return passId;
    }

    public PassType getPassType() {
        return passType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Card getCard() {
        return card;
    }

    public void setPassType(PassType passType) {
        this.passType = passType;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
