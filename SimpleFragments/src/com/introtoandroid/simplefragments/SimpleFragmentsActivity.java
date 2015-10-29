package com.introtoandroid.simplefragments;

/*
 *  This is the main activity, pretty simple eh?
 *  The action is in the layout!
 *  Default layout and landscape layout.
 */


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class SimpleFragmentsActivity extends Activity {
	private final static String TAG ="FRAG-SIMPLEACTIVITY";
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_fragments_layout);
    	Log.d(TAG, "onCreate()");
    }
	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "onStart()");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume()");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(TAG, "onPause()");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG, "onStop()");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy()");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d(TAG, "onRestart()");
	}
}