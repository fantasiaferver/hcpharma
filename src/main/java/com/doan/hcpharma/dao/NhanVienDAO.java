package com.doan.hcpharma.dao;

import com.doan.hcpharma.model.KhachHangEntity;
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
    public boolean addData(Object data) {

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
    public boolean updateData(Object data) {
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
    public List<NhanVienEntity> searchEmployeesByNameOrPhone(String keyword) {
        session= HibernateUtil.getSession();
        try {
            transaction =session.beginTransaction();
            // Tạo câu truy vấn HQL
            String hql = "FROM NhanVienEntity WHERE tenNv LIKE :keyword OR sdtNv  LIKE :keyword";

            // Tạo truy vấn từ câu HQL và thiết lập tham số
            Query query = session.createQuery(hql, NhanVienEntity.class);
            query.setParameter("keyword", "%" + keyword + "%");

            // Thực hiện truy vấn và trả về danh sách nhân viên tìm được
            List<NhanVienEntity> result = query.getResultList();
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
