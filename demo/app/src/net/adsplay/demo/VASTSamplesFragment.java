//
//  VASTSamplesFragment.java
//
//  Copyright (c) 2014 Nexage. All rights reserved.
//
package net.adsplay.demo;

import java.util.ArrayList;

import net.adsplay.demo.adapter.VASTFileListAdapter;
import net.adsplay.demo.adapter.VASTListItem;
import net.adsplay.util.VASTLog;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class VASTSamplesFragment extends ListFragment {
	private final static String TAG = "VASTSamplesFragment";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		VASTLog.d(TAG, "onCreateView");

		VASTFileListAdapter adapter = new VASTFileListAdapter(inflater.getContext(), getTestFiles());
		setListAdapter(adapter);

		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
	
		super.onListItemClick(l, v, position, id);
		// Find the selected row
		VASTListItem selectedListItem = (VASTListItem) getListAdapter().getItem(position);
		VASTLog.d(TAG, "Selected List item: " + selectedListItem.getTitle());
		
		this.getListView().setClickable(false); // Disable multiple taps

		Activity activity = getActivity();		
		new AdsPlayVastRequest().execute(activity);		
	}

	private ArrayList<VASTListItem> getTestFiles() {	
		ArrayList<VASTListItem> testFiles = new ArrayList<VASTListItem>();	
		testFiles.add(new VASTListItem("Fshare", "fshare-15s.xml"));				
		return testFiles;
	}

}
