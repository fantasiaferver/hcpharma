package com.doan.hcpharma.dao;

import com.doan.hcpharma.model.NhanVienEntity;
import com.doan.hcpharma.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class NhanVienDAO implements DAOInterface {
    Transaction transaction = null;
    Session session = null;
    @Override
    public void addData(Object data) {

        session = HibernateUtil.getSession();

        try {
            transaction = session.beginTransaction();

            session.save(data);

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void updateData(Object data) {
        session = HibernateUtil.getSession();

        try {
            transaction = session.beginTransaction();

            session.update(data);

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void removeData(Object data) {
        session = HibernateUtil.getSession();

        try {
            transaction = session.beginTransaction();

            session.delete(data);

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public List getAll() {
        session = HibernateUtil.getSession();
        Query req = session.createQuery("FROM NhanVienEntity ");
        List<NhanVienEntity> li= req.getResultList();
        session.close();
        return li;

    }

}
