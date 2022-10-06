import dao.AdresDAOHibernate;
import dao.OVChipkaartDAOHibernate;
import dao.ProductDAOHibernate;
import dao.ReizigerDAOHibernate;
import domein.Adres;
import domein.OVChipkaart;
import domein.Product;
import domein.Reiziger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Testklasse - deze klasse test alle andere klassen in deze package.
 *
 * System.out.println() is alleen in deze klasse toegestaan (behalve voor exceptions).
 *
 * @author tijmen.muller@hu.nl
 */
public class Main {
    // Creëer een factory voor Hibernate sessions.
    private static final SessionFactory factory;

    static {
        try {
            // Create a Hibernate session factory
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Retouneer een Hibernate session.
     *
     * @return Hibernate session
     * @throws HibernateException
     */
    private static Session getSession() throws HibernateException {
        return factory.openSession();
    }

    public static void main(String[] args) throws SQLException {
        testFetchAll();
    }

    /**
     * P6. Haal alle (geannoteerde) entiteiten uit de database.
     */
    private static void testFetchAll() {
        String gbdatum = "2018-05-31";
        Reiziger sietske = new Reiziger(3, "SAA", "", "Boers", java.sql.Date.valueOf(gbdatum));
        Reiziger sietske1 = new Reiziger(99, "SAA", "", "Boers", java.sql.Date.valueOf(gbdatum));
        Adres adres = new Adres(102, "3405CM", "363", "beneden", "benschop", sietske);
        List<Product> producten = new ArrayList<>();
        List<OVChipkaart> ovChipkaarten = new ArrayList<>();
        OVChipkaart ovchip = new OVChipkaart(80, java.sql.Date.valueOf("2022-01-01"), 1, 27.00, sietske, producten);
        OVChipkaart ovchip1 = new OVChipkaart(90537, java.sql.Date.valueOf("2022-01-01"), 1, 27.00, sietske, producten);
        ovChipkaarten.add(ovchip);
        Product product = new Product(18, "passies123", "test pas", 1, ovChipkaarten);
        producten.add(product);
        //        sietske.setAdres(adres);
        Session session = getSession();
        AdresDAOHibernate adresDAOHibernate = new AdresDAOHibernate(session);
        ReizigerDAOHibernate reizigerDAOHibernate = new ReizigerDAOHibernate(session);
//        reizigerDAOHibernate.save(sietske);
        OVChipkaartDAOHibernate ovChipkaartDAOHibernate = new OVChipkaartDAOHibernate(session);
//        ovChipkaartDAOHibernate.save(ovchip);
//        System.out.println("find by geboortedatum");
//        System.out.println(reizigerDAOHibernate.findByGbdatum(Date.valueOf(gbdatum)));



        ProductDAOHibernate productDAOHibernate = new ProductDAOHibernate(session);
//        System.out.println(productDAOHibernate.save(product));
//        System.out.println(ovChipkaartDAOHibernate.save(ovchip));
//        System.out.println("delete ovchip");
//        System.out.println(ovChipkaartDAOHibernate.delete(ovchip));
//        System.out.println("delete product");
//        System.out.println(productDAOHibernate.delete(product));
        //        productDAOHibernate.save(product);
//        productDAOHibernate.update(product);
        System.out.println("ALL FINDBYS");

        System.out.println("find all reiziger");
        System.out.println(reizigerDAOHibernate.findAll());
        System.out.println("find reiziger by gbdatum");
        System.out.println(reizigerDAOHibernate.findByGbdatum(Date.valueOf(gbdatum)));
        System.out.println("find reiziger by id");
        System.out.println(reizigerDAOHibernate.findById(sietske.getId()));
        System.out.println("find all adres");
        System.out.println(adresDAOHibernate.findAll());
        System.out.println("find adres by reiziger");
        System.out.println(adresDAOHibernate.findByReiziger(sietske));
        System.out.println("find all product");
        System.out.println(productDAOHibernate.findAll());
        System.out.println("find product by ovchip");
        System.out.println(productDAOHibernate.findByOvchipkaart(ovchip));
        System.out.println("findall ovchip");
        System.out.println(ovChipkaartDAOHibernate.findAll());
        System.out.println("find by sietske");
        System.out.println(ovChipkaartDAOHibernate.findByReiziger(sietske));


        //        reizigerDAOHibernate.delete(sietske);
//            reizigerDAOHibernate.update(sietske);
//        System.out.println(reizigerDAOHibernate.findById(134));
//        System.out.println(reizigerDAOHibernate.findAll());
//        System.out.println(reizigerDAOHibernate.findByGbdatum(Date.valueOf(gbdatum)));
//        adresDAOHibernate.save(adres);
//        System.out.println(reizigerDAOHibernate.findAll());
//        System.out.println(reizigerDAOHibernate.findByGbdatum(Date.valueOf(gbdatum)));
//       adresDAOHibernate.save(adres);
//        System.out.println(adresDAOHibernate.findByReiziger(sietske));
//        System.out.println("Find By Reiziger:");
//       adresDAOHibernate.findByReiziger(sietske);
//        System.out.println("Find all:");
//        System.out.println(adresDAOHibernate.findAll());

        session.close();

    }
}