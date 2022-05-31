package model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Borrow;
import util.SessionFactory;

public class BorrowModel {

	private static Transaction transObj;

	public List<Borrow> getAllBorrows() {
		List result = new ArrayList<>();
		try {
			transObj = SessionFactory.getSessionObj().beginTransaction();
			Query queryObj = SessionFactory.getSessionObj().getNamedQuery("borrow_fetchAll");
			result = queryObj.list();

		} catch (Exception exceptionObj) {
			exceptionObj.printStackTrace();
		}
		return result;
	}

	public List<Borrow> getBorrowsByUser(int id) {
		List<Borrow> result = new ArrayList<Borrow>();
		try {
			transObj = SessionFactory.getSessionObj().beginTransaction();
			Query queryObj = SessionFactory.getSessionObj().getNamedQuery("borrow_fetchByUserId");
			queryObj.setParameter("P_USERID", id);
			result = queryObj.list();

		} catch (Exception exceptionObj) {
			exceptionObj.printStackTrace();
		}
		return result;
	}

	public List<Borrow> getBorrowByBook(int id) {
		List<Borrow> result = new ArrayList<Borrow>();
		try {
			transObj = SessionFactory.getSessionObj().beginTransaction();
			Query queryObj = SessionFactory.getSessionObj().getNamedQuery("borrow_fetchByBookId");
			queryObj.setParameter("P_BOOKID", id);
			result = queryObj.list();

		} catch (Exception exceptionObj) {
			exceptionObj.printStackTrace();
		}
		return result;
	}

	public boolean isBorrowed() {
		boolean result = false;
		try {
			transObj = SessionFactory.getSessionObj().beginTransaction();
			Query queryObj = SessionFactory.getSessionObj().getNamedQuery("borrow_isReturned");
			result = queryObj.list().isEmpty();

		} catch (Exception exceptionObj) {
			exceptionObj.printStackTrace();
		}
		return result;
	}

	public boolean create(Borrow borrow) {
		boolean result = true;
		Session session = null;
		Transaction transaction = null;

		try {
			session = SessionFactory.getSessionObj();
			transaction = session.beginTransaction();
			session.save(borrow);
			transaction.commit();
		} catch (Exception e) {
			result = false;
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return result;
	}

	public boolean update(Borrow borrow) {
		boolean result = true;
		Session session = null;
		Transaction transaction = null;

		try {
			session = SessionFactory.getSessionObj();
			transaction = session.beginTransaction();
			session.update(borrow);
			transaction.commit();
		} catch (Exception e) {
			result = false;
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return result;
	}

	public boolean delete(Borrow borrow) {
		boolean result = true;
		Session session = null;
		Transaction transaction = null;

		try {
			session = SessionFactory.getSessionObj();
			transaction = session.beginTransaction();
			session.delete(borrow);
			transaction.commit();
		} catch (Exception e) {
			result = false;
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return result;
	}
}
