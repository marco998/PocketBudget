package com.projet.eseo.pocketbudget;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BDDManager bddm= new BDDManager(this);
        bddm.open();
        TextView diff = (TextView)findViewById(R.id.difference);
        float valueDiff= bddm.difference();
        if(valueDiff<0)
            diff.setTextColor(Color.RED);
        else
            diff.setTextColor(Color.GREEN);
        diff.setText(" "+String.valueOf(valueDiff)+"€");

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

    public void goToExpenditureList(View view)
    {
        Intent intent = new Intent(this, ListExpenditureActivity.class);
        startActivity(intent);
    }




}
