package com.doan.hcpharma.dao;

import java.util.List;

public interface DAOInterface <T>{
    public void addData(T data);
    public void updateData(T data);
    public void removeData(T data);
    public List<T> getAll();

}
