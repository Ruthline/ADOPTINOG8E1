package com.LUIS.ProyectoAndroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ReportadosActivity extends AppCompatActivity {

    private ListView lv;
    private MyDBSQLiteHelper admin;
    private SQLiteDatabase db;
    private Cursor filas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportados);
//        Toast.makeText(this, "Método onCreate()", Toast.LENGTH_LONG).show();
        lv = findViewById(R.id.listareportados);

        admin = new MyDBSQLiteHelper(this, vars.nomDB2 , null, vars.version2);

        ArrayList<String> listado = new ArrayList<String>();



        db = admin.getReadableDatabase();

            filas = db.rawQuery("SELECT * FROM reportes", null);
            while (filas.moveToNext()) {
                listado.add("Nombre: " + filas.getString(1) + "\n\nNombre dueño: " + filas.getString(2) + "\n\nFecha de perdida: " +
                        filas.getString(3) + "\n\nTeléfono: " + filas.getString(4)+ "\n\nDescripción: " + filas.getString(5)+ "\n\nZona perdida: " + filas.getString(6));
            }
            db.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, listado);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Toast.makeText(getApplicationContext(), " - "+lv.getItemAtPosition(pos), Toast.LENGTH_SHORT).show();
            }
        });

        //Activar soporte para la ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getParametros();
    }

    public void goToActivityMain(View view) {
        Intent newIntent = new Intent(this, MainActivity.class);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(newIntent);

    }
    public void OnBackPressed() {
        finish();
    }
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id= menuItem.getItemId();

        if (id == android.R.id.home){
            OnBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

}