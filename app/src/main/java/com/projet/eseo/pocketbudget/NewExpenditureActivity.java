package com.projet.eseo.pocketbudget;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;


public class NewExpenditureActivity extends ActionBarActivity {

    private ArrayAdapter<CharSequence> categories_adapter;
    private Spinner categories_expenditure_spinner;
    private EditText date;
    private EditText nom;
    private EditText montant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expenditure);

        categories_expenditure_spinner = (Spinner) findViewById(R.id.categories_depense_spinner);
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

   public void addNewExpenditure(View view){
       date = (EditText) findViewById(R.id.date_depense_editText);
       categories_expenditure_spinner = (Spinner) findViewById(R.id.categories_depense_spinner);
       nom = (EditText) findViewById(R.id.nom_depense_editText);
       montant = (EditText) findViewById(R.id.montant_depense_editText);

       String dateDepense = date.getText().toString();
       String categorieDepense = categories_expenditure_spinner.getSelectedItem().toString();
       String nomDepense = nom.getText().toString();
       int montantDepense =  Integer.parseInt(montant.getText().toString());


       Context context = getApplicationContext();
       CharSequence text = "Date = "+dateDepense+" - Categorie : "+categorieDepense+" - Nom : "+nomDepense+" - Montant : "+montantDepense;
       int duration = Toast.LENGTH_LONG;

       Toast toast = Toast.makeText(context, text, duration);
       toast.show();

       Expenditure newExpenditur = new Expenditure();
       newExpenditur.setCategorie(categorieDepense);
       newExpenditur.setMontant(montantDepense);
       newExpenditur.setNom(nomDepense);

       SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
       try{
           newExpenditur.setDate(formatter.parse(dateDepense));
       }catch(Exception e){
       }

      /* BDDManager bddManager = new BDDManager(this);
       bddManager.open();
       bddManager.insertDepense(newExpenditur);
       bddManager.close();*/


    }
}
