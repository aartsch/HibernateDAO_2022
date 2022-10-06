package dao;

import domein.Adres;
import domein.Reiziger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public class AdresDAOHibernate extends BaseDAOHibernate implements AdresDAO {
    public AdresDAOHibernate(Session sess) {
        super(sess);
    }

    @Override
    public boolean save(Adres adres) {
        try {
            executeInsideTransaction(sess -> sess.save(adres));
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Adres adres) {
        if(adres != null) {
            executeInsideTransaction(sess -> sess.update(adres));
            return true;
        }else {
            return false;
        }
    }
    @Override
    public boolean delete(Adres adres) {
        if (adres != null) {
            executeInsideTransaction(sess -> sess.delete(adres));
            return true;
        } else{
            return false;
        }
    }


    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        Adres adres = sess.get(Adres.class, reiziger.getId());
        return adres;
    }

    @Override
    public List<Adres> findAll() {
        Query query = sess.createQuery("SELECT a from Adres a");
        List<Adres> adressen = query.list();
        return adressen;
    }
}
