package com.projet.eseo.pocketbudget;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class NewIncomeActivity extends ActionBarActivity {

    private ArrayAdapter<CharSequence> categories_income_adapter;
    private Spinner categories_income_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_income);

        categories_income_spinner = (Spinner) findViewById(R.id.categories_income_spinner);
        categories_income_adapter = ArrayAdapter.createFromResource(this, R.array.categories_list_income, android.R.layout.simple_spinner_item);
        categories_income_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categories_income_spinner.setAdapter(categories_income_adapter);
    }


    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_income, menu);
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
