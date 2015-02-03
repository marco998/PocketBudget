package com.projet.eseo.pocketbudget;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;



public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BDDManager bddm= new BDDManager(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onResume(){
        super.onResume();
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

    public void goToIncomeList(View view)
    {
        Intent intent = new Intent(this, ListIncomeActivity.class);
        startActivity(intent);
    }


    public void goToNewIncome(View view)
    {
        Intent intent = new Intent(this, NewIncomeActivity.class);
        startActivity(intent);
    }

    public void goToNewExpenditure(View view)
    {
        Intent intent = new Intent(this, NewExpenditureActivity.class);
        startActivity(intent);
    }
}
