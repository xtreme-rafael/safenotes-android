package io.pivotal.safenotes;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import go.safenotes_core.Safenotes_core;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private int mCounter = 1;
    private TextView mTextView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mTextView = (TextView) view.findViewById(R.id.textview);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        String notes = Safenotes_core.GetNotes();
        mTextView.setText(notes);
    }

    public void addNote() {
        Safenotes_core.AddNote("Note " + mCounter, "Content for note " + mCounter++);
        String notes = Safenotes_core.GetNotes();
        mTextView.setText(notes);
    }
}
