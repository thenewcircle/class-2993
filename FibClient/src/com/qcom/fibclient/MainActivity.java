package com.qcom.fibclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qcom.fibcommon.FibManager;

public class MainActivity extends Activity {
	EditText textN;
	TextView textOut;
	FibManager fibManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textN = (EditText) findViewById(R.id.text_n);
		textOut = (TextView) findViewById(R.id.text_out);

		fibManager = new FibManager(this);
	}

	public void onClick(View v) {
		long n = Long.parseLong(textN.getText().toString());

		// Java
		long start = System.currentTimeMillis();
		long resultJ = fibManager.fibJ(n);
		long timeJ = System.currentTimeMillis() - start;
		textOut.append(String.format("\n fibJ(%d)=%d (%d ms)", n, resultJ,
				timeJ));

		// Native
		start = System.currentTimeMillis();
		long resultN = fibManager.fibN(n);
		long timeN = System.currentTimeMillis() - start;
		textOut.append(String.format("\n fibN(%d)=%d (%d ms)", n, resultN,
				timeN));
	}

}
