package bean;

import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import entity.User;
import model.UserModel;
import util.SessionFactory;

@ManagedBean
@SessionScoped
public class LoginBean {

	private String username;
	private String password;

	public LoginBean() {
		super();
		this.username = "";
		this.password = "";
	}



	public boolean isCustomer() {
		return SessionFactory.isCustomer();
	}

	public String goTo() {
		return "Login";
	}


	public String logout() {
		this.username = "";
		this.password = "";
		return this.goTo();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		User user = UserModel.login(this.username, this.password);
		if(user == null) {
			return "failure";
		} else {
			SessionFactory.put("user", user);
			
			FacesContext context = FacesContext.getCurrentInstance();
			Application application = context.getApplication();
			BookBean bookBean = application.evaluateExpressionGet(context, "#{bookBean}", BookBean.class);
			return bookBean.goTo();
		}
	}
}
