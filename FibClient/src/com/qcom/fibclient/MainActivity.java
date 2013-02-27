package com.qcom.fibclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qcom.fibcommon.FibManager;
import com.qcom.fibcommon.FibRequest;

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
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		fibManager = null;
	}

	public void onClick(View v) {
		long n = Long.parseLong(textN.getText().toString());

		FibRequest request = new FibRequest(FibRequest.JAVA, n);
		// Java
		long start = System.currentTimeMillis();
		long resultJ = fibManager.fib( request );
		long timeJ = System.currentTimeMillis() - start;
		textOut.append(String.format("\n fibJ(%d)=%d (%d ms)", n, resultJ,
				timeJ));

		// Native
		request.setAlgorithm(FibRequest.NATIVE);
		start = System.currentTimeMillis();
		long resultN = fibManager.fib(request);
		long timeN = System.currentTimeMillis() - start;
		textOut.append(String.format("\n fibN(%d)=%d (%d ms)", n, resultN,
				timeN));
	}

}
