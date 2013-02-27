package com.qcom.fibcommon;

import android.os.Parcel;
import android.os.Parcelable;

public class FibRequest implements Parcelable {
	public static final int JAVA = 1;
	public static final int NATIVE = 2;
	private int algorithm;
	private long n;
	
	public FibRequest(int algorithm, long n) {
		super();
		this.algorithm = algorithm;
		this.n = n;
	}
	public int getAlgorithm() {
		return algorithm;
	}
	public void setAlgorithm(int algorithm) {
		this.algorithm = algorithm;
	}
	public long getN() {
		return n;
	}
	public void setN(long n) {
		this.n = n;
	}
	
	// --- Parcelable stuff ---
	
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(algorithm);
		dest.writeLong(n);
	}
	
	public static final Parcelable.Creator<FibRequest> CREATOR = new Parcelable.Creator<FibRequest>() {

		@Override
		public FibRequest createFromParcel(Parcel source) {
			return new FibRequest(source.readInt(), source.readLong());
		}

		@Override
		public FibRequest[] newArray(int size) {
			return new FibRequest[size];
		}
	};
	
}
