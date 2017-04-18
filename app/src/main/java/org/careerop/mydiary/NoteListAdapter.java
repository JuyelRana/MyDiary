package org.careerop.mydiary;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Juyel_Rana on 12/9/2016.
 */
public class NoteListAdapter extends BaseAdapter {

    private Activity activity;
    private List<NoteList> noteLists;
    private LayoutInflater inflater;
    private TextView title;
    private TextView date;

    public NoteListAdapter(Activity activity, List<NoteList> noteListses) {

        this.activity = activity;
        this.noteLists = noteListses;
    }

    @Override
    public int getCount() {
        return noteLists.size();
    }

    @Override
    public Object getItem(int position) {
        return noteLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(inflater == null){
            inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }if(convertView == null){
            convertView = inflater.inflate(R.layout.note_row, null);
        }

        //Initialize custom listview
        title = (TextView) convertView.findViewById(R.id.txtTitle);
        date = (TextView) convertView.findViewById(R.id.txtDate);

        NoteList noteList = noteLists.get(position);

        //Set the the Note data to custom listview
        title.setText(noteList.getTitle());
        date.setText(noteList.getDate());

        return convertView;
    }
}
