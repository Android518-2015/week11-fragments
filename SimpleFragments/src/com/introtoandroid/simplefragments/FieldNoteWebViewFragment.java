package com.introtoandroid.simplefragments;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebViewFragment;

public class FieldNoteWebViewFragment extends WebViewFragment {

	private final static String TAG ="FRAG-WEBVIEWFRAGMENT";
	String url = "http://a-z-animals.com/animals/";

	public static FieldNoteWebViewFragment newInstance(int index) {
		Log.v(TAG, "Creating new instance: " + index);
		FieldNoteWebViewFragment fragment = new FieldNoteWebViewFragment();

		Bundle args = new Bundle();
		args.putInt("index", index);
		fragment.setArguments(args);
		return fragment;
	}

	public int getShownIndex() {
		int index = -1;
		Bundle args = getArguments();
		if (args != null) {
			index = args.getInt("index", -1);
		}
		if (index == -1) {
			Log.e(TAG, "Not an array index.");
		}

		return index;
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "FRAGMENT onResume():" +  getShownIndex());		
	}

	@Override
	public void onStart() {
		Log.d(TAG, "FRAGMENT onStart(): " + getShownIndex());
		super.onStart();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.d(TAG, "FRAGMENT onActivityCreated(): " + getShownIndex());
		super.onActivityCreated(savedInstanceState);

		// Load the URL after the Webview has been created in onCreateView()
		String[] fieldNoteAnimals = getResources().getStringArray(
				R.array.fieldnotes_array);
				//R.array.fieldnoteurls_array);
		int fieldNoteAnimalIx = getShownIndex();

		WebView webview = getWebView();
		webview.setPadding(2, 2, 2, 2);
		webview.getSettings().setLoadWithOverviewMode(true);
		webview.getSettings().setUseWideViewPort(true);
		// PMC was loading in browser, added this to load in webview
		webview.setWebViewClient(new WebViewClient());

		/*
		 * PMC hardware accelerated canvas rendering is not supported in Chromium WebView.
		 * so turn off hardware accelerated canvas:  (layer = software, not devfault hardware
		 */
		webview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		
		if (fieldNoteAnimalIx != -1) {
			webview.loadUrl(url+fieldNoteAnimals[fieldNoteAnimalIx]);
			//String fieldNoteUrl = fieldNoteUrls[fieldNoteUrlIndex];
			//webview.loadUrl(fieldNoteUrl);
		}
		else
		{
			webview.loadUrl(url);
		}

	}

	@Override
	public void onAttach(Activity activity) {
		Log.d(TAG, "FRAGMENT onAttach()");
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "FRAGMENT onCreate()");
		super.onCreate(savedInstanceState);

		Log.d(TAG, "OnCreate(): " + getShownIndex());

	}

	@Override
	public void onDestroy() {
		Log.d(TAG, "FRAGMENT onDestroy()");
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		Log.d(TAG, "FRAGMENT onDetach()");
		super.onDetach();
	}

	@Override
	public void onPause() {
		Log.d(TAG, "FRAGMENT onPause(): " + getShownIndex());
		super.onPause();
	}

	@Override
	public void onStop() {
		Log.d(TAG, "FRAGMENT onStop(): " + getShownIndex());
		super.onStop();
	}
}