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

import adapters.CarAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Autos.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Autos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Autos extends Fragment implements CarAdapter.Onclick{
        private Button addAuto;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private RecyclerView lista;

    public Autos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Autos.
     */
    // TODO: Rename and change types and number of parameters
    public static Autos newInstance(String param1, String param2) {
        Autos fragment = new Autos();
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
        View view = inflater.inflate(R.layout.fragment_autos, container, false);

        //Seteo de la barra de navegacion
        ((MainActivity)getContext()).navigationView.setCheckedItem(R.id.autos);


        //Intancias de los elementos;
        addAuto = (Button) view.findViewById(R.id.addAuto_btn);
        lista = (RecyclerView) view.findViewById(R.id.ListAutos);

        //Llenaod de la Lista
        lista.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        CarAdapter adaptarCar = new CarAdapter(((MainActivity)getContext()).usuario.getListaAutosOriginal(),this);
        lista.setAdapter(adaptarCar);

        //Mensaje si no hay autos
        if(((MainActivity)getContext()).usuario.getCant_autos() == 0){
            Dialogs dialogs = new Dialogs();
            dialogs.Alert(getContext(),"Sin Autos","Esta cuenta no posee Autos");
        }


        //Acciones de los botones
        addAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCar(v);
            }
        });
        //return
        return view;
    }

    private void addCar(View view){
        try {
            Fragment fragment = new addAuto();
            ((MainActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).addToBackStack(null).commit();

        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

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
    public void onClickcarAdapter(int pos) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
