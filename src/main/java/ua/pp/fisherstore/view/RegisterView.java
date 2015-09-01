package ua.pp.fisherstore.view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import org.primefaces.context.RequestContext;

@ManagedBean(name = "registerView")
public class RegisterView implements Serializable {
	private static final long serialVersionUID = 1L;

	public void viewRegisterForm() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 320);

		RequestContext.getCurrentInstance().openDialog("register", options,
				null);
	}
}
