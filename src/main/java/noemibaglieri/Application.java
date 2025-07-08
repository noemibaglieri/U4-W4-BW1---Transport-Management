package noemibaglieri;

import noemibaglieri.dao.*;
import noemibaglieri.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import noemibaglieri.enums.PassType;

import java.time.LocalDate;
import java.time.LocalTime;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("U4W4BW1");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();



        VendorsDAO vd = new VendorsDAO(em);
        UsersDAO ud = new UsersDAO(em);
        CardsDAO cd = new CardsDAO(em);
        PassDAO pd = new PassDAO(em);
        TicketDAO td = new TicketDAO(em);

        //vendors
        HumanVendor hv1 = new HumanVendor("Tabaccheria Christian", LocalTime.of(9, 30), LocalTime.of(20, 0));
        HumanVendor hv2 = new HumanVendor("Tabaccheria New Cart", LocalTime.of(7, 30), LocalTime.of(17, 0));
        MachineVendor hv3 = new MachineVendor("Macchinetta Via Dei Volsci", true);
        MachineVendor mv1 = new MachineVendor("Macchinetta Viale Le Corbusier", true);
        MachineVendor mv2 = new MachineVendor("Macchinetta Via Tiziano", false);



        // recuper0 utenti dal DB
        User user1 = ud.findById(1L);
        User user2 = ud.findById(2L);
        User user3 = ud.findById(3L);
        User user4 = ud.findById(4L);
        User user5 = ud.findById(5L);

        // recupero vendors dal DB
        Vendor vendor1 = vd.findById(1L);
        Vendor vendor2 = vd.findById(2L);
        Vendor vendor3 = vd.findById(3L);
        Vendor vendor4 = vd.findById(4L);
        Vendor vendor5 = vd.findById(5L);

        // creo cards
        Card card1 = new Card(LocalDate.of(2024, 7, 1), user1);
        Card card2 = new Card(LocalDate.of(2024, 8, 1), user2);
        Card card3 = new Card(LocalDate.of(2024, 6, 15), user3);
        Card card4 = new Card(LocalDate.of(2024, 5, 20), user4);
        Card card5 = new Card(LocalDate.of(2024, 7, 5), user5);

        cd.save(card1);
        cd.save(card2);
        cd.save(card3);
        cd.save(card4);
        cd.save(card5);







        em.close();
        emf.close();


    }

}
