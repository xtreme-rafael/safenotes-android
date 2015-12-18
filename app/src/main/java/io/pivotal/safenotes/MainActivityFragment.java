package io.pivotal.safenotes;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import go.safenotes_core.Safenotes_core;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private int mCounter = 1;
    private ListView mNotesListView;
    private Gson mGson;
    private Type mNoteArrayType;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGson = new Gson();
        mNoteArrayType = new TypeToken<ArrayList<Safenotes_core.Note>>() {}.getType();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mNotesListView = (ListView) view.findViewById(R.id.notes_list);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        refreshNotes();
    }

    private void refreshNotes() {
        String notesJson = Safenotes_core.GetNotes();
        ArrayList<Safenotes_core.Note> notes = mGson.fromJson(notesJson, mNoteArrayType);
        NotesAdapter adapter = new NotesAdapter(getContext(), R.layout.listitem_note, notes);
        mNotesListView.setAdapter(adapter);
    }

    public void addNote() {
        Safenotes_core.AddNote("Note " + mCounter, "Content for note " + mCounter++);
        refreshNotes();
    }
}
