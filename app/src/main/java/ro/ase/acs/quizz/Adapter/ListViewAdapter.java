package ro.ase.acs.quizz.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import java.util.List;

import ro.ase.acs.quizz.*;
import ro.ase.acs.quizz.Model.Answer;


public class ListViewAdapter extends ArrayAdapter<String> {
    public ListViewAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
    }
}
