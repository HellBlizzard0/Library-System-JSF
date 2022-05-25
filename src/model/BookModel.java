package model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;

import entity.Book;
import util.SessionFactory;

public class BookModel {

	private static Transaction transObj;

	public List<Book> getAllBooks() {
		List result = new ArrayList<>();
		try {
			transObj = SessionFactory.sessionObj.beginTransaction();
			Query queryObj = SessionFactory.sessionObj.getNamedQuery("book_fetchAll");
			result = queryObj.list();

			// XHTML Response Text
			// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("findBookById",
			// bookId);
		} catch (Exception exceptionObj) {
			exceptionObj.printStackTrace();
		} finally {
			transObj.commit();
		}
		return result;
	}
}
