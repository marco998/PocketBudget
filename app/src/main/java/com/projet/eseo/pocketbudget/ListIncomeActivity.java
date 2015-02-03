package com.projet.eseo.pocketbudget;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;


public class ListIncomeActivity extends ActionBarActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_incomes);
        listView=(ListView)findViewById(R.id.listViewRevenus);

        BDDManager bdd= new BDDManager(this);
        bdd.open();
        Income income= new Income(new Date(12,01,2012),"Test","Revenu",1000);
        bdd.insertRevenu(income);
        ArrayList<Income> listString = bdd.getAllRevenus();
        StringAdapter adapter = new StringAdapter(getApplicationContext(), listString);

        listView.setAdapter(adapter);

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
