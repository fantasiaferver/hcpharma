package com.doan.hcpharma.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	public static Session  getSession(){
		Session	s = null;
		SessionFactory sf = null;
		sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		s = sf.openSession();
		return s;
	}


}
