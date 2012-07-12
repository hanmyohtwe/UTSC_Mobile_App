package com.test;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class PatientQInfo extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		// Receiving the Data
		Intent i = getIntent();
		String id = i.getStringExtra("Id");
		String name = i.getStringExtra("name");
		String fin = i.getStringExtra("fin");
		String dob = i.getStringExtra("dob");
		String contact = i.getStringExtra("contact");
		String address = i.getStringExtra("address");

		// All variables
		final String URL = "http://homes.soi.rp.edu.sg/101163/utsc/xml/add_patient.php?patient_IC="
				+ fin
				+ "&patient_name="
				+ name
				+ "&patient_dob="
				+ dob
				+ "&patient_contact="
				+ contact
				+ "&patient_address="
				+ address
				+ "&clinic_id=" + id;
		// Text Views
		TextView txtUrQ = (TextView) findViewById(R.id.txtUrQ);
		TextView txtCurrentQ = (TextView) findViewById(R.id.txtCurrentQ);
		TextView txtName = (TextView) findViewById(R.id.txtName);
		TextView txtLocation = (TextView) findViewById(R.id.txtLocation);

		// XML node keys
		final String KEY_CLINIC = "clinic"; // parent node
		final String KEY_NAME = "clinic_name";
		final String KEY_LOCATION = "clinic_location";
		final String KEY_CURRENT = "current_q_no";
		final String KEY_BOOKED = "booked_number";

		// ArrayList<HashMap<String, String>> clinicList = new
		// ArrayList<HashMap<String, String>>();
		//
		XMLParser parser = new XMLParser();
		String xml = parser.getXmlFromUrl(URL); // getting XML
		Document doc = parser.getDomElement(xml); // getting DOM element
		//
		NodeList nl = doc.getElementsByTagName(KEY_CLINIC);
		// // looping through all item nodes <item>
		for (int u = 0; u < nl.getLength(); u++) {
			Element e = (Element) nl.item(u);
			txtName.setText("Clinic Name: " + parser.getValue(e, KEY_NAME));
			txtLocation.setText("Clinic Location: "
					+ parser.getValue(e, KEY_LOCATION));
			txtCurrentQ.setText("Current Queue No. "
					+ parser.getValue(e, KEY_CURRENT));
			txtUrQ.setText("Your Queue No. " + parser.getValue(e, KEY_BOOKED));

			// // creating new HashMap
			// HashMap<String, String> map = new HashMap<String, String>();
			// Element e = (Element) nl.item(u);
			// // adding each child node to HashMap key => value
			// map.put(KEY_LOCATION, parser.getValue(e, KEY_LOCATION));
			// map.put(KEY_NAME, parser.getValue(e, KEY_NAME));
			// map.put(KEY_CURRENT, "No. " + parser.getValue(e, KEY_CURRENT));
			// map.put(KEY_BOOKED, "No. " + parser.getValue(e, KEY_BOOKED));
			//
			// // adding HashList to ArrayList
			// clinicList.add(map);
		}
		//
		// // Adding menuItems to ListView
		// ListAdapter adapter = new SimpleAdapter(this, clinicList,
		// R.layout.register, new String[] { KEY_NAME, KEY_LOCATION,
		// KEY_CURRENT, KEY_BOOKED }, new int[] { R.id.txtName,
		// R.id.txtLocation, R.id.txtCurrentQ, R.id.txtUrQ });
		//
		// setListAdapter(adapter);

		// // getting values from selected ListItem
		// String txtUrQ = ((TextView) findViewById(R.id.txtUrQ)).getText()
		// .toString();
		// String txtCurrentQ = ((TextView) findViewById(R.id.txtCurrentQ))
		// .getText().toString();
		// String txtName = ((TextView) findViewById(R.id.txtName)).getText()
		// .toString();
		// String txtLocation = ((TextView) findViewById(R.id.txtLocation))
		// .getText().toString();

		Button btnClose = (Button) findViewById(R.id.btnClose);

		// Binding Click event to Button
		btnClose.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// Closing SecondScreen Activity
				finish();
			}
		});

	}
}