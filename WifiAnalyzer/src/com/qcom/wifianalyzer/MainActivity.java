package com.qcom.wifianalyzer;

import java.util.Collection;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.net.wifi.p2p.WifiP2pManager.ChannelListener;
import android.net.wifi.p2p.WifiP2pManager.PeerListListener;
import android.os.Bundle;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView output;
	WifiManager wifi;
	WifiP2pManager wifiP2p;
	WifiP2pManager.Channel channel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		output = (TextView) findViewById(R.id.output);
		wifi = (WifiManager) getSystemService(WIFI_SERVICE);
		wifiP2p = (WifiP2pManager) getSystemService(WIFI_P2P_SERVICE);
		channel = wifiP2p.initialize(this, Looper.getMainLooper(),
				channelListener);
	}

	@Override
	protected void onStart() {
		super.onStart();
		registerReceiver(scanReceiver, new IntentFilter(
				WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
		registerReceiver(peerReceiver, new IntentFilter(
				WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION));
	}

	@Override
	protected void onStop() {
		super.onStop();
		unregisterReceiver(scanReceiver);
		unregisterReceiver(peerReceiver);
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
		case R.id.item_discover_peers:
			wifiP2p.discoverPeers(channel, actionListener);
			return true;
		default:
			return false;
		}
	}

	private void clear() {
		output.setText("Output:");
	}

	// --- Wifi Stuff ---

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

	// --- WifiP2p stuff ---
	private final ChannelListener channelListener = new WifiP2pManager.ChannelListener() {

		@Override
		public void onChannelDisconnected() {
			clear();
			output.append("\n WifiP2p channel disconnected");
		}
	};

	private final ActionListener actionListener = new WifiP2pManager.ActionListener() {

		@Override
		public void onFailure(int reason) {
			clear();
			output.append("\n Failed to initialize WifiP2p");
		}

		@Override
		public void onSuccess() {
			clear();
			output.append("\n Initialized WifiP2p");
		}
	};

	private final BroadcastReceiver peerReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			wifiP2p.requestPeers(channel, new PeerListListener() {

				@Override
				public void onPeersAvailable(WifiP2pDeviceList deviceList) {
					Collection<WifiP2pDevice> list = deviceList.getDeviceList();
					clear();
					for (WifiP2pDevice item : list) {
						output.append(String.format("\n %s (%s) %s",
								item.deviceName, item.deviceAddress,
								item.status));
					}
				}
			});
		}
	};
}
