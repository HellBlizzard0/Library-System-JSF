package bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entity.Book;
import entity.Borrow;
import model.BorrowModel;

@ManagedBean
@RequestScoped
public class BookInformationBean {
	Book book;
	List<Borrow> borrows;
	BorrowModel borrowModel = new BorrowModel();

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public List<Borrow> getBorrows() {
		return borrows;
	}

	public void setBorrows(List<Borrow> borrows) {
		this.borrows = borrows;
	}

	public BookInformationBean() {
		super();
	}

	public String goTo(Book book) {
		this.book = book;
		this.borrows = this.borrowModel.getBorrowByBook(this.book.getId());
		return "BookInformation";
	}
}
