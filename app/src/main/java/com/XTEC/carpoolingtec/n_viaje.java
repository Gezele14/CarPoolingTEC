package com.XTEC.carpoolingtec;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import Data.Auto;
import Data.Usuario;
import adapters.CarAdapter;
import adapters.friendAdapter;

public class n_viaje extends Fragment implements CarAdapter.Onclick, friendAdapter.Onclick {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private RecyclerView ListAutos, listaAmigosViaje;
    private Auto car;
    private ArrayList<Usuario> invitados = new ArrayList<Usuario>();
    private CarAdapter adaptarCar;
    private friendAdapter adapterfriend;

    private Button nTravel, Cancel;
    private ArrayList<Auto> AutosSelec = new ArrayList<Auto>();

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public n_viaje() {
    }

    public static n_viaje newInstance(String param1, String param2) {
        n_viaje fragment = new n_viaje();
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
        View view = inflater.inflate(R.layout.fragment_n_viaje, container, false);
        try {
            //Instancia de los elementos
            ListAutos = (RecyclerView) view.findViewById(R.id.listaAutos);
            ListAutos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

            listaAmigosViaje = (RecyclerView) view.findViewById(R.id.recyclerlistaAmigosviaje);
            listaAmigosViaje.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

            AutosSelec = ((MainActivity) getContext()).usuario.getListaAutos();
            nTravel = (Button) view.findViewById(R.id.nTravel_btn);
            Cancel = (Button) view.findViewById(R.id.nTraveCancel_btn);

            //Asignacion de la lista de autos
            adaptarCar = new CarAdapter(AutosSelec, this);
            ListAutos.setAdapter(adaptarCar);
            adapterfriend = new friendAdapter(((MainActivity)getContext()).usuario.getListaAmigos(),this);
            listaAmigosViaje.setAdapter(adapterfriend);

            //Accion de botones
            nTravel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    newTravel(v);
                }
            });
            Cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CancekTravel(v);
                }
            });
        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return view;
    }

    private void newTravel(View v){
        if( car == null){
            Toast.makeText(getContext(),"No se ha seleccionado Auto",Toast.LENGTH_SHORT).show();
        }else if(invitados.isEmpty()){
            Toast.makeText(getContext(),"No se han seleccionado invitados",Toast.LENGTH_SHORT).show();
        } else if(invitados.size() > (car.getCant_pers()-1)){
            Dialogs dialogs = new Dialogs();
            dialogs.Alert(getContext(),"Muchos invitados","El numero de invitados sobrepasa la cantidad de pasajeros del auto, por favor" +
                    " seleccione nuevamente a los invitados.");
            invitados.clear();
        }else{
            Toast.makeText(getContext(),"Se selecciono: "+car.getMarca()+" "+car.getModelo()+" Placa: "+car.getPlaca(),Toast.LENGTH_LONG).show();
        }
    }

    private void CancekTravel(View view){
        Toast.makeText(getContext(),"Nuevo viaje cancelado",Toast.LENGTH_SHORT).show();
        car = null;
        invitados.clear();
        ((MainActivity)getContext()).getSupportFragmentManager().popBackStack();
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClickcarAdapter(int pos) {
        car = AutosSelec.get(pos);
        Dialogs alert = new Dialogs();
        alert.Alert(getContext(),"Auto seleccionado","Se selecciono el auto #"+(pos+1));
    }

    @Override
    public void onClickfriendAdapter(int pos) {
        Usuario user = ((MainActivity)getContext()).usuario.getListaAmigos().get(pos);
        Dialogs alert = new Dialogs();
        if(invitados.contains(user)) {
            alert.Alert(getContext(), "Ya esta en lista", "Este usuario ya pertenece a la lista de invitados");
        }else {
            invitados.add(user);
            alert.Alert(getContext(), "Amigo Agregado", user.getNombre() + " se ha agregado a las lista de invitados\n" +
                    "Total de invitados: " + invitados.size());
        }
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
