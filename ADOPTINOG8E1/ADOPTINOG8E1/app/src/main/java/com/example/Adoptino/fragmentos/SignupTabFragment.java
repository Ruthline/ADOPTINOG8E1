package com.example.Adoptino.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.Adoptino.R;
import androidx.fragment.app.Fragment;



public class SignupTabFragment extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root;
        root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container, false);

        return root;
    }
}
