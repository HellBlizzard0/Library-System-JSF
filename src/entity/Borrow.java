package entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "borrow")
public class Borrow implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user_id")
	private User user;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "book_id")
	private Book book;

	@Column(name = "date_of_borrowing")
	
	private Timestamp dateOfBorrowing;
@Column(name = "return_date")
	private Timestamp returnDate;
}
