package com.example.orcun.shoppinghelper;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

public class ItemsList extends ListFragment {
    protected static final String ROW_ID = "row_id";
    private CursorAdapter items;
    private Context context;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    View itemsView = inflater.inflate(R.layout.items_list,container,false);
    context = this.getActivity();
    String[] fieldList = new String[]{"itemName"};
    int[] showList = new int[]{R.id.itemName};
    items = new SimpleCursorAdapter(this.getActivity(),R.layout.item_cell,null,fieldList,showList,0);
    setListAdapter(items);
    new GetAllItemsTask().execute((Object[])null);
    return itemsView;
    }
    private class GetAllItemsTask extends AsyncTask<Object,Object,Cursor>{
        Database db = new Database(context);
        @Override
        protected Cursor doInBackground(Object... objects) {
            db.open();
            return db.getAllItems();
        }
        @Override
        protected void onPostExecute(Cursor result){
            items.changeCursor(result);
            db.close();
        }
    }


}
