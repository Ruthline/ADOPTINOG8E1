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


        //todo lo de camara
    ImageView picture;
    ImageButton openCamera;

    private static final int REQUEST_PERMISSION_CAMERA =100;
    private static final int REQUEST_IMAGE_CAMERA =101;

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

        //Lo de camara
        picture=findViewById(R.id.picture);
        openCamera= findViewById(R.id.btnOpenCamera);

        openCamera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(ActivityCompat.checkSelfPermission(ReportarActivity.this, Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
                        goToCamera();
                    }else{
                        ActivityCompat.requestPermissions(ReportarActivity.this,new String[]{Manifest.permission.CAMERA},REQUEST_PERMISSION_CAMERA);
                    }
                }else {
                    goToCamera();
                }
                }
        });
        //final de camara
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[]grantResults){
        if (requestCode == REQUEST_PERMISSION_CAMERA){
            if(permissions.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                goToCamera();
            }else{
                Toast.makeText(this, "you need to enable permission",Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_IMAGE_CAMERA){
            if (resultCode== Activity.RESULT_OK){
                Bitmap bitmap= (Bitmap) data.getExtras().get("data");
                picture.setImageBitmap(bitmap);
                Log.i("TAG","Result=>"+bitmap);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //para camera
    private void goToCamera(){
        Intent camaraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(camaraIntent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(camaraIntent,REQUEST_IMAGE_CAMERA);
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
