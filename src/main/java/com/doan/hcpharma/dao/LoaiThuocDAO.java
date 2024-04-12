package com.doan.hcpharma.dao;

import com.doan.hcpharma.model.KhuVucLuuTruEntity;
import com.doan.hcpharma.model.LoaiThuocEntity;
import com.doan.hcpharma.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class LoaiThuocDAO implements DAOInterface<LoaiThuocEntity>{
    Transaction transaction = null;
    Session session = null;
    @Override
    public boolean addData(LoaiThuocEntity data) {
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
    public boolean updateData(LoaiThuocEntity data) {
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
    public void removeData(LoaiThuocEntity data) {
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
    public List<LoaiThuocEntity> getAll() {
        session = HibernateUtil.getSession();
        Query req = session.createQuery("FROM LoaiThuocEntity ");
        List<LoaiThuocEntity> li= req.getResultList();
        session.close();
        return li;
    }

    public List<String> getAllLoaiThuoc() {
        session = HibernateUtil.getSession();
        try {
            String hql = "SELECT DISTINCT lt.maLoaiThuoc FROM LoaiThuocEntity lt";
            Query query = session.createQuery(hql, String.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<LoaiThuocEntity> searchKVLTByNameOrID(String keyword) {
        session = HibernateUtil.getSession();
        try {
            transaction = session.beginTransaction();
            // Tạo câu truy vấn HQL
            String hql = "FROM LoaiThuocEntity WHERE maLoaiThuoc LIKE :keyword OR tenLoaiThuoc  LIKE :keyword";

            // Tạo truy vấn từ câu HQL và thiết lập tham số
            Query query = session.createQuery(hql, LoaiThuocEntity.class);
            query.setParameter("keyword", "%" + keyword + "%");

            // Thực hiện truy vấn và trả về danh sách nhân viên tìm được
            List<LoaiThuocEntity> result = query.getResultList();
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
