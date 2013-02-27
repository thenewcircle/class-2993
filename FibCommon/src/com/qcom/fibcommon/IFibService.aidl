package com.qcom.fibcommon;

import com.qcom.fibcommon.FibRequest;
import com.qcom.fibcommon.IFibListener;

interface IFibService {
	long fibJ(long n);
	long fibN(long n);
	long fib(in FibRequest request);
	oneway void asyncFib( in FibRequest request, in IFibListener listener );
}