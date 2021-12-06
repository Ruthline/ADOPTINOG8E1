package com.example.Adoptino.fragmentos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.Adoptino.MyDBSQLite;
import com.example.Adoptino.R;
import com.example.Adoptino.vars;

public class fragment_reportar extends Fragment{
    private MyDBSQLite admin;
    private SQLiteDatabase db;
    private ContentValues cv;
    private Cursor filas;
    private EditText nommas, telefono, nomdueño, descripmascota, zona;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.reportar_fragment,container, false);
        return view;
    }

    public void onCreate(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.reportar_fragment, container, false);
        //setContentView(R.layout.reportar_fragment);

        admin = new MyDBSQLite(this, vars.nomDB, null, vars.version);

        nommas = (EditText) getView().findViewById(R.id.textnommas);
        nomdueño = (EditText) getView().findViewById(R.id.textnomdueño);
        telefono = (EditText) getView().findViewById(R.id.textelefono);
        descripmascota = (EditText) getView().findViewById(R.id.textdescripmascota);
        zona = (EditText) getView().findViewById(R.id.textzona);

    }

    public void agregarDatos(View view) {
        String nom = nommas.getText().toString();
        String due = nomdueño.getText().toString();
        String tel = telefono.getText().toString();
        String des = descripmascota.getText().toString();
        String zon = zona.getText().toString();

        if (!nom.equals("") && !des.equals("") &&
                !due.equals("") && !tel.equals("") && !zon.equals("")) {
            db = admin.getWritableDatabase();
            cv = new ContentValues();
            cv.put("nommascota", nom);
            cv.put("nomdueño", des);
            cv.put("telefono", tel);
            cv.put("descripcion", des);
            cv.put("zona", zon);
            long reg = db.insert("reportes", null, cv);
            if (reg != -1) {
                Toast.makeText(getActivity(), "Registro almacenado", Toast.LENGTH_SHORT).show();
                nommas.setText("");
                nomdueño.setText("");
                telefono.setText("");
                descripmascota.setText("");
                zona.setText("");
            } else
                Toast.makeText(getActivity(), "El registro no se pudo almacenar", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(getActivity(), "Por favor, ingrese todos los datos", Toast.LENGTH_SHORT).show();
    }

    public void listarDatos(View view) {
//        db = admin.getReadableDatabase();
//        filas = db.rawQuery("SELECT * FROM producto", null);
//        while (filas.moveToNext()) {
//            Toast.makeText(this, filas.getInt(0) + "-" + filas.getString(1) + "-" + filas.getString(2), Toast.LENGTH_SHORT).show();
//        }
//        db.close();
        Intent intent = new Intent(getActivity(), fragment_reportados.class);
        intent.putExtra("nomTabla", "reportes");
        startActivity(intent);
    }

    public void eliminarDatos(View view) {
        String nom = nommas.getText().toString();
        String due = nomdueño.getText().toString();

        db = admin.getWritableDatabase();
        if (!nom.equals("")) {
            String[] args = new String[]{nom};
            int reg = db.delete("reportes", "nombre=?", args);
            if (reg == 0)
                Toast.makeText(getActivity(), "El registro no se pudo eliminar", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getActivity(), "Registro eliminado", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(getActivity(), "Por favor, ingrese el nombre de la mascota", Toast.LENGTH_SHORT).show();
    }

    public void editarDatos(View view) {
        String nom = nommas.getText().toString();
        String due = nomdueño.getText().toString();
        String tel = telefono.getText().toString();
        String des = descripmascota.getText().toString();
        String zon = zona.getText().toString();

        db = admin.getWritableDatabase();
        cv = new ContentValues();
        cv.put("nomdueño", des);
        cv.put("telefono", tel);
        cv.put("descripcion", des);
        cv.put("zona", zon);
        int reg = db.update("reportes", cv, "nombre=" + "'" + nom + "'", null);
        if (reg == 0) {
            Toast.makeText(this, "El registro no se pudo editar", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Registro editado", Toast.LENGTH_SHORT).show();
            nommas.setText("");
            nomdueño.setText("");
        }
    }

    public void buscarDatos(View view) {
        String nom = nommas.getText().toString();
        String tel = telefono.getText().toString();
        String des = descripmascota.getText().toString();
        String zon = zona.getText().toString();

        if(!nom.equals("")) {
            db = admin.getReadableDatabase();
            filas = db.rawQuery("SELECT * FROM reportes WHERE nombre='" + nom + "'", null);
            if (filas.moveToFirst()) {
                nomdueño.setText(filas.getString(2));

            } else {
                Toast.makeText(getActivity(), "La mascota no existe", Toast.LENGTH_SHORT).show();
            }
            db.close();
        }
        else
            Toast.makeText(getActivity(), "Por favor, ingrese el nombre de la mascota", Toast.LENGTH_SHORT).show();
    }

    public void limpiarDatos(View view) {
        nommas.setText("");
        nomdueño.setText("");
    }
}
