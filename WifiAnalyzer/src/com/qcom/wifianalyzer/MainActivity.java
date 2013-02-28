package com.qcom.wifianalyzer;

import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView output;
	WifiManager wifi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		output = (TextView) findViewById(R.id.output);
		wifi = (WifiManager) getSystemService(WIFI_SERVICE);
	}

	@Override
	protected void onStart() {
		super.onStart();
		registerReceiver(scanReceiver, new IntentFilter(
				WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
	}

	@Override
	protected void onStop() {
		super.onStop();
		unregisterReceiver(scanReceiver);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item_configured_networks:
			printConfiguredNetworks();
			return true;
		case R.id.item_clear:
			clear();
			return true;
		case R.id.item_scan:
			wifi.startScan();
			return true;
		default:
			return false;
		}
	}
	
	private void clear() {
		output.setText("Output:");
	}

	private final BroadcastReceiver scanReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			List<ScanResult> list = wifi.getScanResults();
			clear();
			for (ScanResult item : list) {
				output.append(String.format("\n %s (%dMHz) (%d dBm)",
						item.SSID, item.frequency, item.level));
			}
		}
	};

	private void printConfiguredNetworks() {
		List<WifiConfiguration> list = wifi.getConfiguredNetworks();
		if (list == null) {
			output.append("\n No configured networks found!");
			return;
		}
		for (WifiConfiguration item : list) {
			output.append(String.format("\n %s", item.SSID));
		}
	}

}
