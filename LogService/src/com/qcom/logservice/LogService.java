package com.qcom.logservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class LogService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return new ILogServiceImpl(this);
	}

}
