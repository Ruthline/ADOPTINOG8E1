package com.LUIS.ProyectoAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class ParaAdoptarActivity extends AppCompatActivity {

    private TextInputEditText ti1, ti2;
    private EditText et1, et2, et3;

    private MyDBSQLiteHelper admin;
    private SQLiteDatabase db;
    private ContentValues cv;
    private Cursor filas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_para_adoptar);
        ti1 = (TextInputEditText) findViewById(R.id.nombremascotaadopta);
        ti2 = (TextInputEditText) findViewById(R.id.fundacion);
        et1 = findViewById(R.id.ciudaadopta);
        et2 = findViewById(R.id.telefonoadopta);
        et3 = findViewById(R.id.descripcionadopta);

        admin = new MyDBSQLiteHelper(this, vars.nomDB, null, vars.version);

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

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == android.R.id.home) {
            OnBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void agregarAdoptar(View view) {

        String nom = ti1.getText().toString();
        String fundacion = ti2.getText().toString();
        String ciudad = et1.getText().toString();
        String telefono = et2.getText().toString();
        String descripcion = et3.getText().toString();


        if (!nom.equals("") && !ti1.equals("") &&
                !ti2.equals("") && !et1.equals("") && !et2.equals("") && !et3.equals("") && !et3.equals("")) {
            db = admin.getWritableDatabase();
            cv = new ContentValues();
            cv.put("nombre", nom);
            cv.put("fundacion", fundacion);
            cv.put("ciudad", ciudad);
            cv.put("telefono", telefono);
            cv.put("descripcion", descripcion);
            long reg = db.insert("adoptar", null, cv);
            if (reg != -1) {
                Toast.makeText(this, "Mascota almacenado", Toast.LENGTH_SHORT).show();
                ti1.setText("");
                ti2.setText("");
                et1.setText("");
                et2.setText("");
                et3.setText("");

            } else
                Toast.makeText(this, "La mascota no se pudo almacenar", Toast.LENGTH_SHORT).show();
            db.close();
        } else {
            Toast.makeText(this, "Por favor, ingrese todos los datos", Toast.LENGTH_SHORT).show();
        }
    }
}
