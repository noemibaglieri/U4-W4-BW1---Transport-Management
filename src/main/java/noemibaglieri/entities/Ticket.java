package noemibaglieri.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tickets")

public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ticket_id")
    private Long ticketId;
    private double price;

     @Column(name="date_of_purchase")
    private LocalDate dateOfPurchase;
     @Column(name="isValidated")
     private boolean isValidated;

     @ManyToOne
     @JoinColumn(name = "vendor_id")
     private Vendor vendor;

     @ManyToOne
     @JoinColumn(name="vehicle_id")
     private Vehicle vehicle;

     public Ticket() {}

    public Long getTicketId() {
        return ticketId;
    }



    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public boolean isValidated() {
        return isValidated;
    }

    public void setValidated(boolean validated) {
        isValidated = validated;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", price=" + price +
                ", dateOfPurchase=" + dateOfPurchase +
                ", isValidated=" + isValidated +
                '}';
    }
}
