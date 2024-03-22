package com.doan.hcpharma.dao;


import com.doan.hcpharma.model.ThuocEntity;
import com.doan.hcpharma.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class ThuocDAO implements DAOInterface<ThuocEntity> {
    Transaction transaction = null;
    Session session = null;
    @Override
    public boolean addData(ThuocEntity data) {
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
        return false;
    }

    @Override
    public boolean updateData(ThuocEntity data) {
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
        return false;
    }

    @Override
    public void removeData(ThuocEntity data) {
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
    public List<ThuocEntity> getAll() {
        session = HibernateUtil.getSession();
        Query req = session.createQuery("FROM ThuocEntity ");
        List<ThuocEntity> li= req.getResultList();
        session.close();
        return li;

    }

}
