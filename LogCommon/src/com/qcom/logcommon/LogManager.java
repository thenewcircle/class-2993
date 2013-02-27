package com.qcom.logcommon;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

public class LogManager {
	private Context context;
	private ILogService service;

	private ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName component, IBinder binder) {
			service = ILogService.Stub.asInterface(binder);
		}

		@Override
		public void onServiceDisconnected(ComponentName component) {
			service = null;
		}
	};

	public LogManager(Context context) {
		this.context = context;

		context.bindService(new Intent("com.qcom.logcommon.ILogService"), conn,
				Context.BIND_AUTO_CREATE);
	}

	@Override
	public void finalize() {
		context.unbindService(conn);
	}

	// --- Proxy calls ---

	public void log(int priority, String tag, String msg) {
		if (service == null)
			return;
		try {
			service.log(priority, tag, msg);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void logger(LogMessage message) {
		if (service == null)
			return;
		try {
			service.logger(message);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
