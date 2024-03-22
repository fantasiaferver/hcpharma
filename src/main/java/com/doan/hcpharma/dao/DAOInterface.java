package com.doan.hcpharma.dao;

import java.util.List;

public interface DAOInterface <T>{
    public boolean addData(T data);
    public boolean updateData(T data);
    public void removeData(T data);
    public List<T> getAll();

}
