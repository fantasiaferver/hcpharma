package com.doan.hcpharma.dao;


import com.doan.hcpharma.model.KhachHangEntity;
import com.doan.hcpharma.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class KhachHangDAO implements DAOInterface<KhachHangEntity>{
    Transaction transaction = null;
    Session session = null;
    @Override
    public void addData(KhachHangEntity data) {
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
    public void updateData(KhachHangEntity data) {
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
    public void removeData(KhachHangEntity data) {
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
    public List<KhachHangEntity> getAll() {
        session = HibernateUtil.getSession();
            Query req = session.createQuery("FROM KhachHangEntity ");
            List<KhachHangEntity> li= req.getResultList();
            session.close();
            return li;

        }

}
