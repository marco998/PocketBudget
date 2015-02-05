package com.projet.eseo.pocketbudget;

import android.app.DatePickerDialog;import android.app.Dialog;import android.app.DialogFragment;import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;import android.widget.ArrayAdapter;
import android.widget.Button;import android.widget.DatePicker;import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;import android.widget.TextView;import android.widget.Toast;

import org.apache.http.cookie.params.CookieSpecParamBean;

import java.text.SimpleDateFormat;import java.util.Calendar;

/**
 * Activité gérant la liste des revenus
 */

public class IncomeActivity extends ActionBarActivity {

    private ArrayAdapter<CharSequence> categories_income_adapter;
    private Spinner categories_income_spinner;
    private EditText date;
    private EditText nom;
    private EditText montant;

    private TextView pDisplayDate;
    private ImageButton pPickDate;
    private int pYear;
    private int pMonth;
    private int pDay;
    static final int DATE_DIALOG_ID = 0;
    private long id;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        categories_income_spinner = (Spinner) findViewById(R.id.categories_income_spinner);
        categories_income_adapter = ArrayAdapter.createFromResource(this, R.array.categories_list_income, android.R.layout.simple_spinner_item);
        categories_income_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categories_income_spinner.setAdapter(categories_income_adapter);

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
        Income income = new Income();
        BDDManager bdd = new BDDManager(this);
        bdd.open();
        income = bdd.getRevenuWithId(id);
        bdd.close();

        categories_income_spinner = (Spinner) findViewById(R.id.categories_income_spinner);
        nom = (EditText) findViewById(R.id.nom_revenu_ediText);
        montant = (EditText) findViewById(R.id.montant_revenu_editText);
        date=(EditText)findViewById(R.id.displayDate);

        String compareValue = income.getCategorie();
        if (!compareValue.equals(null)) {
            int spinnerPostion = categories_income_adapter.getPosition(compareValue);
            categories_income_spinner.setSelection(spinnerPostion);
            spinnerPostion = 0;
        }
        date.setText(income.getDate());
        nom.setText(income.getNom());
        montant.setText(String.valueOf(income.getMontant()));

    }

    public void updateIncome(View view){
        Income newIncome = new Income();

        date = (EditText) findViewById((R.id.displayDate));
        categories_income_spinner = (Spinner) findViewById(R.id.categories_income_spinner);
        nom = (EditText) findViewById(R.id.nom_revenu_ediText);
        montant = (EditText) findViewById(R.id.montant_revenu_editText);
        if(date.getText().toString().trim().length()>0 && nom.getText().toString().trim().length()>0 && montant.getText().toString().trim().length()>0 ) {

            if (!categories_income_spinner.getSelectedItem().toString().equals("")
                    && !nom.getText().toString().equals("")
                    && Float.parseFloat(montant.getText().toString()) != 0) {

                newIncome.setCategorie(categories_income_spinner.getSelectedItem().toString());
                newIncome.setNom(nom.getText().toString());
                newIncome.setMontant(Float.parseFloat(montant.getText().toString()));
                newIncome.setDate(date.getText().toString());

                BDDManager bdd = new BDDManager(this);
                bdd.open();
                bdd.updateRevenus(id, newIncome);
            }

            Intent intent = new Intent(this, ListIncomeActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this,R.string.error_insert,Toast.LENGTH_LONG).show();
        }
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
