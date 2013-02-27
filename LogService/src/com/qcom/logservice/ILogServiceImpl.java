package com.qcom.logservice;

import android.content.Context;
import android.os.RemoteException;

import com.qcom.logcommon.ILogService;
import com.qcom.logcommon.LogMessage;

public class ILogServiceImpl extends ILogService.Stub {
	private Context context;

	public ILogServiceImpl(Context context) {
		this.context = context;
	}

	@Override
	public void log(int priority, String tag, String msg)
			throws RemoteException {
		context.enforceCallingOrSelfPermission(
				"com.qcom.permission.LOG_NATIVE", "Can't use native log!");
		LogLib.logN(priority, tag, msg);
	}

	@Override
	public void logger(LogMessage in) throws RemoteException {
		LogLib.logJ(in.getPriority(), in.getTag(), in.getMessage());
	}

}
