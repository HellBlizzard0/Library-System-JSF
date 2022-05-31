package bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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

	private BookWithStatus newBook;
	private BookWithStatus deleteBook;

	private BookModel bookModel = new BookModel();
	private BorrowModel borrowModel = new BorrowModel();

	private boolean editBookMode = false;
	private boolean deleteMode = false;

	public BookWithStatus getDeleteBook() {
		return deleteBook;
	}

	public void setDeleteBook(BookWithStatus deleteBook) {
		this.deleteBook = deleteBook;
	}


	public boolean isDeleteMode() {
		return deleteMode;
	}

	public void setDeleteMode(boolean deleteMode) {
		this.deleteMode = deleteMode;
	}

	public boolean isEditBookMode() {
		return editBookMode;
	}

	public void setEditBookMode(boolean editBookMode) {
		this.editBookMode = editBookMode;
	}




	public BookWithStatus getNewBook() {
		return newBook;
	}

	public void setNewBook(BookWithStatus newBook) {
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
		newBook = new BookWithStatus(0, "", "", null, null, true);
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
				null));

		FacesContext context = FacesContext.getCurrentInstance();
		Application application = context.getApplication();
		BorrowBean borrowBean = application.evaluateExpressionGet(context, "#{borrowBean}", BorrowBean.class);
		return borrowBean.goTo();
	}

	public String deleteBook() {
		this.bookModel.delete(this.deleteBook.toBook());
		this.deleteMode = false;
		return this.goTo();
	}

	public String cancelAddEdit() {
		this.newBook = new BookWithStatus(0, "", "", null, null, true);
		this.editBookMode = false;
		return this.refresh();
	}

	public String cancelDelete() {
		this.deleteBook = new BookWithStatus(0, "", "", null, null, true);
		this.deleteMode = false;
		return this.refresh();
	}

	public String addEditBook() {
		if(editBookMode)
			this.bookModel.update(this.newBook.toBook());
		else
			this.bookModel.create(this.newBook.toBook());

		this.editBookMode = false;
		return this.goTo();
	}

	public String toggleEdit(BookWithStatus book) {
		this.newBook = book;
		this.editBookMode = true;
		this.deleteMode = false;
		return this.refresh();
	}

	public String toggleDelete(BookWithStatus book) {
		this.deleteBook = book;
		this.editBookMode = false;
		this.deleteMode = true;
		return this.refresh();
	}

	private String refresh() {
		return "BookList";
	}

	public String goTo() {
		this.newBook = new BookWithStatus(0, "", "", null, null, true);
		this.deleteBook = new BookWithStatus(0, "", "", null, null, true);
		this.books = this.bookModel.getBooksWithStatuses();
		this.editBookMode = false;
		this.deleteMode = false;
		return "BooksList";
	}
	


}
