package com.qcom.fibclient;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qcom.fibcommon.FibManager;
import com.qcom.fibcommon.FibRequest;
import com.qcom.fibcommon.IFibListener;

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
		handler = null;
	}

	private static final int MSG_ID = 42;
	private final IFibListener listener = new IFibListener.Stub() {

		/** Executes on a non-UI thread */
		@Override
		public void onResponse(long result) throws RemoteException {
			Log.d("Fib", String.format("%d", result));
			Message msg = handler.obtainMessage(MSG_ID, result);
			handler.sendMessage(msg);
		}
	};

	private final Handler handler = new Handler() {

		/** Executes on UI thread */
		@Override
		public void handleMessage(Message msg) {
			if (msg.what != MSG_ID)
				return;
			textOut.append(String.format("\n fibJ(?)=%d (? ms)", msg.obj));
		}

	};

	public void onClick(View v) {
		long n = Long.parseLong(textN.getText().toString());

		FibRequest request = new FibRequest(FibRequest.JAVA, n);
		// Java
		long start = System.currentTimeMillis();
		fibManager.asyncFib(request, listener);
		long timeJ = System.currentTimeMillis() - start;
		textOut.append(String.format("\n fibJ(%d)=? (%d ms)", n, timeJ));

		// Native
		// request.setAlgorithm(FibRequest.NATIVE);
		// start = System.currentTimeMillis();
		// long resultN = fibManager.fib(request);
		// long timeN = System.currentTimeMillis() - start;
		// textOut.append(String.format("\n fibN(%d)=%d (%d ms)", n, resultN,
		// timeN));
	}

}
