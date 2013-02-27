package com.qcom.logservice;

import android.os.RemoteException;

import com.qcom.logcommon.ILogService;
import com.qcom.logcommon.LogMessage;

public class ILogServiceImpl extends ILogService.Stub {

	@Override
	public void log(int priority, String tag, String msg) throws RemoteException {
		LogLib.logN(priority, tag, msg);
	}

	@Override
	public void logger(LogMessage in) throws RemoteException {
		LogLib.logJ(in.getPriority(), in.getTag(), in.getMessage());
	}

}
