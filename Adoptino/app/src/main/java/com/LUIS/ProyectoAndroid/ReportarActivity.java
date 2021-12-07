package com.LUIS.ProyectoAndroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class ReportarActivity extends AppCompatActivity {

    ImageView imagen;


    private TextInputEditText ti1, ti2;
    private EditText et1, et2, et3, et4;

    private MyDBSQLiteHelper admin;
    private SQLiteDatabase db;
    private ContentValues cv;
    private Cursor filas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_reportar);

        imagen =(ImageView) findViewById(R.id.imgPhoto);

        ti1 = (TextInputEditText) findViewById(R.id.nombremascota);
        ti2 = (TextInputEditText) findViewById(R.id.nombredueño);
        et1 = findViewById(R.id.editTextDate2);
        et2 = findViewById(R.id.editTextPhone2);
        et3 = findViewById(R.id.editTextdescripcion);
        et4 = findViewById(R.id.editTextzona);
        admin = new MyDBSQLiteHelper(this, vars.nomDB2, null, vars.version2);

        //Activar soporte para la ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getParametros();
    }

    public void onClick(View view) {
        cargarImagen();
    }

    private void cargarImagen() {
        Intent intent =new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione la aplicación"),10);
    }

    @Override
    protected void onActivityResult(int requestCode , int resultCode , @Nullable Intent data) {
        super.onActivityResult(requestCode , resultCode , data);
        if (resultCode==RESULT_OK){
            Uri path= data.getData();
            imagen.setImageURI(path);
        }
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

    public void agregarReporte(View view) {

        String nom = ti1.getText().toString();
        String nomdueño = ti2.getText().toString();
        String fecha = et1.getText().toString();
        String telf = et2.getText().toString();
        String descripcion = et3.getText().toString();
        String zona = et4.getText().toString();

        if (!nom.equals("") && !ti1.equals("") &&
                !ti2.equals("") && !et1.equals("") && !et2.equals("") && !et3.equals("") && !et3.equals("")) {
            db = admin.getWritableDatabase();
            cv = new ContentValues();
            cv.put("nombre", nom);
            cv.put("nomdueño", nomdueño);
            cv.put("fecha", fecha);
            cv.put("telefono", telf);
            cv.put("descripcion", descripcion);
            cv.put("zona", zona);
            long reg = db.insert("reportes", null, cv);
            if (reg != -1) {
                Toast.makeText(this, "Reporte almacenado", Toast.LENGTH_SHORT).show();
                ti1.setText("");
                ti2.setText("");
                et1.setText("");
                et2.setText("");
                et3.setText("");
                et4.setText("");
            } else
                Toast.makeText(this, "El reporte no se pudo almacenar", Toast.LENGTH_SHORT).show();
            db.close();
        } else {
            Toast.makeText(this, "Por favor, ingrese todos los datos", Toast.LENGTH_SHORT).show();
        }
    }
}
