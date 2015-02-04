package com.projet.eseo.pocketbudget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pierr_000 on 04/02/2015.
 */
public class StringAdapterExpenditure extends BaseAdapter {
    ArrayList<Expenditure> data;
    LayoutInflater inflater;

    public StringAdapterExpenditure(Context context,ArrayList<Expenditure> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return data.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView nomElement;
        TextView montantElement;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            holder = new ViewHolder();
            // On lie les éléments au fichier ligne_de_la_listview.xml
            convertView = inflater.inflate(R.layout.activity_list_expenditures_item, null);
            // On lie les deux TextView déclarés précédemment à ceux du xml
            holder.nomElement = (TextView)convertView.findViewById(R.id.nom);
            holder.montantElement = (TextView)convertView.findViewById(R.id.montant);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // On défini ici le texte que doit contenir chacun des TextView
        holder.nomElement.setText(((Expenditure)this.getItem(position)).getNom());
        holder.montantElement.setText(String.valueOf(((Expenditure) this.getItem(position)).getMontant()));
        return convertView;
    }

}
