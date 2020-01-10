package com.example.orcun.shoppinghelper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AddItem extends Fragment {
    private EditText name;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View addItemView = inflater.inflate(R.layout.add_item, container, false);
        context = this.getActivity();
        name = addItemView.findViewById(R.id.addItemName);
        Button add = addItemView.findViewById(R.id.addItem);
        add.setOnClickListener();
        return addItemView;
    }
    @SuppressLint("StaticFieldLeak")
    AsyncTask<Object,Object,Object> AddItemTask = new AsyncTask<Object, Object, Object>() {
        @Override
        protected Object doInBackground(Object... objects) {
            Database db = new Database(context);
            db.addItem(name.getText().toString());
            return null;
        }
    };

    public View.OnClickListener addClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AddItemTask.execute((Object[])null);
        }
    };
}