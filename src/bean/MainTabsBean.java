package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class MainTabsBean {

	public MainTabsBean() {
		
	}
	
	public String checkActiveTab(int tabCode, String currentTab) {
		String result = "";
		switch (tabCode) {
		case 0: {
			result = "books".matches(currentTab) ? "active" : "";
			break;
		}
		case 1: {
			result = "borrows".matches(currentTab) ? "active" : "";
			break;
		}
		case 2: {
			result = "profile".matches(currentTab) ? "active" : "";
			break;
		}

		}
		return result;
	}

}
