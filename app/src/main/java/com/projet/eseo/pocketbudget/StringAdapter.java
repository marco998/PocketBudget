package com.projet.eseo.pocketbudget;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;
/**
 * Created by marc-antoine on 03/02/15.
 */
public class StringAdapter extends BaseAdapter {
    ArrayList<Income> data;
    LayoutInflater inflater;

    public StringAdapter(Context context,ArrayList<Income> data) {
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
            convertView = inflater.inflate(R.layout.activity_list_incomes_items, null);
            // On lie les deux TextView déclarés précédemment à ceux du xml
            holder.nomElement = (TextView)convertView.findViewById(R.id.nom);
            holder.montantElement = (TextView)convertView.findViewById(R.id.montant);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // On défini ici le texte que doit contenir chacun des TextView
        holder.nomElement.setText(((Income)this.getItem(position)).getNom());
        holder.montantElement.setText(String.valueOf(((Income) this.getItem(position)).getMontant()));
        return convertView;
    }

}
