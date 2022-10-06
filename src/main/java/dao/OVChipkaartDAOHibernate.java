package dao;

import domein.Adres;
import domein.OVChipkaart;
import domein.Reiziger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public class OVChipkaartDAOHibernate extends BaseDAOHibernate implements OVChipkaartDAO{
    public OVChipkaartDAOHibernate(Session sess) {
        super(sess);
    }

    @Override
    public boolean save(OVChipkaart ovChipkaart) {
        try {
            executeInsideTransaction(sess -> sess.save(ovChipkaart));
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(OVChipkaart ovChipkaart) {
        if(ovChipkaart != null) {
            executeInsideTransaction(sess -> sess.update(ovChipkaart));
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean delete(OVChipkaart ovChipkaart) {
        if (ovChipkaart != null) {
            executeInsideTransaction(sess -> sess.delete(ovChipkaart));
            return true;
        } else{
            return false;
        }
    }

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) {
        Query query = sess.createQuery("select ovchip from OVChipkaart ovchip where reiziger =  " + reiziger.getId());
        List<OVChipkaart> ovChipkaarten = query.list();
        return ovChipkaarten;
    }


    @Override
    public List<OVChipkaart> findAll() {
        Query query = sess.createQuery("select ovchip from OVChipkaart ovchip");
        List<OVChipkaart> ovChipkaarten = query.list();
        return ovChipkaarten;
    }
}
