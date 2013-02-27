package com.qcom.fibservice;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.util.Log;

import com.qcom.fibcommon.FibRequest;
import com.qcom.fibcommon.IFibListener;
import com.qcom.fibcommon.IFibService;

public class IFibServiceImpl extends IFibService.Stub {
	private Context context;

	public IFibServiceImpl(Context context) {
		this.context = context;
	}

	@Override
	public long fibJ(long n) throws RemoteException {
		// Enforce permission
		context.enforceCallingOrSelfPermission(
				"com.qcom.permission.FIB_SERVICE_SLOW",
				"You don't got com.qcom.permission.FIB_SERVICE_SLOW permission!!!");

		return FibLib.fibJ(n);
	}

	@Override
	public long fibN(long n) throws RemoteException {
		return FibLib.fibN(n);
	}

	@Override
	public long fib(FibRequest request) throws RemoteException {
		switch (request.getAlgorithm()) {
		case FibRequest.JAVA:
			return fibJ(request.getN());
		case FibRequest.NATIVE:
			return fibN(request.getN());
		default:
			return -1;
		}
	}

	@Override
	public void asyncFib(FibRequest request, IFibListener listener)
			throws RemoteException {
		long result = fib(request); // this could take a while!
		listener.onResponse(result);
	}

}
