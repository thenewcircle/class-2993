package com.qcom.fibclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qcom.fibcommon.IFibService;

public class MainActivity extends Activity {
	EditText textN;
	TextView textOut;
	IFibService fibService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textN = (EditText) findViewById(R.id.text_n);
		textOut = (TextView) findViewById(R.id.text_out);
	}

	@Override
	protected void onStart() {
		super.onStart();
		boolean bind = bindService(
				new Intent("com.qcom.fibcommon.IFibService"),
				connection, Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onStop() {
		super.onStop();
		unbindService(connection);
	}

	ServiceConnection connection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			fibService = IFibService.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			fibService = null;
		}

	};

	public void onClick(View v) {
		long n = Long.parseLong(textN.getText().toString());

		try {
			// Java
			long start = System.currentTimeMillis();
			long resultJ = fibService.fibJ(n);
			long timeJ = System.currentTimeMillis() - start;
			textOut.append(String.format("\n fibJ(%d)=%d (%d ms)", n, resultJ,
					timeJ));

			// Native
			start = System.currentTimeMillis();
			long resultN = fibService.fibN(n);
			long timeN = System.currentTimeMillis() - start;
			textOut.append(String.format("\n fibN(%d)=%d (%d ms)", n, resultN,
					timeN));
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

}
