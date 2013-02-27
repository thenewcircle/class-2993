package com.qcom.fibcommon;

import com.qcom.fibcommon.FibRequest;

interface IFibService {
	long fibJ(long n);
	long fibN(long n);
	long fib(in FibRequest request);
}