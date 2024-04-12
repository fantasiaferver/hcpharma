package com.doan.hcpharma.dao;

import com.doan.hcpharma.model.KhuVucLuuTruEntity;
import com.doan.hcpharma.model.NhaCungCapEntity;
import com.doan.hcpharma.model.NhanVienEntity;
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

    public List<String> getAllNCC() {
        session = HibernateUtil.getSession();
        try {
            String hql = "SELECT DISTINCT s.maNcc FROM NhaCungCapEntity  s";
            Query query = session.createQuery(hql, String.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<NhaCungCapEntity> searchNCCByNameOrPhone(String keyword) {
        session = HibernateUtil.getSession();
        try {
            transaction = session.beginTransaction();
            // Tạo câu truy vấn HQL
            String hql = "FROM NhaCungCapEntity WHERE tenNcc LIKE :keyword OR soDtNcc  LIKE :keyword";

            // Tạo truy vấn từ câu HQL và thiết lập tham số
            Query query = session.createQuery(hql, NhaCungCapEntity.class);
            query.setParameter("keyword", "%" + keyword + "%");

            // Thực hiện truy vấn và trả về danh sách nhân viên tìm được
            List<NhaCungCapEntity> result = query.getResultList();
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
