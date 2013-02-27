package com.qcom.fibcommon;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

public class FibManager {
	private Context context;
	private IFibService fibService;

	public FibManager(Context context) {
		this.context = context;
		boolean bind = context.bindService(new Intent(
				"com.qcom.fibcommon.IFibService"), connection,
				Context.BIND_AUTO_CREATE);
	}

	@Override
	public void finalize() {
		context.unbindService(connection);
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

	// --- Proxy calls ---
	public long fibJ(long n) {
		if (fibService == null) {
			return -1;
		}
		try {
			return fibService.fibJ(n);
		} catch (RemoteException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public long fibN(long n) {
		if (fibService == null) {
			return -1;
		}
		try {
			return fibService.fibN(n);
		} catch (RemoteException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public long fib(FibRequest request) {
		if (fibService == null) {
			return -1;
		}
		try {
			return fibService.fib(request);
		} catch (RemoteException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public void asyncFib(FibRequest request, IFibListener listener) {
		if (fibService == null) {
			return;
		}
		try {
			fibService.asyncFib(request, listener);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}



}
