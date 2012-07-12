package com.test;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.test.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ClinicInfo extends Activity {

	ClinicQGettersSetters data;
	ImageButton back;
	ArrayList<Clinic> name;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.information);

		back = (ImageButton) findViewById(R.id.information_back);
		back.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent1 = new Intent(ClinicInfo.this,
						Clinic_finder2Activity.class);
				startActivity(intent1);
			}
		});
		String clinic_url;
		final Intent i = getIntent();
		final String id =i.getStringExtra("Id"); 
		/**
		 * Get the view of the layout within the main layout, so that we can add
		 * TextViews.
		 **/
		View layout = findViewById(R.id.layout);
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				// Perform action on click
				Intent myIntent = new Intent(getApplicationContext(), Register.class);
				myIntent.putExtra("Id", id);
				ClinicInfo.this.startActivity(myIntent);
				
				
			}
		});
		//
		// /**
		// * Create TextView Arrays to add the retrieved data to.
		// **/
		TextView clinic_name[];
		TextView latest_q_no[];
		TextView clinic_location[];
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
			
			
			
			
			clinic_url = "http://homes.soi.rp.edu.sg/101163/utsc/xml/get_q.php?clinic_id="+id;
//			String clinic_url1=clinic_url+id;
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
		latest_q_no = new TextView[data.getClinic_id().size()];
		clinic_location = new TextView[data.getClinic_location().size()];
		current_q_no = new TextView[data.getCurrent_q().size()];

		for (int u = 0; u < data.getClinic_name().size(); u++) {			

			clinic_name[u] = new TextView(this);
			clinic_name[u].setText(data.getClinic_name().get(u));
			
			clinic_location[u] = new TextView(this);
			clinic_location[u].setText(data.getClinic_location().get(u));		
			
			current_q_no[u] = new TextView(this);
			current_q_no[u].setText("Current Queue No. = "+data.getCurrent_q().get(u));
			
			latest_q_no[u] = new TextView(this);
			latest_q_no[u].setText("Latest Queue No. = "+data.getLatest_q().get(u));
			
			


//			
//			latest_q_no[i] = new TextView(this);
//			latest_q_no[i].setText("Number of Waiting --> "
//					+data.getLatest_q().get(i));
//			test[i] = new TextView(this);
//			test[i].setText("URL" +clinic_url+id);
			
		
			

			//
			// lat[i] = new TextView(this);
			// lat[i].setText("Clinic ID = " + data.getLat().get(i));
			//
			// lng[i] = new TextView(this);
			// lng[i].setText("Latest Q = " + data.getLng().get(i));
			//
			// distance[i] = new TextView(this);
			// distance[i].setText("Latest Q = " + data.getDistance().get(i));
			((ViewGroup) layout).addView(clinic_name[u]);	
			((ViewGroup) layout).addView(clinic_location[u]);				
			 ((ViewGroup) layout).addView(latest_q_no[u]);
			 ((ViewGroup) layout).addView(current_q_no[u]);


		}

	}
}