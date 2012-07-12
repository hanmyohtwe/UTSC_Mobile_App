package com.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.test.R;

public class Register extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form);

		final Intent i = getIntent();
		final String id = i.getStringExtra("Id");
		final EditText name = (EditText) findViewById(R.id.form_name);
		final EditText fin = (EditText) findViewById(R.id.form_fin);
		final EditText dob = (EditText) findViewById(R.id.form_dob);
		final EditText contact = (EditText) findViewById(R.id.form_contact);
		final EditText address = (EditText) findViewById(R.id.form_address);
		final Button submit = (Button) findViewById(R.id.submit);

		// Listening to button event
		submit.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// Starting a new Intent
				Intent patientQ = new Intent(getApplicationContext(),
						PatientQInfo.class);

				// Sending data to another Activity
				patientQ.putExtra("name", name.getText().toString());
				patientQ.putExtra("fin", fin.getText().toString());
				patientQ.putExtra("dob", dob.getText().toString());
				patientQ.putExtra("contact", contact.getText().toString());
				patientQ.putExtra("address", address.getText().toString());
				patientQ.putExtra("Id", id);

				// starting new activity
				startActivity(patientQ);

			}
		});
		//

	}
}