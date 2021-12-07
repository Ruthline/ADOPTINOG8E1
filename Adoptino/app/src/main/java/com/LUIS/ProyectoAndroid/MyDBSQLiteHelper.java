package com.LUIS.ProyectoAndroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBSQLiteHelper extends SQLiteOpenHelper {
    public MyDBSQLiteHelper(Context context, String nomDB, SQLiteDatabase.CursorFactory factory, int version){
        super(context,nomDB,factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //DB registro
        db.execSQL("CREATE TABLE registro(_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellido TEXT, fecha TEXT, telefono TEXT, correo TEXT, contra TEXT)");
        //DB reportes
        db.execSQL("CREATE TABLE reportes(_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, nomdueño TEXT, fecha TEXT, telefono TEXT, descripcion TEXT, zona TEXT)");
        //DB adoptar
        db.execSQL("CREATE TABLE adoptar(_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, fundacion TEXT, ciudad TEXT, telefono TEXT, descripcion TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int verDBAnterior, int verDBNueva) {

        db.execSQL("DROP TABLE IF EXISTS registro");
        db.execSQL("DROP TABLE IF EXISTS reportes");
        db.execSQL("DROP TABLE IF EXISTS adoptar");
        db.execSQL("CREATE TABLE registro(_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellido TEXT, fecha TEXT, telefono TEXT, correo TEXT, contra TEXT)");
        db.execSQL("CREATE TABLE reportes(_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, nomdueño TEXT, fecha TEXT, telefono TEXT, descripcion TEXT, zona TEXT)");
        db.execSQL("CREATE TABLE adoptar(_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, fundacion TEXT, ciudad TEXT, telefono TEXT, descripcion TEXT)");

    }
}