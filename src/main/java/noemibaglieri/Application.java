package noemibaglieri;

import noemibaglieri.dao.*;
import noemibaglieri.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalTime;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("U4W4BW1");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager(); // <-- crei EntityManager
        VendorsDAO vd = new VendorsDAO(em);
        UsersDAO ud = new UsersDAO(em);
        CardsDAO cd = new CardsDAO(em);

        HumanVendor hv1 = new HumanVendor("Tabaccheria Christian", LocalTime.of(9, 30), LocalTime.of(20,0));
        HumanVendor hv2 = new HumanVendor("Tabaccheria New Cart", LocalTime.of(7, 30), LocalTime.of(17,0));
        MachineVendor hv3 = new MachineVendor("Macchinetta Via Dei Volsci", true);
        MachineVendor mv1 = new MachineVendor("Macchinetta Viale Le Corbusier", true);
        MachineVendor mv2 = new MachineVendor("Macchinetta Via Tiziano", false);

        // Salva il vendor nel database
        /*
        vd.save(hv1);
        vd.save(hv2);
        vd.save(hv3);
        vd.save(mv1);
        vd.save(mv2);
         */

        User user1 = new User("Daenerys", "Targaryen", LocalDate.of(1995,1,14));
        User user2 = new User("Cersei", "Lannister", LocalDate.of(1978, 9, 11));
        User user3 = new User("Obara", "Martell", LocalDate.of(2001, 3, 17));
        User user4 = new User("Olenna", "Tyrell", LocalDate.of(1940, 12, 31));
        User user5 = new User("Arya", "Stark", LocalDate.of(2004, 12, 11));

        /*
        ud.save(user1);
        ud.save(user2);
        ud.save(user3);
        ud.save(user4);
        ud.save(user5);
         */

        em.close();
        emf.close();
    }
}
