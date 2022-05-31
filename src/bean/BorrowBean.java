package bean;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import entity.Borrow;
import entity.User;
import model.BorrowModel;
import util.SessionFactory;

@ManagedBean
@SessionScoped
public class BorrowBean {
	private BorrowModel borrowModel = new BorrowModel();
	private List<Borrow> borrows = new ArrayList<Borrow>();


	public String goTo() {
		this.borrows = null;
		User user = (User) SessionFactory.get("user");

		if (SessionFactory.isCustomer())
			this.borrows = this.borrowModel.getBorrowsByUser(((User) SessionFactory.get("user")).getId());
		else
			this.borrows = this.borrowModel.getAllBorrows();
		return "BorrowsList";
	}

	public BorrowModel getBorroModel() {
		return borrowModel;
	}

	public void setBorroModel(BorrowModel borrowModel) {
		this.borrowModel = borrowModel;
	}

	public List<Borrow> getBorrows() {
		return borrows;
	}

	public void setBorrows(List<Borrow> borrows) {
		this.borrows = borrows;
	}

	public String returnBook(Borrow borrow) {
		borrow.setReturnDate(Timestamp.valueOf(LocalDateTime.now()));
		this.borrowModel.update(borrow);
		return this.goTo();
	}
}
