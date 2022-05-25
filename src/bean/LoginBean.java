package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entity.User;
import model.UserModel;

@ManagedBean
@RequestScoped
public class LoginBean {

	public LoginBean() {
		super();
	}

	private User user;

	public LoginBean(User user) {
		super();
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String goTo() {
		return "Login";
	}

	public String login() {
		User user = UserModel.login(this.user.getUsername(), this.user.getPassword());
		if(user == null) {
			return "false";
		} else {
			return "true";
		}
	}
}
