package com.qcom.wifianalyzer;

import java.util.List;

import android.app.Activity;
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

		printConfiguredNetworks();
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
			output.setText("Output:");
			return true;
		case R.id.item_scan:
			// TODO
			return true;
		default:
			return false;
		}
	}

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
