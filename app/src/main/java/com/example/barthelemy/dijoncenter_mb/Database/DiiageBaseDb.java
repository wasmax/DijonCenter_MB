package com.example.barthelemy.dijoncenter_mb.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Max on 20/09/2017.
 */

public class DiiageBaseDb extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "diiagebase.db";

    public DiiageBaseDb(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Parcours ( "
                + "id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL ," +
                " nomCinema VARCHAR NOT NULL ," +
                " nomRestaurant VARCHAR NOT NULL ," +
                " date_creation DATE NOT NULL ," +
                " date_realisation DATE NOT NULL ," +
                " commentaire VARCHAR NOT NULL ," +
                " statut VARCHAR NOT NULL );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
