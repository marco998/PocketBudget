package com.projet.eseo.pocketbudget;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;


public class ListIncomeActivity extends ActionBarActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_expenditure);
        listView=(ListView)findViewById(R.id.listViewExpenditure);

        BDDManager bdd= new BDDManager(this);
        bdd.open();
        final ArrayList<Income> listString = bdd.getAllRevenus();
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
    }

    @Override
    public void onResume(){
        super.onResume();
        /*BDDManager bdd= new BDDManager(this);
        bdd.open();
        ArrayList<Income> listString = bdd.getAllRevenus();
        StringAdapter adapter = new StringAdapter(getApplicationContext(), listString);

        listView.setAdapter(adapter);
        bdd.close();*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_income, menu);
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


    public void goToNewIncome(View view)
    {
        Intent intent = new Intent(this, NewIncomeActivity.class);
        startActivity(intent);
    }
}
