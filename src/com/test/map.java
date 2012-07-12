package com.test;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.test.R;

public class map extends MapActivity {
	ImageButton end_map;
	LocationManager lm;
	double longitude;
	double latitude;
	MapView mapview;
	ArrayList<GeoPoint> location = new ArrayList<GeoPoint>();
	ArrayList<String>clinic_name=new ArrayList<String>();
	ArrayList<String>clinic_address=new ArrayList<String>();
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	public void onCreate(Bundle savedInstanceState) {

		 location.add(new GeoPoint((int)(1.320646 *1E6),(int)(103.842773
		 * 1E6)));
		 location.add(new GeoPoint((int)(1.320817 *1E6),(int)(103.843460
				 * 1E6)));
		 location.add(new GeoPoint((int)(1.324593 *1E6),(int)(103.843975
				 * 1E6)));
		 location.add(new GeoPoint((int)(1.321418 *1E6),(int)(103.844104
				 * 1E6)));
		 location.add(new GeoPoint((int)(1.328197 *1E6),(int)(103.841314
				 * 1E6)));
	
		 clinic_name.add("DR BC NG AESTHETICS");
		 clinic_name.add("BALESTIER DISPENSARY MEDICAL and SURGICAL CLINIC");
		 clinic_name.add("GLOAESTHETICS");
		 clinic_name.add("NOVENA CLINIC");
		 clinic_name.add("Noel Leong Fertility & IVF Clinic");
		 
		 clinic_address.add("1 Goldhill Plaza Singapore 308899");
		 clinic_address.add("163 Thomson Rd, Singapore 307616");
		 clinic_address.add("Novena Medical Centre 10 Sinaran Drive #09-30 Singapore 307506");
		 clinic_address.add("10 Sinaran Drive");
		 clinic_address.add("10 Sinaran Drive #09-14 Novena Medical Centre Singapore 307506");
		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);
		mapview = (MapView) findViewById(R.id.mapview);
		end_map = (ImageButton) findViewById(R.id.map_end);
		TextView testing = (TextView) findViewById(R.id.test);
		end_map.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(map.this,
						Clinic_finder2Activity.class);
				startActivity(intent);
			}
		});

		// GPS part
		lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		Drawable drawable1 = this.getResources().getDrawable(
				R.drawable.places);
		final List<Overlay> mapOverlays = mapview.getOverlays();
		final HelloItemizedOverlay itemizedOverlay1 = new HelloItemizedOverlay(
				drawable1, this);

		GeoPoint current = new GeoPoint((int)(1.320646*1E6),(int)(103.84449*1E6));

		OverlayItem overlayitem1 = new OverlayItem(current, "Current Location",
				"You are here");
		itemizedOverlay1.addOverlay(overlayitem1);
		mapOverlays.add(itemizedOverlay1);
		LocationListener listener = new LocationListener() {

			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub

				longitude = location.getLongitude();
				latitude = location.getLatitude();
//
//				GeoPoint current = new GeoPoint((int) (latitude * 1E6),
//						(int) (longitude * 1E6));
				GeoPoint current = new GeoPoint((int)(1.320646*1E6),(int)(103.84449*1E6));

				OverlayItem overlayitem1 = new OverlayItem(current, "Current Location",
						"You are here");
				itemizedOverlay1.addOverlay(overlayitem1);
				mapOverlays.add(itemizedOverlay1);

			}

			public void onProviderDisabled(String arg0) {
				// TODO Auto-generated method stub

			}

			public void onProviderEnabled(String arg0) {
				// TODO Auto-generated method stub

			}

			public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
				// TODO Auto-generated method stub

			}

		};

		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);

		mapview.setBuiltInZoomControls(true);

		MapController myMapController = mapview.getController();
		myMapController.setZoom(19);

		// overlay part

		Drawable drawable = this.getResources().getDrawable(
				R.drawable.clinic);

		HelloItemizedOverlay itemizedOverlay = new HelloItemizedOverlay(
				drawable, this);

		MapController mapController = mapview.getController();

		mapController.setCenter(new GeoPoint((int)(1.320646*1E6),(int)( 103.84449*1E6)));
		
	
		for (int z = 0; z < location.size(); z++) {
			GeoPoint point = location.get(z);
			OverlayItem overlayitem = new OverlayItem(point,clinic_name.get(z),clinic_address.get(z));
			itemizedOverlay.addOverlay(overlayitem);
			mapOverlays.add(itemizedOverlay);
		}

	}

}
