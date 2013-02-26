package com.qcom.logservice;

import android.os.RemoteException;

import com.qcom.logcommon.ILogService;

public class ILogServiceImpl extends ILogService.Stub {

	@Override
	public void log(int priority, String tag, String msg) throws RemoteException {
		LogLib.logN(priority, tag, msg);
	}

}
