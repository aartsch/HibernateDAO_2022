package dao;

import domein.OVChipkaart;
import domein.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOHibernate extends BaseDAOHibernate implements ProductDAO {
    public ProductDAOHibernate(Session sess) {
        super(sess);
    }

    @Override
    public boolean save(Product product) {
        try {
            executeInsideTransaction(sess -> sess.save(product));
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Product product) {
        if(product != null) {
            executeInsideTransaction(sess -> sess.update(product));
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean delete(Product product) {
        if (product != null) {
            executeInsideTransaction(sess -> sess.delete(product));
            return true;
        } else{
            return false;
        }
    }

    @Override
    public List<Product> findByOvchipkaart(OVChipkaart ovChipkaart) {
        List<Product> producten1 = new ArrayList<>();
        Query query = sess.createQuery("select product from Product product");
        List<Product> producten = query.list();
        for(Product p : producten) {
            for (OVChipkaart ovchip : p.getOvChipkaarten()) {
                if (ovchip.getKaartNummer() == ovChipkaart.getKaartNummer()) {
                    producten1.add(p);
                }
            }
        }
        return producten1;
    }


    @Override
    public List<Product> findAll() {
        Query query = sess.createQuery("select product from Product product");
        List<Product> producten = query.list();
        return producten;
    }
}