package com.introtoandroid.simplefragments;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FieldNoteListFragment extends ListFragment implements
		FragmentManager.OnBackStackChangedListener {

	int mCurPosition = -1;
	boolean mShowTwoFragments;
	private final static String TAG ="FRAG-LISTFRAGMENT";

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		// Populate our ListView control within the Fragment
		String[] fieldNotes = getResources().getStringArray(
				R.array.fieldnotes_array);
		setListAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_activated_1, fieldNotes));

		// Check which state we're in
		View detailsFrame = getActivity().findViewById(R.id.fieldentry);
		mShowTwoFragments = detailsFrame != null
				&& detailsFrame.getVisibility() == View.VISIBLE;

		if (savedInstanceState != null) {
			mCurPosition = savedInstanceState.getInt("curChoice", 0);
		}

		if (mShowTwoFragments == true || mCurPosition != -1) {
			// Set the initial url to our default blog post, or the last one shown
			viewAnimalInfo(mCurPosition);
		}

		// monitor back stack changes to update list view
		getFragmentManager().addOnBackStackChangedListener(this);

	}

	@Override
	public void onBackStackChanged() {
		// update position
		FieldNoteWebViewFragment details = (FieldNoteWebViewFragment) getFragmentManager()
				.findFragmentById(R.id.fieldentry);
		if (details != null) {
			mCurPosition = details.getShownIndex();
			getListView().setItemChecked(mCurPosition, true);
			
			// if we're in single pane, then we need to switch forward to the viewer
			if (!mShowTwoFragments) {
				viewAnimalInfo(mCurPosition);
			}
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("curChoice", mCurPosition);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		viewAnimalInfo(position);
	}

	void viewAnimalInfo(int index) {
		mCurPosition = index;
		if (mShowTwoFragments == true) {
			// Check what fragment is currently shown, replace if needed.
			FieldNoteWebViewFragment details = (FieldNoteWebViewFragment) getFragmentManager()
					.findFragmentById(R.id.fieldentry);
			if (details == null || details.getShownIndex() != index) {

				// Make new fragment to show this selection.
				FieldNoteWebViewFragment newDetails = FieldNoteWebViewFragment
						.newInstance(index);

				// Execute a transaction, replacing any existing fragment
				// with this one inside the frame.
				FragmentManager fm = getFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();
				ft.replace(R.id.fieldentry, newDetails);
				// Add this fragment instance to the back-stack of the Activity
				// so we can backtrack through our field notes
				if (index != -1) {
					String[] fieldNotes = getResources().getStringArray(
							R.array.fieldnotes_array);
					String strBackStackTagName = fieldNotes[index];
					ft.addToBackStack(strBackStackTagName);
				}
				// Fade between Urls
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}

		} else {
			// Otherwise we need to launch a new activity to display
			// the dialog fragment with selected text.
			Intent intent = new Intent();
			intent.setClass(getActivity(), FieldNoteViewActivity.class);
			intent.putExtra("index", index);
			startActivity(intent);
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
		Log.d(TAG, "FRAGMENT onPause()");
		super.onPause();
	}

	@Override
	public void onResume() {
		Log.d(TAG, "FRAGMENT onResume(): "
				+ mCurPosition);
		super.onResume();
	}

	@Override
	public void onStart() {
		Log.d(TAG, "FRAGMENT onStart()");
		super.onStart();
	}

	@Override
	public void onStop() {
		Log.d(TAG, "FRAGMENT onStop()");
		super.onStop();
	}

}