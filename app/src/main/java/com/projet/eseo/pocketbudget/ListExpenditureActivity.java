package com.projet.eseo.pocketbudget;


import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;


public class ListExpenditureActivity extends ActionBarActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_expenditure);

        listView=(ListView)findViewById(R.id.listViewExpenditure);

        final BDDManager bdd= new BDDManager(this);
        bdd.open();
        Expenditure expenditure= new Expenditure(new Date(12,01,2012),"Test","Revenu",1000);
        expenditure.setId(bdd.insertDepense(expenditure));
        final ArrayList<Expenditure> listString = bdd.getAllDepense();

        ArrayAdapter<Expenditure> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, listString) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(listString.get(position).getNom());
                text2.setText(String.valueOf(listString.get(position).getMontant()));
                return view;
            }
        };

        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override

            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ListExpenditureActivity.this);
                builder.setMessage(R.string.dialog_delete_message)
                        .setTitle(R.string.dialog_delete_title);
                builder.setPositiveButton(R.string.dialog_delete_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int value=bdd.removeDepenseWithID(position+1);

                    }
                });
                builder.setNegativeButton(R.string.dialog_delete_no, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        /*BDDManager bdd= new BDDManager(this);
        bdd.open();
        ArrayList<Expenditure> listString = bdd.getAllDepense();
        StringAdapterExpenditure adapter = new StringAdapterExpenditure(getApplicationContext(), listString);

        listView.setAdapter(adapter);
        bdd.close();*/
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_expenditure, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToNewExpenditure(View view)
    {
        Intent intent = new Intent(this, NewExpenditureActivity.class);
        startActivity(intent);
    }

}
