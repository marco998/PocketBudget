package com.projet.eseo.pocketbudget;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Activité gérant la modification et la visualisation d'un revenu
 */

public class ExpenditureActivity extends ActionBarActivity {

    private ArrayAdapter<CharSequence> categories_expenditure_adapter;
    private Spinner categories_expenditure_spinner;
    private EditText date;
    private EditText nom;
    private EditText montant;


    private TextView pDisplayDate;
    private ImageButton pPickDate;
    private int pYear;
    private int pMonth;
    private int pDay;
    private long id;
    static final int DATE_DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenditure);

        categories_expenditure_spinner = (Spinner) findViewById(R.id.categories_depense_spinner);
        categories_expenditure_adapter = ArrayAdapter.createFromResource(this, R.array.categories_list_expenditure, android.R.layout.simple_spinner_item);
        categories_expenditure_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categories_expenditure_spinner.setAdapter(categories_expenditure_adapter);

        /** Capture our View elements */
        pDisplayDate = (TextView) findViewById(R.id.displayDate);
        pPickDate = (ImageButton) findViewById(R.id.pickDate);

        /** Listener for click event of the button */
        pPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        /** Get the current date */
        final Calendar cal = Calendar.getInstance();
        pYear = cal.get(Calendar.YEAR);
        pMonth = cal.get(Calendar.MONTH);
        pDay = cal.get(Calendar.DAY_OF_MONTH);

        /** Display the current date in the TextView */
        updateDisplay();

        Intent intent = getIntent();
        id = intent.getExtras().getLong("ID");
        Toast.makeText(getApplicationContext(), "testCREATE : "+id,Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "test : "+id,Toast.LENGTH_LONG).show();
        Expenditure expenditure = new Expenditure();
        BDDManager bdd = new BDDManager(this);
        bdd.open();
        expenditure = bdd.getDepenseWithId(id);
        bdd.close();

        categories_expenditure_spinner = (Spinner) findViewById(R.id.categories_depense_spinner);
        nom = (EditText) findViewById(R.id.nom_depense_editText);
        montant = (EditText) findViewById(R.id.montant_depense_editText);

        String compareValue = expenditure.getCategorie();
        Toast.makeText(getApplicationContext(), "test : "+compareValue,Toast.LENGTH_LONG).show();
        if (!compareValue.equals(null)) {
            int spinnerPostion = categories_expenditure_adapter.getPosition(compareValue);
            categories_expenditure_spinner.setSelection(spinnerPostion);
            spinnerPostion = 0;
        }

        nom.setText(expenditure.getNom());
        montant.setText(String.valueOf(expenditure.getMontant()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_expenditure, menu);
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


    private DatePickerDialog.OnDateSetListener pDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    pYear = year;
                    pMonth = monthOfYear;
                    pDay = dayOfMonth;
                    updateDisplay();
                }
            };

    /** Updates the date in the TextView */
    private void updateDisplay() {
        pDisplayDate.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(pDay).append("/")
                        .append(pMonth + 1).append("/")
                        .append(pYear).append(" "));
    }


    /** Create a new dialog for date picker */
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        pDateSetListener,
                        pYear, pMonth, pDay);
        }
        return null;
    }

    public void updateExpenditure(View view){
        Expenditure newExpenditure = new Expenditure();

        date = (EditText) findViewById((R.id.displayDate));
        categories_expenditure_spinner = (Spinner) findViewById(R.id.categories_depense_spinner);
        nom = (EditText) findViewById(R.id.nom_depense_editText);
        montant = (EditText) findViewById(R.id.montant_depense_editText);

        if(!categories_expenditure_spinner.getSelectedItem().toString().equals("")
                && !nom.getText().toString().equals("")
                && Float.parseFloat(montant.getText().toString()) != 0){

            newExpenditure.setCategorie(categories_expenditure_spinner.getSelectedItem().toString());
            newExpenditure.setNom(nom.getText().toString());
            newExpenditure.setMontant(Float.parseFloat(montant.getText().toString()));

            BDDManager bdd = new BDDManager(this);
            bdd.open();
            bdd.updateDepense(id,newExpenditure);
        }
        Toast.makeText(getApplicationContext(), "test : MODIIIFF"+id,Toast.LENGTH_LONG).show();
    }
}
