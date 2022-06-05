
package util;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;

import model.UserModel;

public class CustomPhaseListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent pe) {
		// System.out.println("After phase: " + pe.getPhaseId());
	}

	@Override
	public void beforePhase(PhaseEvent pe) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpServletRequest servletRequest = (HttpServletRequest) ctx.getExternalContext().getRequest();
		String fullURI = servletRequest.getRequestURI();

		// If User NOT logged in and tries to load a page inside the application.
		if (!UserModel.isLoggedIn(SessionFactory.get("user"))
				&& !fullURI.contains("Login")) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// If User IS logged in and tries to load the login page.
		else if (UserModel.isLoggedIn(SessionFactory.get("user")) && fullURI.contains("Login")) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("BooksList.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}