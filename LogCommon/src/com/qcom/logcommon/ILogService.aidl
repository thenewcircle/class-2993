package com.qcom.logcommon;

import com.qcom.logcommon.LogMessage;

interface ILogService {
	void log(int priority, String tag, String msg);
	void logger( in LogMessage msg);
}