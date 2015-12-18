package io.pivotal.safenotes;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import go.safenotes_core.Safenotes_core;

/**
 * Created by pivotal on 2015-12-18.
 */
public class NotesAdapter extends ArrayAdapter<Safenotes_core.Note> {
    private int mLayoutResourceId;

    public NotesAdapter(Context context, int resource, List<Safenotes_core.Note> notes) {
        super(context, resource, notes);
        mLayoutResourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if(row == null)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(mLayoutResourceId, parent, false);
        }

        Safenotes_core.Note note = getItem(position);

        TextView title = (TextView) row.findViewById(R.id.note_title);
        title.setText(note.getTitle());

        TextView date = (TextView) row.findViewById(R.id.note_date);
        date.setText(getDate(note.getTimestamp()));

        return row;
    }

    private String getDate(long timestampInSeconds) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(timestampInSeconds * 1000L);
        return DateFormat.format("d/MMM/yyyy h:ma", calendar).toString();
    }
}
