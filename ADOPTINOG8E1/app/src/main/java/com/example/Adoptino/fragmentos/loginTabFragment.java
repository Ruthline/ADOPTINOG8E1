package com.example.Adoptino.fragmentos;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.Adoptino.R;
import com.google.android.material.tabs.TabLayout;

import java.net.PasswordAuthentication;

public class loginTabFragment extends Fragment {

    /*Email email;
    Pass pass;
    ForgetPass forgetPass;
    Login login;*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tag_fragment, container, false);

       /* email= root.findViewById(R.id.email);
        pass= root.findViewById(R.id.pass);
        forgetPass= root.findViewById(R.id.forgetPass);
        login= root.findViewById(R.id.login);


        email.setTranslation(000);
        pass.setTranslation(000);
        forgetPass.setTranslation(000);
        login.setTranslation(000);


        email.setAlpha(v);
        pass.setAlpha(v);
        forgetPass.setAlpha(v);
        login.setAlpha(v);

        email.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetPass.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();*/

        return  root;
    }

}
