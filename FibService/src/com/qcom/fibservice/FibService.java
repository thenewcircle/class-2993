package com.qcom.fibservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class FibService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return new IFibServiceImpl();
	}

}
