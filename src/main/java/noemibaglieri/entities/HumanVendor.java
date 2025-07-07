package noemibaglieri.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalTime;

@Entity
@Table(name= "human_vendors")


public class HumanVendor extends Vendor{

    @Column(name= "shop_name")
    private String shopName;
    @Column(name="opening_time")
    private LocalTime openingTime;
    @Column(name="closing_time")
    private LocalTime closingTime;

    public HumanVendor() {}


    public HumanVendor(String shopName, LocalTime openingTime, LocalTime closingTime) {
        this.shopName = shopName;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
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
                "shopName='" + shopName + '\'' +
                ", openingTime=" + openingTime +
                ", closingTime=" + closingTime +
                "vendorId=" + vendorId +

                '}';
    }
}
