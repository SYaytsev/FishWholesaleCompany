package ua.pp.fishstore.other;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

// TODO 
//SEVERE: IOException while loading persisted sessions: java.io.WriteAbortedException: writing aborted; java.io.NotSerializableException: ua.pp.fishstore.bean.EmployeesListBean
//java.io.WriteAbortedException: writing aborted; java.io.NotSerializableException: ua.pp.fishstore.bean.EmployeesListBean

@FacesConverter("myDateTimeConverter")
public class MyDateTimeConverter extends DateTimeConverter {

	public MyDateTimeConverter() {
		setPattern("dd.MM.yyyy");
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && value.length() != getPattern().length()) {
			throw new ConverterException("Invalid format");
		}

		return super.getAsObject(context, component, value);
	}

}