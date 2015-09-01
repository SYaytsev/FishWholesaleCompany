package ua.pp.fishstore.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean
@ViewScoped
public class InfoWindowView implements Serializable {
	private static final long serialVersionUID = 1L;

	private MapModel mapModel;

	private Marker marker;

	@PostConstruct
	public void init() {
		mapModel = new DefaultMapModel();

		// Coordinates
		LatLng officeCoordinate = new LatLng(50.46512797086097,
				30.521155446767807);
		LatLng storeCoordinate = new LatLng(50.46731504086146,
				30.455794036388397);

		// Icons and Data
		mapModel.addOverlay(new Marker(officeCoordinate,
				"Office \"Fisherman\" LLC", "logo.png",
				"http://maps.google.com/mapfiles/ms/micons/blue-dot.png"));
		mapModel.addOverlay(new Marker(storeCoordinate,
				"Store \"Fisherman\" LLC", "store.png",
				"http://maps.google.com/mapfiles/ms/micons/red-dot.png"));
	}

	public MapModel getAdvancedModel() {
		return mapModel;
	}

	public void onMarkerSelect(OverlaySelectEvent event) {
		marker = (Marker) event.getOverlay();
	}

	public Marker getMarker() {
		return marker;
	}
}