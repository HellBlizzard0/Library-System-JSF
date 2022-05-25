package util;

import org.hibernate.Session;

public class SessionFactory {
	public static Session sessionObj = HibernateUtil.getSessionFactory().openSession();
}
