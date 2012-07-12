package com.test;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.test.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Clinic_finder2Activity extends Activity {
	/** Called when the activity is first created. */

	Button detail;
	Button map;
	double longitude;
	double latitude;
	Clinic data;
	ArrayList<Clinic1> name = new ArrayList<Clinic1>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		detail = (Button) findViewById(R.id.search_details);
		map = (Button) findViewById(R.id.search_map);

		// get GPS location
		LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		LocationListener mlocListener = new MyLocationListener();
		mlocManager.requestLocationUpdates(
				LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
		detail.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(Clinic_finder2Activity.this,
						ClinicView.class);
				Bundle extras = new Bundle();

				extras.putDouble("Longitude", longitude);
				extras.putDouble("Latitude", latitude);
				intent.putExtras(extras);
				startActivityForResult(intent, 1);
			}
		});

		map.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
		
				// TODO Auto-generated method stub
				
				Intent intent1 = new Intent(Clinic_finder2Activity.this,
						map.class);
				
				startActivityForResult(intent1, 1);

				
			}
		});
	}

	// add unimplmented class
	public class MyLocationListener implements LocationListener {

		public void onLocationChanged(Location loc) {

			latitude = loc.getLatitude();
			longitude = loc.getLongitude();

		}

		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "Gps Disabled",
					Toast.LENGTH_SHORT).show();
		}

		public void onProviderEnabled(String arg0) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "Gps Enabled",
					Toast.LENGTH_SHORT).show();

		}

		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			// TODO Auto-generated method stub

		}
	}
}