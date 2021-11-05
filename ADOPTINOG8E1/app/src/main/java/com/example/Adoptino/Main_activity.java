package com.example.Adoptino;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.Adoptino.fragmentos.fragment_Sobrenosotros;
import com.example.Adoptino.fragmentos.fragment_iniciar_sesion;
import com.example.Adoptino.fragmentos.fragment_registrar_usuario;
import com.example.Adoptino.fragmentos.fragment_reportar;
import com.example.Adoptino.fragmentos.mainfragment;
import com.google.android.material.navigation.NavigationView;

public class Main_activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //declarando drawer
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    //declarando fragment principal
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //cargando drawer
        toolbar =findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);

        //On click listener drawer
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        //cargando fragment
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.mcontainer, new mainfragment());
        fragmentTransaction.commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);

        //abriendo el fragmment Adopta
        if(item.getItemId() == R.id.Adoptab){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mcontainer, new mainfragment());
            fragmentTransaction.commit();
        }
        //abriendo el fragment reporta
        if(item.getItemId() == R.id.reportarb){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mcontainer, new fragment_reportar());
            fragmentTransaction.commit();
        }
        //abriendo el fragment registrar
        if(item.getItemId() == R.id.registrarb){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mcontainer, new fragment_registrar_usuario());
            fragmentTransaction.commit();
        }
        //abriendo el fragment inciar sesion
        if(item.getItemId() == R.id.iniciosb){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mcontainer, new fragment_iniciar_sesion());
            fragmentTransaction.commit();
        }
        //abriendo el fragment sobre nosotros
        if(item.getItemId() == R.id.snb){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mcontainer, new fragment_Sobrenosotros());
            fragmentTransaction.commit();
        }
        return false;
    }
}