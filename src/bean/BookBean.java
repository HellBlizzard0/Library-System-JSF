package bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import entity.Book;
import model.BookModel;

@ManagedBean
@SessionScoped
public class BookBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String bookName = "Book 1";
	private List<Book> books;

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public BookBean() {
		super();
		this.bookName = "Book 1";
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public void borrowBook(Book book) {
		System.out.println("Borrow Book: " + book.getName());
	}
	public String goTo() {
		BookModel bookModel = new BookModel();
		List<Book> books = bookModel.getAllBooks();
		for (Book book : books) {
			System.out.println(book.getAuthor());
		}
		this.books = books;
		return "BooksList";
	}

}
