package com.LUIS.ProyectoAndroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegistroActivity extends AppCompatActivity {

    private TextInputEditText ti1, ti2;
    private EditText et1, et2, et3, et4;
    private MyDBSQLiteHelper admin;
    private SQLiteDatabase db;
    private ContentValues cv;
    private Cursor filas;

    private String nom = "";
    private String ape = "";
    private String fecha = "";
    private String telf = "";
    private String correo = "";
    private String contra = "";

    FirebaseAuth fa;
    DatabaseReference df;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_registro);
        ti1 = (TextInputEditText) findViewById(R.id.nombre1);
        ti2 = (TextInputEditText) findViewById(R.id.apellido2);
        et1 = findViewById(R.id.editTextDate2);
        et2 = findViewById(R.id.editTextPhone2);
        et3 = findViewById(R.id.editTextTextEmailAddress2);
        et4 = findViewById(R.id.editTextTextPassword2);
        fa = FirebaseAuth.getInstance();
        df = FirebaseDatabase.getInstance().getReference();
        admin = new MyDBSQLiteHelper(this, vars.nomDB , null, vars.version);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    public void agregarDatos(View view) {

        nom = ti1.getText().toString();
         ape = ti2.getText().toString();
         fecha = et1.getText().toString();
         telf = et2.getText().toString();
        correo = et3.getText().toString();
         contra = et4.getText().toString();

        if (!nom.equals("") && !ti1.equals("") &&
                !ti2.equals("") && !et1.equals("") && !et2.equals("") && !et3.equals("") && !et3.equals("")) {
            if(contra.length()<6){
                Toast.makeText(RegistroActivity.this, "La contraseña debe tener 6 caracteres", Toast.LENGTH_SHORT).show();
            }else{
          registrar();
            db = admin.getWritableDatabase();
            cv = new ContentValues();
            cv.put("nombre", nom);
            cv.put("apellido", ape);
            cv.put("fecha", fecha);
            cv.put("telefono", telf);
            cv.put("correo", correo);
            cv.put("contra", contra);
            long reg = db.insert("registro", null, cv);
            if (reg != -1) {
                Toast.makeText(this, "Registro almacenado", Toast.LENGTH_SHORT).show();
                ti1.setText("");
                ti2.setText("");
                et1.setText("");
                et2.setText("");
                et3.setText("");
                et4.setText("");
            } else
                Toast.makeText(this, "El registro no se pudo almacenar", Toast.LENGTH_SHORT).show();
            db.close();
        }}
        else{
            Toast.makeText(this, "Por favor, ingrese todos los datos", Toast.LENGTH_SHORT).show();
        }
    }
    public void listarDatos(View view) {
        //db = admin.getReadableDatabase();
        //filas = db.rawQuery("SELECT * FROM producto", null);
        //while (filas.moveToNext()) {
        //    Toast.makeText(this, filas.getInt(0) + "-" + filas.getString(1) + "-" + filas.getString(2)+ "-" +
        //            filas.getString(3)+ "-" + filas.getString(4)+ "-" + filas.getString(5)+ "-" + filas.getString(6), Toast.LENGTH_SHORT).show();
        //}
        //db.close();
        Intent intent = new Intent(this, ListViewActivity.class);
        intent.putExtra("nomTabla", "registro");
        startActivity(intent);
    }
    public void eliminarDatos(View view) {
        String nom = ti1.getText().toString();

        db = admin.getWritableDatabase();
        if (!nom.equals("")) {
//            int reg = db.delete("producto", "nombre=" + "'" + nom + "'", null);
            String[] args = new String[]{nom};
            int reg = db.delete("registro", "nombre=?", args);
            if (reg == 0)
                Toast.makeText(this, "El registro no se pudo eliminar", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Registro eliminado", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(this, "Por favor, ingrese el nombre del producto", Toast.LENGTH_SHORT).show();
    }
    public void editarDatos(View view) {
        String nom = ti1.getText().toString();
        String ape = ti2.getText().toString();

        db = admin.getWritableDatabase();
        cv = new ContentValues();
        cv.put("apellido", ape);
        int reg = db.update("registro", cv, "nombre=" + "'" + nom + "'", null);
        if (reg == 0)
            Toast.makeText(this, "El registro no se pudo editar", Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(this, "Registro editado", Toast.LENGTH_SHORT).show();
            et1.setText("");
            et2.setText("");
        }
    }
    public void buscarDatos(View view) {
        String nom = ti1.getText().toString();

        if(!nom.equals("")) {
            db = admin.getReadableDatabase();
            filas = db.rawQuery("SELECT * FROM registro WHERE nombre='" + nom + "'", null);
            if (filas.moveToFirst()) {
                et2.setText(filas.getString(2));
            } else {
                Toast.makeText(this, "El producto no existe", Toast.LENGTH_SHORT).show();
            }
            db.close();
        }
        else
            Toast.makeText(this, "Por favor, ingrese el nombre del producto", Toast.LENGTH_SHORT).show();
    }
    private void registrar(){
        fa.createUserWithEmailAndPassword(correo, contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    String id = fa.getCurrentUser().getUid();
                    Map<String,Object> map = new HashMap<>();
                    map.put("name",nom);
                    map.put("lastnname",ape);
                    map.put("telefono",telf);
                    map.put("correo",correo);
                    map.put("password",contra);
                   df.child("users").child(id).setValue(map);
                }
                else{

                }
            }
        });
    }
}