package util;

import javax.faces.context.FacesContext;

import org.hibernate.Session;

import entity.User;

public class SessionFactory {
	private static Session sessionObj = HibernateUtil.getSessionFactory().openSession();

	public static Session getSessionObj() {
		if(!sessionObj.isOpen())
			sessionObj = HibernateUtil.getSessionFactory().openSession();
		return sessionObj;
	}
	
	public static void put(String key, Object value) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
	}

	public static Object get(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
	}

	public static boolean isCustomer() {
		return ((User) SessionFactory.get("user")).getRole().getName().contains("CUSTOMER");
	}
}
