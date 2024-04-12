package com.doan.hcpharma.dao;

import com.doan.hcpharma.model.KhuVucLuuTruEntity;
import com.doan.hcpharma.model.NhanVienEntity;
import com.doan.hcpharma.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class KhuVucLuuTruDAO implements DAOInterface<KhuVucLuuTruEntity> {
    Transaction transaction = null;
    Session session = null;
    @Override
    public boolean addData(KhuVucLuuTruEntity data) {
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
    public boolean updateData(KhuVucLuuTruEntity data) {
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
    public void removeData(KhuVucLuuTruEntity data) {
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
    public List<KhuVucLuuTruEntity> getAll() {
        session = HibernateUtil.getSession();
        Query req = session.createQuery("FROM KhuVucLuuTruEntity ");
        List<KhuVucLuuTruEntity> li= req.getResultList();
        session.close();
        return li;

    }
    public List<String> getAllKhuVuc() {
        session = HibernateUtil.getSession();
        try {
            String hql = "SELECT DISTINCT kv.maKv FROM KhuVucLuuTruEntity kv";
            Query query = session.createQuery(hql, String.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<KhuVucLuuTruEntity> searchKVLTByNameOrID(String keyword) {
        session = HibernateUtil.getSession();
        try {
            transaction = session.beginTransaction();
            // Tạo câu truy vấn HQL
            String hql = "FROM KhuVucLuuTruEntity WHERE maKv LIKE :keyword OR tenKv  LIKE :keyword";

            // Tạo truy vấn từ câu HQL và thiết lập tham số
            Query query = session.createQuery(hql, KhuVucLuuTruEntity.class);
            query.setParameter("keyword", "%" + keyword + "%");

            // Thực hiện truy vấn và trả về danh sách nhân viên tìm được
            List<KhuVucLuuTruEntity> result = query.getResultList();
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
