package com.example.Adoptino.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.Adoptino.R;

public class fragment_reportar extends Fragment{
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

        nommas = (EditText) getView().findViewById(R.id.textnommas);
        nomdueño = (EditText) getView().findViewById(R.id.textnomdueño);
        telefono = (EditText) getView().findViewById(R.id.textelefono);
        descripmascota = (EditText) getView().findViewById(R.id.textdescripmascota);
        zona = (EditText) getView().findViewById(R.id.textzona);

    }

    public void obtenerDatos(View view) {
        String nom = "Nombre mascota: " + nommas.getText().toString();
        String fnac = "Teléfono: " + telefono.getText().toString();
        String tel = "Nombre dueño: " + nomdueño.getText().toString();
        String email = "Descripción mascota: " + descripmascota.getText().toString();
        String dir = "Zona de perdida: " + zona.getText().toString();

    }
}
