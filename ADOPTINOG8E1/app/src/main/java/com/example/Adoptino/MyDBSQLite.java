package com.example.Adoptino;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBSQLite extends  SQLiteOpenHelper {

    public MyDBSQLite(Context context, String nomDB, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nomDB, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE reportes(_id INTEGER PRIMARY KEY AUTOINCREMENT, nommascota TEXT, nomdueño TEXT," +
                "telefono TEXT, descripcion TEXT, zona TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int verDBAnterior, int verDBNueva) {
        db.execSQL("DROP TABLE IF EXISTS reportes");
        db.execSQL("CREATE TABLE reportes(_id INTEGER PRIMARY KEY AUTOINCREMENT, nommascota TEXT, nomdueño TEXT," +
                "telefono TEXT, descripcion TEXT, zona TEXT)");
    }
}

