package com.test;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CopyOfPatientQInfo extends Activity {

	ClinicQGettersSetters data;

	// Receiving the Data
	Intent i = getIntent();
	String id = i.getStringExtra("Id");
	String name = i.getStringExtra("name");
	String fin = i.getStringExtra("fin");
	String dob = i.getStringExtra("dob");
	String contact = i.getStringExtra("contact");
	String address = i.getStringExtra("address");

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		String clinic_url;

		/**
		 * Get the view of the layout within the main layout, so that we can add
		 * TextViews.
		 **/
		View layout = findViewById(R.id.layout);
		//
		// /**
		// * Create TextView Arrays to add the retrieved data to.
		// **/
		TextView clinic_name[];
		TextView clinic_location[];
		TextView booked_number[];
		TextView current_q_no[];

		//
		try {
			//
			// /**
			// * Create a new instance of the SAX parser
			// **/
			SAXParserFactory saxPF = SAXParserFactory.newInstance();
			SAXParser saxP = saxPF.newSAXParser();
			XMLReader xmlR = saxP.getXMLReader();
			//

			clinic_url = "http://homes.soi.rp.edu.sg/101163/utsc/xml/add_patient.php?patient_IC="
					+ fin
					+ "&patient_name="
					+ name
					+ "&patient_dob="
					+ dob
					+ "&patient_contact="
					+ contact
					+ "&patient_address="
					+ address + "&clinic_id=" + id;

			// String clinic_url1=clinic_url+id;
			URL url = new URL(clinic_url);

			// // URL
			// // of
			// // the
			// // XMLv
			// // double lat;
			// // double long;
			//
			// // String var="        "+st+"/"
			//
			// /**
			// * Create the Handler to handle each of the XML tags.
			// **/
			ClinicQHandler myXMLHandler = new ClinicQHandler();
			xmlR.setContentHandler(myXMLHandler);
			xmlR.parse(new InputSource(url.openStream()));

		} catch (Exception e) {
			System.out.println(e);
		}
		//
		data = ClinicQHandler.data;

		//
		// /**
		// * Makes the TextView length the size of the TextView arrays by
		// getting
		// * the size of the
		// **/

		clinic_name = new TextView[data.getClinic_name().size()];
		clinic_location = new TextView[data.getClinic_location().size()];
		current_q_no = new TextView[data.getCurrent_q().size()];
		booked_number = new TextView[data.getBooked_q().size()];

		for (int u = 0; u < data.getClinic_name().size(); u++) {

			clinic_name[u] = new TextView(this);
			clinic_name[u].setText(data.getClinic_name().get(u));

			clinic_location[u] = new TextView(this);
			clinic_location[u].setText(data.getClinic_location().get(u));

			booked_number[u] = new TextView(this);
			booked_number[u].setText("Your Queue No. = "
					+ data.getBooked_q().get(u));

			current_q_no[u] = new TextView(this);
			current_q_no[u].setText("Current Queue No. = "
					+ data.getCurrent_q().get(u));

			((ViewGroup) layout).addView(clinic_name[u]);
			((ViewGroup) layout).addView(clinic_location[u]);
			((ViewGroup) layout).addView(booked_number[u]);
			((ViewGroup) layout).addView(current_q_no[u]);

		}

	}
}