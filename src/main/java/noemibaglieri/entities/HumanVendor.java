package noemibaglieri.entities;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name= "human_vendors")
@DiscriminatorValue("Human")
public class HumanVendor extends Vendor{

    @Column(name="opening_time")
    private LocalTime openingTime;
    @Column(name="closing_time")
    private LocalTime closingTime;

    public HumanVendor() {}

    public HumanVendor(String name, LocalTime openingTime, LocalTime closingTime) {
        super(name);
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    @Override
    public String toString() {
        return "HumanVendor{" +
                "shopName='" + name + '\'' +
                ", openingTime=" + openingTime +
                ", closingTime=" + closingTime +
                "vendorId=" + vendorId +

                '}';
    }
}
