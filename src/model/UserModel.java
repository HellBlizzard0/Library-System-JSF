package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.User;
import util.SessionFactory;

public class UserModel {

	public static User login(String username, String password) {
		Session session = null;
		String encrypted = null;
		try {
			session = SessionFactory.getSessionObj();
			encrypted = encrypt(password);
			Query query = session.getNamedQuery("user_login");
			query.setParameter("P_USERNAME", username);
			query.setParameter("P_PASSWORD", encrypted);
			User user = (User) query.list().get(0);

			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private static User loginWithoutEncryption(String username, String password) {
		Session session = null;
		String encrypted = null;
		try {
			session = SessionFactory.getSessionObj();
			encrypted = password;
			Query query = session.getNamedQuery("user_login");
			query.setParameter("P_USERNAME", username);
			query.setParameter("P_PASSWORD", encrypted);
			User user = (User) query.list().get(0);

			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static User create(User user) {
		boolean result = true;
		Session session = null;
		Transaction transaction = null;

		String decrypted = user.getPassword();
		user.setPassword(encrypt(user.getPassword()));

		try {
			session = SessionFactory.getSessionObj();
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();

			return login(user.getUsername(), decrypted);
		} catch (Exception e) {
			result = false;
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return null;
	}

	private static String encrypt(String password) {
		String encryptedpassword = null;

		try {
			MessageDigest m = MessageDigest.getInstance("MD5");

			m.update(password.getBytes());

			byte[] bytes = m.digest();

			StringBuilder s = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}

			encryptedpassword = s.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return encryptedpassword;
	}

	public static boolean isLoggedIn(Object obj) {
		User user = (User) obj;
		if (user == null)
			return false;
		else if (loginWithoutEncryption(user.getUsername(), user.getPassword()) == null)
			return false;
		else
			return true;

	}
}