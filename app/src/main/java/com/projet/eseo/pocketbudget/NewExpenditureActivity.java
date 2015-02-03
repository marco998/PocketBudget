package com.projet.eseo.pocketbudget;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class NewExpenditureActivity extends ActionBarActivity {

    private ArrayAdapter<CharSequence> categories_adapter;
    private Spinner categories_expenditure_spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expenditure);

        categories_expenditure_spinner = (Spinner) findViewById(R.id.categories_expenditure_spinner);
        categories_adapter = ArrayAdapter.createFromResource(this,R.array.categories_list_expenditure,android.R.layout.simple_spinner_item);

        categories_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categories_expenditure_spinner.setAdapter(categories_adapter);

    }

    @Override
         protected void onResume(){
            super.onResume();
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_expenditure, menu);
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
}
