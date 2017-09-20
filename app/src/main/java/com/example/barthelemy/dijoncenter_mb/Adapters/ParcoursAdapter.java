package com.example.barthelemy.dijoncenter_mb.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.barthelemy.dijoncenter_mb.Database.DiiageBaseDb;
import com.example.barthelemy.dijoncenter_mb.Model.Parcours;
import com.example.barthelemy.dijoncenter_mb.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Max on 20/09/2017.
 */

public class ParcoursAdapter extends BaseAdapter {

    public static final String TABLE = "parcours";
    public SQLiteDatabase db;
    ArrayList<Parcours> list;
    Context context;

    public ParcoursAdapter(SQLiteDatabase db) {
        this.db = db;
    }

    public ParcoursAdapter(SQLiteDatabase db, Context context) {
        this.db = db;
        this.list = getAllParcours();
        this.context = context;
    }

    public ArrayList<Parcours> getAllParcours() {

        Cursor c = db.query("parcours", new String[]{"id", "idCinema", "idRestaurant", "date_creation", "date_realisation", "commentaire", "statut"}, //table
                null,
                null, //parametre de selection
                null, //order
                null, //group
                null); //limit

        ArrayList<Parcours> parcours = new ArrayList<>();

        while (c.moveToNext()) {
            Parcours comm = new Parcours(c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6));
            parcours.add(comm);
        }

        c.close();

        return parcours;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).
                    inflate(R.layout.layout_list_view_row_items, viewGroup, false);
        }

        Parcours current = list.get(i);

        TextView textViewItemName = (TextView)
                view.findViewById(R.id.text_view_item_name);
        TextView textViewItemAuthor = (TextView)
                view.findViewById(R.id.text_view_item_description);
        TextView textViewItemDescription = (TextView)
                view.findViewById(R.id.text_view_item_address);

        String dataTitle = "Cinéma : " + current.getIdCinema().toString() + " & Restaurant : " + current.getIdRestaurant().toString();
        String dataAuth = current.getCommentaire();
        String dataGenre = "Le : " + current.getDateRealisation().toString() + " / Statut : " + current.getStatut();

        textViewItemName.setText(dataTitle);
        textViewItemAuthor.setText(dataAuth);
        textViewItemDescription.setText(dataGenre);

        return view;
    }
}
