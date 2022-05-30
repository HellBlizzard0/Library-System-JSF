package entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

//borrow_fetchByUserId
@NamedQueries({ @NamedQuery(name = "borrow_fetchAll", query = "from Borrow b"),
		@NamedQuery(name = "borrow_isReturned", query = "from Borrow b " + "where b.book.id = :P_BOOKID "
				+ "and b.returnDate is null"),
		@NamedQuery(name = "borrow_fetchByUserId", query = "from Borrow b " + "where b.user.id = :P_USERID "),
		@NamedQuery(name = "borrow_isBookAvailable", query = "from Borrow b "
				+ "where b.book.id = :P_BOOKID " + "and b.returnDate is null") })

@Entity
@Table(name = "borrow")
public class Borrow implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "id", name = "user_id")
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Borrow() {
		super();
	}

	public Borrow(int id, User user, Book book, Timestamp dateOfBorrowing, Timestamp returnDate) {
		super();
		this.id = id;
		this.user = user;
		this.book = book;
		this.dateOfBorrowing = dateOfBorrowing;
		this.returnDate = returnDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Timestamp getDateOfBorrowing() {
		return dateOfBorrowing;
	}

	public void setDateOfBorrowing(Timestamp dateOfBorrowing) {
		this.dateOfBorrowing = dateOfBorrowing;
	}

	public Timestamp getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Timestamp returnDate) {
		this.returnDate = returnDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "id", name = "book_id")
	private Book book;

	@Column(name = "date_of_borrowing")
	
	private Timestamp dateOfBorrowing;
@Column(name = "return_date")
	private Timestamp returnDate;
}
