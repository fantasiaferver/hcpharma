package com.doan.hcpharma.dao;


import com.doan.hcpharma.model.NhanVienEntity;
import com.doan.hcpharma.model.ThuocEntity;
import com.doan.hcpharma.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
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
        List<ThuocEntity> li = new ArrayList<>();
        try (Session session = HibernateUtil.getSession()) {
            Query req = session.createQuery("FROM ThuocEntity");
            li = req.getResultList();
        } catch (HibernateException e) {
            // Xử lý ngoại lệ nếu cần
            e.printStackTrace();
        }
        return li;
    }

    public List<ThuocEntity> findThuocByNameOrKindOf(String keyword) {
        session= HibernateUtil.getSession();
        try {
            transaction =session.beginTransaction();
            // Tạo câu truy vấn HQL
            String hql = "FROM ThuocEntity WHERE tenThuoc LIKE :keyword OR maLoaiThuoc  LIKE :keyword";

            // Tạo truy vấn từ câu HQL và thiết lập tham số
            Query query = session.createQuery(hql, ThuocEntity.class);
            query.setParameter("keyword", "%" + keyword + "%");

            // Thực hiện truy vấn và trả về danh sách nhân viên tìm được
            List<ThuocEntity> result = query.getResultList();
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
