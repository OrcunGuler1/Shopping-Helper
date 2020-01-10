package com.example.orcun.shoppinghelper;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

public class ShopHelperMain extends Activity {
    private long rowID;
    private EditText name;
    private Spinner item;
    private EditText price;
    private CursorAdapter itemAdapter;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] fieldList;
        int[] showList;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_helper_main);
        context = this.getApplicationContext();
        Button save = findViewById(R.id.save);
        Button delete = findViewById(R.id.delete);
        Button goBack = findViewById(R.id.goBack);
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        fieldList = new String[]{"name"};
        showList = new int[]{R.id.name};
        itemAdapter = new SimpleCursorAdapter(this, R.layout.item_cell, null, fieldList, showList, 0);
        save.setOnClickListener();
        delete.setOnClickListener();
        goBack.setOnClickListener();
        Bundle extras = getIntent().getExtras();
        rowID = extras.getLong("row_id");
        new GetItemsTask().execute(rowID);
    }

    private class GetItemsTask extends AsyncTask<Long, Object, Cursor> {
        Database db = new Database(context);

        @Override
        protected Cursor doInBackground(Long... longs) {
            db.open();
            return db.getItem(longs[0]);
        }

        @Override
        protected void onPostExecute(Cursor result) {
        super.onPostExecute(result);
        result.moveToFirst();
        int namePos = result.getColumnIndex("name");
        name.setText(result.getString(namePos));
            for (int i = 0; i < itemAdapter.getCount() ; i++) {
                Cursor cursor = (Cursor) itemAdapter.getItem(i);
                String itemName = cursor.getString(cursor.getColumnIndex("name"));
                String resultItemName = result.getString(namePos);
                if(itemName.equals(resultItemName)){
                    item.setSelection(i);
                    break;
                }
            }
            result.close();
            db.close();
        }
    }
    public View.OnClickListener saveClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    public View.OnClickListener deleteClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    public View.OnClickListener gobackClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
