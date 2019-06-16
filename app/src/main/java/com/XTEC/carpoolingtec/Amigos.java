package com.XTEC.carpoolingtec;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import Data.Usuario;
import adapters.friendAdapter;
import adapters.requestsAdapter;


public class Amigos extends Fragment implements friendAdapter.Onclick, requestsAdapter.Onclick  {

    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerSolicitudes,recyclerAmigos;
    private friendAdapter adapterfriend;
    private requestsAdapter adapterequest;

    private ArrayList<Usuario> listaAmigos= new ArrayList<Usuario>();



    public Amigos() {
        // Required empty public constructor
    }


    public static Amigos newInstance(String param1, String param2) {
        Amigos fragment = new Amigos();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_amigos, container, false);
        try {
            recyclerSolicitudes = view.findViewById(R.id.friendrequestRecycler);
            recyclerSolicitudes.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            //Instancias de los elementos
            recyclerAmigos = (RecyclerView) view.findViewById(R.id.friendlistRecycler);
            recyclerAmigos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


            //Instancias de los adapter
            listaAmigos = ((MainActivity)getContext()).usuario.getListaAmigos();
            adapterfriend = new friendAdapter(listaAmigos, this);
            recyclerAmigos.setAdapter(adapterfriend);
            adapterequest = new requestsAdapter(((MainActivity)getContext()).usuario.getListaSolicitudes(),this);
            recyclerSolicitudes.setAdapter(adapterequest);


            //Seteo de la barra de navegacion
            ((MainActivity) getContext()).navigationView.setCheckedItem(R.id.amigos);
        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
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
    public void onClickfriendAdapter(int pos) {
        Toast.makeText(getContext(),"Se presiono un amigo",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClickrequestAdapter(final int pos) {

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        Toast.makeText(getContext(),"Solicitud aceptada",Toast.LENGTH_SHORT).show();
                        adapterequest.notifyDataSetChanged();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        ((MainActivity)getContext()).usuario.getListaSolicitudes().remove(pos);
                        adapterequest.notifyDataSetChanged();
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("¿desea aceptar la solicitud de "+
                ((MainActivity)getContext()).usuario.getListaSolicitudes().get(pos).getNombre()+"?")
                .setPositiveButton("Si", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
