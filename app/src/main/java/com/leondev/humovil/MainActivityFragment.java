package com.leondev.humovil;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.leondev.humovil.Activitys.ActivityCons;
import com.leondev.humovil.Activitys.ActivityOem;
import com.leondev.humovil.Activitys.ActivityPlanta;
import com.leondev.humovil.Activitys.ActivityProg;
import com.leondev.humovil.Activitys.ActivityWOA;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {


    //fragment_main
    private Button btnPlanta;
    private Button btnOem;
    private Button btnCons;
    private Button btnProg;
    private Button btnWOA;
    private View myView;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView =  inflater.inflate(R.layout.fragment_main, container, false);

        btnPlanta = (Button) myView.findViewById(R.id.btnPlanta);
        btnOem = (Button) myView.findViewById(R.id.btnOem);
        btnCons = (Button) myView.findViewById(R.id.btnCons);
        btnProg = (Button) myView.findViewById(R.id.btnProg);
        btnWOA = (Button) myView.findViewById(R.id.btnWOA);


        btnPlanta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ActivityPlanta = new Intent(getContext(), ActivityPlanta.class);
                startActivity(ActivityPlanta);
            }
        });

        btnOem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ActivityOem = new Intent(getContext(), ActivityOem.class);
                startActivity(ActivityOem);
            }
        });

        btnCons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ActivityCons = new Intent(getContext(), ActivityCons.class);
                startActivity(ActivityCons);
            }
        });

        btnProg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ActivityProg = new Intent(getContext(), ActivityProg.class);
                startActivity(ActivityProg);
            }
        });

        btnWOA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ActivityWOA = new Intent(getContext(), ActivityWOA.class);
                startActivity(ActivityWOA);
            }
        });

        return  myView;

    }
}
