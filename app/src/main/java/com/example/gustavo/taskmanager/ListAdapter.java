package com.example.gustavo.taskmanager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.gustavo.taskmanager.model.Task;

import java.util.List;

/**
 * Created by Gustavo on 17/02/2017.
 */

public class ListAdapter extends ArrayAdapter<Task> {

    public ListAdapter(Context context, Task[] items) {
        super(context, R.layout.activity_main, items);
    }

    @Override
    public View getView(int position, final View convertView, final ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);

        Task task = getItem(position);
        TextView desc = (TextView) view.findViewById(R.id.taskDesc);
        //Button but = (Button) view.findViewById(R.id.taskButton);

        desc.setText(task.getDescription());

        return view;
    }
}