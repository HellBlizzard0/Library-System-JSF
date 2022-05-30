package bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import entity.Book;
import entity.BookWithStatus;
import entity.Borrow;
import entity.User;
import model.BookModel;
import model.BorrowModel;
import util.SessionFactory;

@ManagedBean
@SessionScoped
public class BookBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String bookName = "Book 1";
	private List<BookWithStatus> books;

	private Book newBook;

	private BookModel bookModel = new BookModel();
	private BorrowModel borrowModel = new BorrowModel();

	public Book getNewBook() {
		return newBook;
	}

	public void setNewBook(Book newBook) {
		this.newBook = newBook;
	}



	public List<BookWithStatus> getBooks() {
		return books;
	}

	public void setBooks(List<BookWithStatus> books) {
		this.books = books;
	}

	public BookBean() {
		super();
		this.bookName = "Book 1";
		newBook = new Book(0, "", "", null, null);
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public String borrowBook(Book book) {
		this.borrowModel.create(new Borrow(1, (User) SessionFactory.get("user"), book,
				Timestamp.valueOf(LocalDateTime.now()),
				Timestamp.valueOf(LocalDateTime.now())));
		return "BorrowsList";
	}

	public String deleteBook(Book book) {
		this.bookModel.delete(book);
		return this.goTo();
	}

	public String cancelAddEdit() {
		this.newBook = new Book(0, "", "", null, null);
		// System.out.println("cance;AddEdit");
		return this.goTo();
	}

	public String addEditBook() {
		boolean result = this.bookModel.create(this.newBook);
		// System.out.println("addEditBook result = " + result);

		return this.goTo();
	}

	public boolean isCustomer() {

		return true;
	}
	public String goTo() {
		this.books = this.bookModel.getBooksWithStatuses();
		return "BooksList";
	}

}
