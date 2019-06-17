package com.XTEC.carpoolingtec;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Inicio extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Button Stat, viaje,nviaje;
    private TextView Puntos;

    private String mParam1;
    private String mParam2;


    public Inicio() {
        // Required empty public constructor
    }


    public static Inicio newInstance(String param1, String param2) {
        Inicio fragment = new Inicio();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        //Seteo de la barra de navegacion
        ((MainActivity)getContext()).navigationView.setCheckedItem(R.id.nav_home);

        //Intancias de los elementos graficos
        Stat = (Button) view.findViewById(R.id.estat_btn);
        viaje = (Button) view.findViewById(R.id.viaje_btn);
        nviaje = (Button) view.findViewById(R.id.nviaje_btn);
        Puntos = (TextView) view.findViewById(R.id.puntos);

        if(((MainActivity)getContext()).usuario.getCant_autos() == 0){
            nviaje.setEnabled(false);
            nviaje.setVisibility(View.INVISIBLE);
        } else{
            nviaje.setEnabled(true);
            nviaje.setVisibility(View.VISIBLE);
        }

        //Dar valor a los lables
        Puntos.setText(((MainActivity)getContext()).usuario.getCant_puntos()+ " pts.");

        //Acciones de los Botones
        Stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dshaboards(v);
            }
        });
        viaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invitaciones(v);
            }
        });
        nviaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nviaje(v);
            }
        });

        return view;
    }

    private void dshaboards(View view){
        Fragment fragment = new Dashboards();
        ((MainActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).addToBackStack(null).commit();
    }

    private void invitaciones(View view){
        Fragment fragment = new Dashboards();
        ((MainActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).addToBackStack(null).commit();
    }

    private void Nviaje(View view){
        Fragment fragment = new n_viaje();
        ((MainActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).addToBackStack(null).commit();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}
