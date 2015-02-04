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


public class ListExpenditureActivity extends ActionBarActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_expenditure);

        listView=(ListView)findViewById(R.id.listViewExpenditure);

        BDDManager bdd= new BDDManager(this);
        bdd.open();
        ArrayList<Expenditure> listString = bdd.getAllDepense();
        StringAdapterExpenditure adapter = new StringAdapterExpenditure(getApplicationContext(), listString);

        listView.setAdapter(adapter);
        bdd.close();
    }

    @Override
    public void onResume(){
        super.onResume();
        BDDManager bdd= new BDDManager(this);
        bdd.open();
        ArrayList<Expenditure> listString = bdd.getAllDepense();
        StringAdapterExpenditure adapter = new StringAdapterExpenditure(getApplicationContext(), listString);

        listView.setAdapter(adapter);
        bdd.close();
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
