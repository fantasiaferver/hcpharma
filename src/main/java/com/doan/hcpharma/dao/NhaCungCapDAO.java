package com.doan.hcpharma.dao;

import com.doan.hcpharma.model.KhuVucLuuTruEntity;
import com.doan.hcpharma.model.NhaCungCapEntity;
import com.doan.hcpharma.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class NhaCungCapDAO implements DAOInterface<NhaCungCapEntity>{
    Transaction transaction = null;
    Session session = null;
    @Override
    public boolean addData(NhaCungCapEntity data) {
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
        return true;
    }

    @Override
    public boolean updateData(NhaCungCapEntity data) {
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
        return true;
    }

    @Override
    public void removeData(NhaCungCapEntity data) {
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
    public List<NhaCungCapEntity> getAll() {
        session = HibernateUtil.getSession();
        Query req = session.createQuery("FROM NhaCungCapEntity ");
        List<NhaCungCapEntity> li= req.getResultList();
        session.close();
        return li;
    }
}
