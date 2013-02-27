package com.qcom.logcommon;

import android.os.Parcel;
import android.os.Parcelable;

public class LogMessage implements Parcelable {
	private int priority;
	private String tag, message;

	public LogMessage(int priority, String tag, String message) {
		super();
		this.priority = priority;
		this.tag = tag;
		this.message = message;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// --- Parcelable methods ---
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(priority);
		dest.writeString(tag);
		dest.writeString(message);
	}

	public static final Parcelable.Creator<LogMessage> CREATOR = new Parcelable.Creator<LogMessage>() {

		@Override
		public LogMessage createFromParcel(Parcel source) {
			return new LogMessage(source.readInt(), source.readString(),
					source.readString());
		}

		@Override
		public LogMessage[] newArray(int size) {
			return new LogMessage[size];
		}
	};
}
