package com.test;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.test.R;
import com.test.Clinic_finder2Activity.MyLocationListener;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class ClinicView extends ListActivity {

	ImageButton end;
	Clinic data;
	ArrayList<Clinic1> name = new ArrayList<Clinic1>();
	double longitude;
	double latitude;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);

		end = (ImageButton) findViewById(R.id.details_end);

		// Bundle extras = getIntent().getExtras();
		// LocationManager mlocManager = (LocationManager)
		// getSystemService(Context.LOCATION_SERVICE);
		//
		// LocationListener mlocListener = new MyLocationListener();
		// mlocManager.requestLocationUpdates(
		// LocationManager.GPS_PROVIDER, 0, 0, mlocListener);

		// if (extras != null) {
		// longitude = extras.getDouble("Longitude");
		// latitude=extras.getDouble("Latitude");
		// }

		end.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ClinicView.this,
						Clinic_finder2Activity.class);
				startActivity(intent);

			}

		});
		try {

			SAXParserFactory saxPF = SAXParserFactory.newInstance();
			SAXParser saxP = saxPF.newSAXParser();
			XMLReader xmlR = saxP.getXMLReader();
			//
			// URL url = new URL(
			// "http://homes.soi.rp.edu.sg/101163/utsc/xml/get_clinic.php?lat="+latitude+"&lng="+longitude+"&radius=2");
			URL url = new URL(
					"http://homes.soi.rp.edu.sg/101163/utsc/xml/get_clinic.php?lat=1.320646&lng=103.84449&radius=0.5");

			ClinicHandler myXMLHandler = new ClinicHandler();
			xmlR.setContentHandler(myXMLHandler);

			xmlR.parse(new InputSource(url.openStream()));

			//

		} catch (Exception e) {
			System.out.println(e);

		}

		// //
		data = ClinicHandler.data;

		ArrayList<String> test = new ArrayList<String>();
		for (int i = 0; i < data.getcName().size(); i++) {
			test.add(data.getClinic_id().get(i).toString());

			Clinic1 clinicinfo = new Clinic1(data.getcName().get(i), data
					.getLat().get(i), data.getLng().get(i), data.getClinic_id()
					.get(i));
			name.add(clinicinfo);

		}
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data.getcName()));

	}

	public void onListItemClick(ListView parent, View v, int position, long id) {

		Intent i = new Intent(getApplicationContext(), ClinicInfo.class);
		i.putExtra("Id", name.get(position).getId());
		startActivity(i);

	}

}
