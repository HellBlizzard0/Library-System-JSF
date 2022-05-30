package entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class BookWithStatus extends Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean available;

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public BookWithStatus(int id, String name, String author, Timestamp dateOfCreation, Timestamp lastUpdated,
			boolean available) {
		super(id, name, author, dateOfCreation, lastUpdated);
		this.available = available;
	}

	public BookWithStatus(Book book, boolean available) {
		super(book.getId(), book.getName(), book.getAuthor(), book.getDateOfCreation(), book.getLastUpdated());
		this.available = available;
	}
}
