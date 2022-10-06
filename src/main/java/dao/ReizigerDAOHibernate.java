package dao;

import domein.Adres;
import domein.Reiziger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

public class ReizigerDAOHibernate extends BaseDAOHibernate implements ReizigerDAO {
    public ReizigerDAOHibernate(Session sess) {
        super(sess);
    }

    @Override
    public boolean save(Reiziger reiziger) {
        try {
            executeInsideTransaction(sess -> sess.save(reiziger));
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Reiziger reiziger) {
            if(reiziger != null) {
                executeInsideTransaction(sess -> sess.update(reiziger));
                return true;
            }else {
                return false;
            }
    }


    @Override
    public boolean delete(Reiziger reiziger) {
        if (reiziger != null) {
            executeInsideTransaction(sess -> sess.delete(reiziger));
            return true;
        } else{
            return false;
        }
    }

    @Override
    public Reiziger findById(int id) {
        Reiziger reiziger = sess.get(Reiziger.class, id);
        return reiziger;
    }

    @Override
    public List<Reiziger> findByGbdatum(Date datum) {
        Query query = sess.createQuery("select r from Reiziger r");
        List<Reiziger> reizigers = query.list();
        for(Reiziger r : reizigers) {
            if (r.getGeboortedatum() == datum) {
                reizigers.add(r);
            }
        }
        return reizigers;
    }

    @Override
    public List<Reiziger> findAll() {
        Query query = sess.createQuery("select r from Reiziger r");
        List<Reiziger> reizigers = query.list();
        return reizigers;

    }
}
