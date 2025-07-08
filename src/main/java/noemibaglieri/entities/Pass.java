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
    @Enumerated(EnumType.STRING)
    private PassType passType;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name="vendor_id")
    private Vendor vendor;

     @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;


    public Pass () {}

    public Pass(Card card,LocalDate startDate, PassType passType) {
        this.card = card;
        this.startDate = startDate;
        this.passType = passType;
        if (passType == PassType.MONTHLY)  {
            this.endDate = startDate.plusDays(30);}
        else  {
            this.endDate = startDate.plusDays(7);
        }
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
