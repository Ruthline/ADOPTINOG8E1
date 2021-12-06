package com.example.Adoptino;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.Adoptino.fragmentos.SignupTabFragment;
import com.example.Adoptino.fragmentos.fragment_Sobrenosotros;
import com.example.Adoptino.fragmentos.fragment_reportados;
import com.example.Adoptino.fragmentos.fragment_reportar;
import com.example.Adoptino.fragmentos.loginTabFragment;
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


    //variables de inicio de sesion(provicional)
    /*TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton fb, google,ws;
    float v=0;*/

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
        //abriendo el fragment reportados
        if(item.getItemId() == R.id.reportadosb){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mcontainer, new fragment_reportados());
            fragmentTransaction.commit();
        }
        //abriendo el fragment registrar
        if(item.getItemId() == R.id.registrarb){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mcontainer, new SignupTabFragment());
            fragmentTransaction.commit();
        }
        //abriendo el fragment inciar sesion
        if(item.getItemId() == R.id.iniciosb){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mcontainer, new loginTabFragment());
            fragmentTransaction.commit();

           /* tabLayout= findViewById(R.id.tab_layout);
            viewPager= findViewById(R.id.view_pager);
            fb= findViewById(R.id.fab_fb);
            google= findViewById(R.id.fab_google);
            ws= findViewById(R.id.fab_ws);

            tabLayout.addTab(tabLayout.newTab().setText("Login"));
            tabLayout.addTab(tabLayout.newTab().setText("Signup"));
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

            final LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(),this, tabLayout.getTabCount());
            viewPager.setAdapter(adapter);

            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            fb.setTranslationY(300);
            google.setTranslationY(300);
            ws.setTranslationY(300);
            tabLayout.setTranslationY(300);

            fb.setAlpha(v);
            google.setAlpha(v);
            ws.setAlpha(v);
            tabLayout.setAlpha(v);

            fb.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
            google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
            ws.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
            tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();*/
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

    private void setBackgroundResources(int image) {
    }
}