package com.fknt.voltage.smartshoppinglist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import com.fknt.voltage.smartshoppinglist.Adapters.ListHeadersAdapter;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ListHeadersAdapter adapter;
    ListView lvHeaders;

    private MainActivity getContext()
    {
        return MainActivity.this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter= new ListHeadersAdapter(GetHeaders(),getContext());
        lvHeaders= (ListView) findViewById(R.id.lvHeaders);
        lvHeaders.setAdapter(adapter);



    }

    void Refresh()
    {
        adapter= new ListHeadersAdapter(GetHeaders(),getContext());
        lvHeaders= (ListView) findViewById(R.id.lvHeaders);
        lvHeaders.setAdapter(adapter);
    }

    private List<ListHeader> GetHeaders()
    {
        return SQLite.select()
                .from(ListHeader.class)
                .queryList();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_new) {
            //openFile(FILENAME);
            CreateNewItem();
            Refresh();
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    private void CreateNewItem(String newHeaderName) {
        ListHeader item= new ListHeader();
        item.title=newHeaderName;
        item.dateCreate= new Date();
        item.TotalSum=0.0;
        item.save();
    }

    private void CreateNewItem()
    {
        AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.title_new_header_dialog);
        final EditText input= new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton(R.string.btn_ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newHeaderName=input.getText().toString();
                        CreateNewItem(newHeaderName);
                        dialog.dismiss();
                    }
                });

        builder.setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

}
