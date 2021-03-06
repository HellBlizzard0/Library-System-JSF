package model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Book;
import entity.BookWithStatus;
import util.SessionFactory;

public class BookModel {

	private static Transaction transObj;

	public List<Book> getAllBooks() {
		List result = new ArrayList<>();
		try {
			transObj = SessionFactory.getSessionObj().beginTransaction();
			Query queryObj = SessionFactory.getSessionObj().getNamedQuery("book_fetchAll");
			result = queryObj.list();

		} catch (Exception exceptionObj) {
			exceptionObj.printStackTrace();
		}
		return result;
	}


	public boolean create(Book book) {
		boolean result = true;
		Session session = null;
		Transaction transaction = null;

		try {
			session = SessionFactory.getSessionObj();
			transaction = session.beginTransaction();
			session.save(book);
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

	public boolean update(Book book) {
		boolean result = true;
		Session session = null;
		Transaction transaction = null;

		try {
			session = SessionFactory.getSessionObj();
			transaction = session.beginTransaction();
			session.update(book);
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

	public boolean delete(Book book) {
		boolean result = true;
		Session session = null;
		Transaction transaction = null;

		try {
			session = SessionFactory.getSessionObj();
			transaction = session.beginTransaction();
			session.delete(book);
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

	public List<BookWithStatus> getBooksWithStatuses() {
		ArrayList<BookWithStatus> result = new ArrayList<BookWithStatus>();
		try {
			transObj = SessionFactory.getSessionObj().beginTransaction();
			Query queryObj = SessionFactory.getSessionObj().getNamedQuery("book_fetchAll");
			List<Book> books = queryObj.list();
			for (Book book : books) {
				queryObj = SessionFactory.getSessionObj().getNamedQuery("borrow_isBookAvailable");
				queryObj.setParameter("P_BOOKID", book.getId());
				result.add(new BookWithStatus(book, queryObj.list().isEmpty()));
			}

		} catch (Exception exceptionObj) {
			exceptionObj.printStackTrace();
		}
		return result;
	}
}
