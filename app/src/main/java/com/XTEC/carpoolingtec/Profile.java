package com.XTEC.carpoolingtec;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


public class Profile extends Fragment {

    private Spinner spiner1;
    private EditText Name,LName,usrName,usrId;
    private String sName,sLName;

    public Profile() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            sName = getArguments().getString("Name","456");
            sLName = getArguments().getString("LName","");
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        Name = (EditText) view.findViewById(R.id.ProfileName);
        LName = (EditText) view.findViewById(R.id.ProfileLName);
        usrName = (EditText) view.findViewById(R.id.ProfileusrName);
        usrId = (EditText) view.findViewById(R.id.profileID);
        spiner1 = (Spinner) view.findViewById(R.id.spinner);

        //Llenado del spinner
        String [] Categorias = {"Pasagero", "Conductor"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,Categorias);
        spiner1.setAdapter(adapter);

        //llenado de valores
        Name.setText(sName);
        Name.setEnabled(false);
        LName.setText(sLName);
        LName.setEnabled(false);

        return  view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
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
