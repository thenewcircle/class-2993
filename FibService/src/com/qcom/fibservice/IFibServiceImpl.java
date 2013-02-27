package com.qcom.fibservice;

import android.os.RemoteException;

import com.qcom.fibcommon.FibRequest;
import com.qcom.fibcommon.IFibListener;
import com.qcom.fibcommon.IFibService;

public class IFibServiceImpl extends IFibService.Stub {

	@Override
	public long fibJ(long n) throws RemoteException {
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
			return FibLib.fibJ(request.getN());
		case FibRequest.NATIVE:
			return FibLib.fibN(request.getN());
		default:
			return -1;
		}
	}

	@Override
	public void asyncFib(FibRequest request, IFibListener listener)
			throws RemoteException {
		long result = fib(request);	// this could take a while!
		listener.onResponse(result);
	}

}
